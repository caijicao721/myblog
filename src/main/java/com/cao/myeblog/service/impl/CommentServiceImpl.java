package com.cao.myeblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cao.myeblog.entity.Comment;
import com.cao.myeblog.mapper.CommentMapper;
import com.cao.myeblog.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cao.myeblog.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caojiaming
 * @since 2022-07-23
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    CommentMapper commentMapper;
    @Override
    public IPage<CommentVo> paing(Page page, Long postId, Long userId, String order) {

        return commentMapper.selectComments(page,new QueryWrapper<CommentVo>()
                .eq(postId!=null,"post_id",postId)
                .eq(userId != null, "user_id", userId)
                .orderByDesc(order != null, order));
    }
}
