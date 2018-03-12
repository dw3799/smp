package com.alipapa.smp.user.service;

import com.alipapa.smp.user.mapper.RoleMapper;
import com.alipapa.smp.user.mapper.UserMapper;
import com.alipapa.smp.user.mapper.UserRoleMapper;
import com.alipapa.smp.user.pojo.Role;
import com.alipapa.smp.user.pojo.User;
import com.alipapa.smp.user.pojo.UserExample;
import com.alipapa.smp.user.pojo.UserRole;
import com.alipapa.smp.user.vo.FuzzyUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * 用户服务
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;


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

    /**
     * @param userId
     * @return
     */
    public boolean deleteUser(Long userId) {
        if (userId == null) {
            return false;
        }
        userMapper.deleteByPrimaryKey(userId);
        return true;
    }

    /**
     * @param userNo
     * @return
     */
    public User getUserByUserNo(String userNo) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserNoEqualTo(userNo);
        List<User> userList = userMapper.selectByExample(example);

        if (!CollectionUtils.isEmpty(userList)) {
            return userList.get(0);
        }
        return null;
    }

    /**
     * @param name
     * @return
     */
    public User getUserByName(String name) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        List<User> userList = userMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(userList)) {
            return userList.get(0);
        }
        return null;
    }


    /**
     * 用户模糊查询
     *
     * @param searchString
     * @return
     */
    public List<FuzzyUserVo> userSearch(String searchString) {
        HashMap<String, String> params = new HashMap<>();
        params.put("searchString", searchString);

        List<User> userList = userMapper.fuzzyUserSearch(params);

        if (!CollectionUtils.isEmpty(userList)) {
            List<FuzzyUserVo> fuzzyUserVoList = new ArrayList<>();
            for (User user : userList) {
                FuzzyUserVo fuzzyUserVo = new FuzzyUserVo();
                fuzzyUserVo.setUserId(user.getId());
                fuzzyUserVo.setName(user.getName());
                fuzzyUserVo.setUserNo(user.getUserNo());
                fuzzyUserVoList.add(fuzzyUserVo);
            }
            return fuzzyUserVoList;
        }
        return null;
    }


    /**
     * 添加用户
     *
     * @param user
     * @param roleIdList
     * @return
     */
    public boolean addUser(User user, List<String> roleIdList) throws Exception {
        userMapper.insert(user);
        User savedUser = this.getUserByUserNo(user.getUserNo());
        for (String roleId : roleIdList) {
            Role role = roleMapper.selectByPrimaryKey(Long.valueOf(roleId));
            UserRole userRole = new UserRole();
            userRole.setRoleId(role.getId());
            userRole.setRoleName(role.getRoleName());
            userRole.setUserId(savedUser.getId());
            userRole.setUserNo(savedUser.getUserNo());
            userRole.setUuid(savedUser.getUuid());
            userRoleMapper.insert(userRole);
        }
        return false;
    }


    /**
     * @param groupId
     * @return
     */
    public List<User> listUserByGroupId(Long groupId) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIdEqualTo(groupId);
        List<User> userList = userMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(userList)) {
            return userList;
        }
        return null;
    }




    /**
     * 获取最新的用户ID
     *
     * @return
     */
    public Long getLatestUserId() {
        return userMapper.selectMaxId();
    }

}
