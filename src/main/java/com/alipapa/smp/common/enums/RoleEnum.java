package com.alipapa.smp.common.enums;

/**
 * 角色枚举
 */
public enum RoleEnum {
    saler(1, "saler", "业务员"),

    supervisor(2, "supervisor", "业务主管"),

    financial(3, "financial", "财务人员"),

    cashier(4, "cashier", "出纳人员"),

    selfBuyer(5, "selfBuyer", "自营采购人员"),

    storage(6, "storage", "仓储人员"),

    documentation(7, "documentation", "单证"),

    admin(8, "admin", "管理员"),

    agentBuyer(9, "agentBuyer", "代理采购员"),

    superBuyer(10, "superBuyer", "采购主管");

    RoleEnum(int code, String codeName, String dec) {
        this.code = code;
        this.codeName = codeName;
        this.dec = dec;
    }

    private int code;

    private String codeName;

    private String dec;

    public int getCode() {
        return code;
    }

    public String getCodeName() {
        return codeName;
    }

    public String getDec() {
        return dec;
    }

    public static RoleEnum valueOf(int code) {
        RoleEnum[] roleEnums = RoleEnum.values();
        for (RoleEnum roleEnum : roleEnums) {
            if (roleEnum.getCode() == code) {
                return roleEnum;
            }
        }
        return null;
    }
}
