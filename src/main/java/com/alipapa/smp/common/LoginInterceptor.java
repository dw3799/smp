package com.alipapa.smp.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alipapa.smp.common.request.UserInfo;
import com.alipapa.smp.common.request.UserStatus;
import com.alipapa.smp.exception.ServiceException;
import com.alipapa.smp.user.pojo.UserRole;
import com.alipapa.smp.user.service.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.util.Date;

/**
 * 登录认证的拦截器
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Autowired
    private UserRoleService userRoleService;

    /**
     * Handler执行之前调用这个方法
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getRequestURI().indexOf("login") >= 0 || request.getRequestURI().indexOf("listRole") >= 0) {
            return true;
        }
        String token = request.getHeader("token");
        String uuid = request.getHeader("uuid");
        String roleName = request.getHeader("roleName");
        //1：token为空
        if (token == null) {
            response.setStatus(500);
            throw new ServiceException("token校验失败，请重新登录！");
        }
        //2：token不存在
        UserRole role = userRoleService.getUserRoleByToken(token);
        if (role == null) {
            response.setStatus(500);
            throw new ServiceException("token校验失败，请重新登录！");
        }
        //3：信息不正确
        if (!uuid.equals(role.getUuid()) || !roleName.equals(role.getRoleName())) {
            response.setStatus(500);
            throw new ServiceException("token校验失败，请重新登录！");
        }

        //4：token过期
        if (role.getExpireTime().before(new Date())) {
            response.setStatus(500);
            throw new ServiceException("token校验失败，请重新登录！");
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setToken(token);
        userInfo.setRoleName(roleName);
        userInfo.setUserId(role.getUserId());
        userInfo.setUserNo(role.getUserNo());
        userInfo.setUuid(role.getUuid());
        userInfo.setUserRoleId(role.getId());
        UserStatus.setUserInfo(userInfo);
        return super.preHandle(request, response, handler);
    }

}
