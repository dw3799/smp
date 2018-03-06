package com.alipapa.smp.user.service;

import com.alipapa.smp.user.pojo.UserRole;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class UserRoleService {

    public List<UserRole> listRoleByUserId(Long userId) {
        return null;
    }

    public UserRole getUserRoleByUserIdAndRoleId(Long userId, Long roleId) {
        return null;
    }

    public boolean updateUserRole(UserRole role) {
        return false;
    }

    public UserRole getUserRoleByToken(String token) {
        return null;
    }

    public List<UserRole> findUserByParam(Map<String, Object> params) {
        return null;
    }

    Long findUserByParamCount(Map<String, Object> params) {
        return null;
    }
}
