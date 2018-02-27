package com.alipapa.smp.user.service;

import com.alipapa.smp.user.pojo.UserRole;

import java.util.List;

public interface UserRoleService {

    List<UserRole> listRoleByUserId(Long userId);

    UserRole getUserRoleByUserIdAndRoleId(Long userId, Long roleId);

    boolean updateUserRole(UserRole role);

}
