package com.alipapa.smp.common;

import com.alipapa.smp.common.request.UserInfo;
import com.alipapa.smp.common.request.UserStatus;
import com.alipapa.smp.exception.ServiceException;
import com.alipapa.smp.user.pojo.UserRole;
import com.alipapa.smp.user.service.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 登录认证的拦截器
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    private UserRoleService userRoleService;

    /**
     * Handler执行之前调用这个方法
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (userRoleService == null) {
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            userRoleService = (UserRoleService) factory.getBean("userRoleService");
        }


        if (request.getRequestURI().indexOf("login") >= 0 || request.getRequestURI().indexOf("listRole") >= 0) {
            return true;
        }
        String token = request.getParameter("token");
        String uuid = request.getParameter("uuid");
        String userRoleId = request.getParameter("userRoleId");
        //1：token为空
        if (token == null) {
            response.setStatus(500);
            throw new ServiceException("token校验失败，请重新登录！");
        }
        //2：token不存在
        UserRole userRole = userRoleService.getUserRoleByToken(token);
        if (userRole == null) {
            response.setStatus(500);
            throw new ServiceException("token校验失败，请重新登录！");
        }
        //3：信息不正确
        if (!uuid.equals(userRole.getUuid()) || !userRoleId.equals(String.valueOf(userRole.getId()))) {
            response.setStatus(500);
            throw new ServiceException("token校验失败，请重新登录！");
        }

        //4：token过期
        if (userRole.getExpireTime().before(new Date())) {
            response.setStatus(500);
            throw new ServiceException("token校验失败，请重新登录！");
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setToken(token);
        userInfo.setRoleName(userRole.getRoleName());
        userInfo.setUserId(userRole.getUserId());
        userInfo.setUserNo(userRole.getUserNo());
        userInfo.setUuid(userRole.getUuid());
        userInfo.setUserRoleId(userRole.getId());
        userInfo.setRoleId(userRole.getRoleId());
        UserStatus.setUserInfo(userInfo);
        return super.preHandle(request, response, handler);
    }

}
