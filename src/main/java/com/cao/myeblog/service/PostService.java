package com.cao.myeblog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cao.myeblog.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cao.myeblog.vo.PostVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caojiaming
 * @since 2022-07-23
 */
public interface PostService extends IService<Post> {

    IPage paging(Page page, Long categoryId, Long userId, Integer level, Boolean recommend, String order);

    PostVo selectOnePost(QueryWrapper<Post> wrapper);

    void initWeekRank();

    void incrCommentCountAndUnionForWeekRank(long postId, boolean isIncr);

    void putViewCount(PostVo vo);
}
