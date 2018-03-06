package com.alipapa.smp.user.service;

import com.alipapa.smp.user.pojo.User;

import java.util.List;


/**
 * 用户服务
 */
public interface UserService {
    User getUserByUserName(String name);

    User getUserByCnName(String cnName);

    /**
     * 添加用户
     *
     * @param user
     * @param groupId
     * @param roleList
     * @return
     */
    boolean addUser(User user, String groupId, List<String> roleList);

    /**
     * 获取最新的用户ID
     *
     * @return
     */
    Long getLatestUserId();

}
