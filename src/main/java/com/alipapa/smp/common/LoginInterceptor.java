package com.alipapa.smp.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alipapa.smp.user.controller.UserController;
import com.alipapa.smp.user.pojo.UserRole;
import com.alipapa.smp.user.service.UserRoleService;
import com.alipapa.smp.utils.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Enumeration;


/**
 * 登录认证的拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Autowired
    private UserRoleService userRoleService;


    /**
     * Handler执行完成之后调用这个方法
     */
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception exc)
            throws Exception {

    }

    /**
     * Handler执行之后，ModelAndView返回之前调用这个方法
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
    }

    /**
     * Handler执行之前调用这个方法
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {


        if (request.getRequestURI().indexOf("login") >= 0 || request.getRequestURI().indexOf("listRole") >= 0) {
            return true;
        }
        String token = request.getHeader("token");
        String userNo = request.getHeader("userNo");
        String roleName = request.getHeader("roleName");
        //1：token为空
        if (token == null) {
            response.setStatus(500);
            return false;
        }
        //2：token不存在
        UserRole role = userRoleService.getUserRoleByToken(token);
        if (role == null) {
            response.setStatus(500);
            return false;
        }
        //3：信息不正确
        if (!userNo.equals(role.getUserNo()) || !roleName.equals(role.getRoleName())) {
            response.setStatus(500);
            return false;
        }

        //4：token过期
        if (role.getExpireTime().before(new Date())) {
            response.setStatus(500);
            return false;
        }
        return true;
    }

}
