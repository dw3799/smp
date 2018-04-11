package com.alipapa.smp.permission.controller;

import com.alipapa.smp.common.request.UserInfo;
import com.alipapa.smp.common.request.UserStatus;
import com.alipapa.smp.permission.pojo.PermissionItem;
import com.alipapa.smp.permission.pojo.RolePermission;
import com.alipapa.smp.permission.service.PermissionService;
import com.alipapa.smp.permission.vo.PermissionItemVo;
import com.alipapa.smp.utils.WebApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 权限管理接口
 *
 * @author liuwei
 * @version 1.0
 * 2018-03-20
 */

@RestController
@RequestMapping("/api/perms")
public class PermissionController {
    private static Logger logger = LoggerFactory.getLogger(PermissionController.class);

    private static final String LevelKey = "LevelKey";
    @Autowired
    private PermissionService permissionService;


    /**
     * @param
     * @return
     */
    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public WebApiResponse<List<PermissionItemVo>> listItems() {
        UserInfo userInfo = UserStatus.getUserInfo();
        Long roleId = userInfo.getRoleId();

/*
        //管理员返回全部权限
        if ("admin".equals(userInfo.getRoleName())) {
            List<PermissionItem> permissionItemList = permissionService.listMainPermissionItem();
            List<PermissionItemVo> permissionItemVoList = new ArrayList<>();

            for (PermissionItem permissionItem : permissionItemList) {
                PermissionItemVo permissionItemVo = new PermissionItemVo();
                permissionItemVo.setId(permissionItem.getId());
                permissionItemVo.setIcon(permissionItem.getIcon());
                permissionItemVo.setName(permissionItem.getName());
                permissionItemVo.setUrl(permissionItem.getUrl());

                List<PermissionItem> childList = permissionService.listPermissionItemByParentId(permissionItem.getId());
                if (!CollectionUtils.isEmpty(childList)) {
                    List<PermissionItemVo> childVoList = new ArrayList<>();
                    for (PermissionItem child : childList) {
                        PermissionItemVo childVo = new PermissionItemVo();
                        childVo.setId(child.getId());
                        childVo.setIcon(child.getIcon());
                        childVo.setName(child.getName());
                        childVo.setUrl(child.getUrl());
                        childVoList.add(childVo);
                    }

                    permissionItemVo.setChilds(childVoList);
                }
                permissionItemVoList.add(permissionItemVo);
            }
            return WebApiResponse.success(permissionItemVoList);
        }
*/
        //其他的查看权限树
        List<RolePermission> rolePermissionList = permissionService.listRolePermissionByRoleId(roleId);
        if (!CollectionUtils.isEmpty(rolePermissionList)) {
            List<PermissionItem> mainPermissionItemList = new ArrayList<>();

            HashMap<String, PermissionItem> level2Map = new HashMap();
            for (RolePermission rolePermission : rolePermissionList) {
                PermissionItem permissionItem = permissionService.getPermissionItemById(rolePermission.getPermissionId());
                if (permissionItem != null && permissionItem.getLevel() != null && permissionItem.getLevel() == 1) {
                    mainPermissionItemList.add(permissionItem);
                } else if (permissionItem != null && permissionItem.getLevel() != null && permissionItem.getLevel() == 2) {
                    level2Map.put(LevelKey + permissionItem.getId(), permissionItem);
                }
            }

            List<PermissionItemVo> permissionItemVoList = new ArrayList<>();

            for (PermissionItem permissionItem : mainPermissionItemList) {
                PermissionItemVo permissionItemVo = new PermissionItemVo();
                permissionItemVo.setId(permissionItem.getId());
                permissionItemVo.setIcon(permissionItem.getIcon());
                permissionItemVo.setName(permissionItem.getName());
                permissionItemVo.setUrl(permissionItem.getUrl());

                List<PermissionItem> childList = permissionService.listPermissionItemByParentId(permissionItem.getId());
                if (!CollectionUtils.isEmpty(childList)) {
                    List<PermissionItemVo> childVoList = new ArrayList<>();
                    for (PermissionItem child : childList) {
                        if (level2Map.get(LevelKey + child.getId()) != null) {
                            PermissionItemVo childVo = new PermissionItemVo();
                            childVo.setId(child.getId());
                            childVo.setIcon(child.getIcon());
                            childVo.setName(child.getName());
                            childVo.setUrl(child.getUrl());
                            childVoList.add(childVo);
                        }
                    }
                    permissionItemVo.setChilds(childVoList);
                }
                permissionItemVoList.add(permissionItemVo);
            }

            return WebApiResponse.success(permissionItemVoList);
        }


        return null;
    }

}
