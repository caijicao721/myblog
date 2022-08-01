package com.cao.myeblog.service;

import com.cao.myeblog.common.lang.Result;
import com.cao.myeblog.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cao.myeblog.shiro.AccountProfile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caojiaming
 * @since 2022-07-23
 */
public interface UserService extends IService<User> {

    Result register(User user);

    AccountProfile login(String username, String password);
}
