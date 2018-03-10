package com.alipapa.smp.user.service;

import com.alipapa.smp.user.mapper.UserMapper;
import com.alipapa.smp.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 用户服务
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * @param id
     * @return
     */
    public User getUserById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * @param user
     * @return
     */
    public boolean updateUser(User user) {
        if (user == null || user.getId() == null) {
            return false;
        }
        userMapper.updateByPrimaryKey(user);
        return true;
    }


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
