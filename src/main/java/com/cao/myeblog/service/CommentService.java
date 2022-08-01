package com.cao.myeblog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cao.myeblog.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cao.myeblog.vo.CommentVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caojiaming
 * @since 2022-07-23
 */
public interface CommentService extends IService<Comment> {

    IPage<CommentVo> paing(Page page, Long postId, Long userId, String order);
}
