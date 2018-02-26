package com.alipapa.smp.user.service;

import com.alipapa.smp.user.pojo.User;


/**
 * 用户服务
 */
public interface UserService {
    User getUserByUserName(String name);

}
