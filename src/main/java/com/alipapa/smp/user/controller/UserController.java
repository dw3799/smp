package com.alipapa.smp.user.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipapa.smp.common.request.UserInfo;
import com.alipapa.smp.common.request.UserStatus;
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
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.alipapa.smp.utils.WebApiResponse.error;

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
            return error("参数不可以为空");
        }
        User user = userService.getUserByUserName(name);
        if (user == null) {
            return error("用户名或密码错误");
        }

        Role role = roleService.getRoleByName(roleName);
        if (role == null) {
            return error("角色不存在");
        }

        UserRole userRole = userRoleService.getUserRoleByUserIdAndRoleId(user.getId(), role.getId());
        if (userRole == null) {
            return error("用户角色不存在");
        }

        if (!user.getPwd().equals(MD5.digist(pwd))) {
            return error("用户名或密码错误");
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
            return error("登录异常");
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
            return error("参数不可以为空");
        }
        User user = userService.getUserByUserName(name);

        if (user == null) {
            return error("用户名不存在");
        }

        List<UserRole> userRoleList = userRoleService.listRoleByUserId(user.getId());
        if (CollectionUtils.isEmpty(userRoleList)) {
            return error("请联系管理员设置角色");
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
    public WebApiResponse<String> addUser(@RequestBody String jsonStr, HttpServletRequest request) {
        UserInfo userInfo = UserStatus.getUserInfo();

        if (jsonStr == null) {
            logger.error("提交的json格式数据不可以为空!");
            return error("输入的信息不可以为空");
        }
        try {
            JSONObject json = JSON.parseObject(jsonStr);
            if (json == null) {
                logger.error("客户提交的数据解析失败: " + jsonStr);
                return error("用户数据解析失败");
            }


            String cnName = json.getString("cnName");
            String groupId = json.getString("groupId");
            JSONArray roleIds = json.getJSONArray("roleIds");
            String remark = json.getString("remark");

            if (cnName == null || roleIds == null) {
                return error("请输入必要信息");
            }

            if (userService.getUserByCnName(cnName) == null) {
                logger.error("员工姓名已存在: " + cnName);
                return error("员工姓名已存在");
            }

            User newUser = new User();
            newUser.setName(new Date().getYear() + "001");
            newUser.setCnName(cnName);
            newUser.setCreateUser(userInfo.getUserNo());
            newUser.setUserNo(UUID.randomUUID().toString());
            newUser.setPwd(MD5.digist("666666"));
            newUser.setRemark(remark);
            
            return WebApiResponse.success("ok");
        } catch (Exception ex) {
            return error("新建用户失败");
        }
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
            return error("参数不可以为空");
        }
        User user = userService.getUserByUserName(name);

        if (user == null) {
            return WebApiResponse.error("用户名不存在");
        }

        List<UserRole> userRoleList = userRoleService.listRoleByUserId(user.getId());
        if (CollectionUtils.isEmpty(userRoleList)) {
            return error("请联系管理员设置角色");
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
            return error("参数不可以为空");
        }
        User user = userService.getUserByUserName(name);

        if (user == null) {
            return error("用户名不存在");
        }

        List<UserRole> userRoleList = userRoleService.listRoleByUserId(user.getId());
        if (CollectionUtils.isEmpty(userRoleList)) {
            return error("请联系管理员设置角色");
        }

        List<String> roles = new ArrayList<>();
        for (UserRole userRole : userRoleList) {
            roles.add(userRole.getRoleName());
        }
        return WebApiResponse.success("");
    }


}