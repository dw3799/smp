package com.alipapa.smp.permission.service;

import com.alipapa.smp.permission.mapper.PermissionItemMapper;
import com.alipapa.smp.permission.mapper.RolePermissionMapper;
import com.alipapa.smp.permission.pojo.RolePermission;
import com.alipapa.smp.permission.pojo.RolePermissionExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionItemMapper permissionItemMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    public List<RolePermission> listRolePermissionByRoleId(Long roleId) {
        if (roleId == null) {
            return null;
        }
        RolePermissionExample example = new RolePermissionExample();
        RolePermissionExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        return rolePermissionMapper.selectByExample(example);
    }


}
