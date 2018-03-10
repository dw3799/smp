package com.alipapa.smp.user.vo;

/**
 * 模糊查询
 */
public class FuzzyUserVo {
    private Long userId;

    private String userNo;//用户编号

    private String name;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
