package com.cao.myeblog.schedules;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cao.myeblog.entity.Post;
import com.cao.myeblog.service.PostService;
import com.cao.myeblog.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class ViewCountSyncTask {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    PostService postService;


    @Scheduled(cron = "0/5 * * * * *") //每半分钟同步
    public void task() {
        //获取所有文章缓存key
        Set<String> keys = redisTemplate.keys("rank:post:*");
        //获取所有有viewcount的id
        List<String> ids = new ArrayList<>();
        for (String key : keys) {
            if(redisUtil.hHasKey(key, "post:viewCount")){
                ids.add(key.substring("rank:post:".length()));
            }
        }
        // 如果空就直接返回
        if(ids.isEmpty()) return;

        // 需要更新阅读量
        List<Post> posts = postService.list(new QueryWrapper<Post>().in("id", ids));



        posts.forEach((post) ->{
            Integer viewCount = (Integer) redisUtil.hget("rank:post:" + post.getId(), "post:viewCount");
            post.setViewCount(viewCount);
        });

        //文章可能已经删除，如果没有直接跳过
        if(posts.isEmpty()) return;

        //mybatis-plus根据多个id进行更新
        boolean isSucc = postService.updateBatchById(posts);

        if(isSucc) {
            ids.forEach((id) -> {
                redisUtil.hdel("rank:post:" + id, "post:viewCount");
                System.out.println(id + "---------------------->同步成功");
            });
        }
    }

}
