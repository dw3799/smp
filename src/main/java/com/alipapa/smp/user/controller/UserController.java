package com.alipapa.smp.user.controller;

import com.alipapa.smp.stock.pojo.Person;
import com.alipapa.smp.stock.pojo.StockManage;
import com.alipapa.smp.stock.service.StockManageService;
import com.alipapa.smp.user.pojo.User;
import com.alipapa.smp.user.pojo.UserRole;
import com.alipapa.smp.user.service.UserRoleService;
import com.alipapa.smp.user.service.UserService;
import com.alipapa.smp.user.vo.LoginInfo;
import com.alipapa.smp.utils.StringUtil;
import com.alipapa.smp.utils.WebApiResponse;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setToken("");
        loginInfo.setUserNo("");

        return WebApiResponse.success(loginInfo);
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

}