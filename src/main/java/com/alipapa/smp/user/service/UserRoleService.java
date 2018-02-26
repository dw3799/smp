package com.alipapa.smp.user.service;

import com.alipapa.smp.user.pojo.UserRole;

import java.util.List;

public interface UserRoleService {

    List<UserRole> listRoleByUserId(Long userId);

}
