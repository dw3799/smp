package com.alipapa.smp.common.request;

/**
 * Description: UserStatus
 * Author: liuwei
 * date: 2018-03-05
 */
public class UserStatus {

    public static ThreadLocal<UserInfo> userInfo = new ThreadLocal<UserInfo>();

    /**
     * 获取本次访问的用户id
     *
     * @return
     */
    public static UserInfo getUserInfo() {
        return userInfo.get();
    }

    public static void setUserInfo(UserInfo data) {
        userInfo.set(data);
    }

}
