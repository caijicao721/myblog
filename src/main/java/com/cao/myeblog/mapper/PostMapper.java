package com.cao.myeblog.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cao.myeblog.entity.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cao.myeblog.vo.PostVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author caojiaming
 * @since 2022-07-23
 */
@Component
public interface PostMapper extends BaseMapper<Post> {
    IPage<PostVo>  selectPosts(Page page,@Param(Constants.WRAPPER) QueryWrapper wrapper);

    PostVo selectOnePost(@Param(Constants.WRAPPER) QueryWrapper<Post> wrapper);
}
