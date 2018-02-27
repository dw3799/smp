package com.alipapa.smp.user.controller;

import com.alipapa.smp.stock.pojo.Person;
import com.alipapa.smp.stock.pojo.StockManage;
import com.alipapa.smp.stock.service.StockManageService;
import com.alipapa.smp.user.pojo.Role;
import com.alipapa.smp.user.pojo.User;
import com.alipapa.smp.user.pojo.UserRole;
import com.alipapa.smp.user.service.RoleService;
import com.alipapa.smp.user.service.UserRoleService;
import com.alipapa.smp.user.service.UserService;
import com.alipapa.smp.user.vo.LoginInfo;
import com.alipapa.smp.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 用户管理接口
 *
 * @author liuwei
 * @version 1.0
 * 2018-02-23
 */

@Controller
@RequestMapping("/api")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    /**
     * 登录接口
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/v1/login", method = RequestMethod.POST)
    public WebApiResponse<LoginInfo> userLogin(@RequestParam("name") String name, @RequestParam("pwd") String pwd, @RequestParam("roleName") String roleName) {
        if (StringUtils.isBlank(name) || StringUtils.isBlank(pwd) || StringUtils.isBlank(roleName)) {
            logger.error("参数不能为空!");
            return WebApiResponse.error("参数不可以为空");
        }
        User user = userService.getUserByUserName(name);
        if (user == null) {
            return WebApiResponse.error("用户名或密码错误");
        }

        Role role = roleService.getRoleByName(roleName);
        if (role == null) {
            return WebApiResponse.error("角色不存在");
        }

        UserRole userRole = userRoleService.getUserRoleByUserIdAndRoleId(user.getId(), role.getId());
        if (userRole == null) {
            return WebApiResponse.error("用户角色不存在");
        }

        //if (!user.getPwd().equals(pwd)) {
        if (!user.getPwd().equals(MD5.digist(pwd))) {
            return WebApiResponse.error("用户名或密码错误");
        }

        Map<String, Object> srcData = new HashMap<>();
        srcData.put("user", user.getId());
        srcData.put("role", role.getId());
        srcData.put("userRole", userRole.getId());
        try {
            String token = SecurityUtil.authentication(srcData);
            userRole.setToken(token);
            userRole.setExpireTime(DateUtil.addDays(1));
            userRoleService.updateUserRole(userRole);

            LoginInfo loginInfo = new LoginInfo();
            loginInfo.setToken(token);
            loginInfo.setUserNo(user.getUserNo());
            loginInfo.setRoleName(roleName);
            return WebApiResponse.success(loginInfo);
        } catch (Exception e) {
            logger.error("登录异常", e);
            return WebApiResponse.error("登录异常");
        }
    }


    /**
     * 获取人员角色
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/v1/listRole", method = RequestMethod.POST)
    public WebApiResponse<List<String>> listRole(@RequestParam("name") String name) {
        if (StringUtils.isBlank(name)) {
            logger.error("参数不能为空!");
            return WebApiResponse.error("参数不可以为空");
        }
        User user = userService.getUserByUserName(name);

        if (user == null) {
            return WebApiResponse.error("用户名不存在");
        }

        List<UserRole> userRoleList = userRoleService.listRoleByUserId(user.getId());
        if (CollectionUtils.isEmpty(userRoleList)) {
            return WebApiResponse.error("请联系管理员设置角色");
        }

        List<String> roles = new ArrayList<>();
        for (UserRole userRole : userRoleList) {
            roles.add(userRole.getRoleName());
        }
        return WebApiResponse.success(roles);
    }


    /**
     * 新建用户
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/v1/addUser", method = RequestMethod.POST)
    public WebApiResponse<String> addUser(@RequestParam("name") String name) {
        if (StringUtils.isBlank(name)) {
            logger.error("参数不能为空!");
            return WebApiResponse.error("参数不可以为空");
        }
        User user = userService.getUserByUserName(name);

        if (user == null) {
            return WebApiResponse.error("用户名不存在");
        }

        List<UserRole> userRoleList = userRoleService.listRoleByUserId(user.getId());
        if (CollectionUtils.isEmpty(userRoleList)) {
            return WebApiResponse.error("请联系管理员设置角色");
        }

        List<String> roles = new ArrayList<>();
        for (UserRole userRole : userRoleList) {
            roles.add(userRole.getRoleName());
        }
        return WebApiResponse.success("");
    }


    /**
     * 新建组
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/v1/addGroup", method = RequestMethod.POST)
    public WebApiResponse<String> addGroup(@RequestParam("name") String name) {
        if (StringUtils.isBlank(name)) {
            logger.error("参数不能为空!");
            return WebApiResponse.error("参数不可以为空");
        }
        User user = userService.getUserByUserName(name);

        if (user == null) {
            return WebApiResponse.error("用户名不存在");
        }

        List<UserRole> userRoleList = userRoleService.listRoleByUserId(user.getId());
        if (CollectionUtils.isEmpty(userRoleList)) {
            return WebApiResponse.error("请联系管理员设置角色");
        }

        List<String> roles = new ArrayList<>();
        for (UserRole userRole : userRoleList) {
            roles.add(userRole.getRoleName());
        }
        return WebApiResponse.success("");
    }


    /**
     * 用户查询
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/v1/listGroupUser", method = RequestMethod.POST)
    public WebApiResponse<String> listGroupUser(@RequestParam("name") String name) {
        if (StringUtils.isBlank(name)) {
            logger.error("参数不能为空!");
            return WebApiResponse.error("参数不可以为空");
        }
        User user = userService.getUserByUserName(name);

        if (user == null) {
            return WebApiResponse.error("用户名不存在");
        }

        List<UserRole> userRoleList = userRoleService.listRoleByUserId(user.getId());
        if (CollectionUtils.isEmpty(userRoleList)) {
            return WebApiResponse.error("请联系管理员设置角色");
        }

        List<String> roles = new ArrayList<>();
        for (UserRole userRole : userRoleList) {
            roles.add(userRole.getRoleName());
        }
        return WebApiResponse.success("");
    }


}