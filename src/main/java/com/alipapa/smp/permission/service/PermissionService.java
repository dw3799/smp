package com.alipapa.smp.permission.service;

import com.alipapa.smp.permission.mapper.PermissionItemMapper;
import com.alipapa.smp.permission.mapper.RolePermissionMapper;
import com.alipapa.smp.permission.pojo.PermissionItem;
import com.alipapa.smp.permission.pojo.PermissionItemExample;
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

    /**
     * @param roleId
     * @return
     */
    public List<RolePermission> listRolePermissionByRoleId(Long roleId) {
        if (roleId == null) {
            return null;
        }
        RolePermissionExample example = new RolePermissionExample();
        RolePermissionExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        return rolePermissionMapper.selectByExample(example);
    }

    /**
     * @param id
     * @return
     */
    public PermissionItem getPermissionItemById(Long id) {
        return permissionItemMapper.selectByPrimaryKey(id);
    }


    /**
     * 获取第一层权限树
     *
     * @return
     */
    public List<PermissionItem> listMainPermissionItem() {
        PermissionItemExample example = new PermissionItemExample();
        PermissionItemExample.Criteria criteria = example.createCriteria();
        criteria.andLevelEqualTo(1);
        example.setOrderByClause("sort");
        return permissionItemMapper.selectByExample(example);
    }


    /**
     * 获取下层权限树
     *
     * @return
     */
    public List<PermissionItem> listPermissionItemByParentId(Long parentId) {
        PermissionItemExample example = new PermissionItemExample();
        PermissionItemExample.Criteria criteria = example.createCriteria();
        criteria.andLevelEqualTo(2);
        criteria.andParentIdEqualTo(parentId);

        example.setOrderByClause("sort");
        return permissionItemMapper.selectByExample(example);
    }

}