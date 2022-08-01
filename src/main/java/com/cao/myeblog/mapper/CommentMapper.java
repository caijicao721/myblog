package com.cao.myeblog.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cao.myeblog.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cao.myeblog.vo.CommentVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import javax.management.Query;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author caojiaming
 * @since 2022-07-23
 */
@Component
public interface CommentMapper extends BaseMapper<Comment> {

    IPage<CommentVo> selectComments(Page page, @Param(Constants.WRAPPER) QueryWrapper<CommentVo> wrapper);
}
