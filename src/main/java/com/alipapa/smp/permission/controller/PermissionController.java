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

        //管理员返回全部权限
        if ("admin".equals(userInfo.getRoleName())) {

            return null;
        }


        //其他的查看权限树
        List<RolePermission> rolePermissionList = permissionService.listRolePermissionByRoleId(roleId);
        if (!CollectionUtils.isEmpty(rolePermissionList)) {
            HashMap<String, PermissionItem> level1Map = new HashMap();
            HashMap<String, PermissionItem> level2Map = new HashMap();
            for (RolePermission rolePermission : rolePermissionList) {


            }
        }

        return null;
    }

}
