package com.alipapa.smp.user.service;

import com.alipapa.smp.user.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 用户服务
 */
@Service
public class UserService {
    public User getUserByUserNo(String name) {
        return null;
    }


    public User getUserByName(String cnName) {
        return null;
    }

    /**
     * 添加用户
     *
     * @param user
     * @param groupId
     * @param roleList
     * @return
     */
    public boolean addUser(User user, String groupId, List<String> roleList) {
        return false;
    }

    /**
     * 获取最新的用户ID
     *
     * @return
     */
    public Long getLatestUserId() {
        return null;
    }

}
