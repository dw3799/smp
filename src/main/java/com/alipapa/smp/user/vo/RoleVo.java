package com.alipapa.smp.user.vo;


public class RoleVo {

    //业务员	业务主管	财务人员	出纳人员	采购人员	仓储人员	单证	管理员

    private Long id;

    private String roleName;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}