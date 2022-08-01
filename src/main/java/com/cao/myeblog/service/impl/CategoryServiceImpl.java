package com.cao.myeblog.service.impl;

import com.cao.myeblog.entity.Category;
import com.cao.myeblog.mapper.CategoryMapper;
import com.cao.myeblog.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
