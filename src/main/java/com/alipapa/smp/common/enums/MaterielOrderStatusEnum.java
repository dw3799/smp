package com.alipapa.smp.common.enums;

/**
 * 订单状态枚举
 */
public enum MaterielOrderStatusEnum {

    CREATE(0, "CREATE", "已创建"),

    BUYER_ORDER(1, "BUYER_ORDER", "待采购下单"),

    SPR_BUYER_APV(2, "SPR_BUYER_APV", "待采购主管审核采购订单"),

    SUB_FIN_FRONT_APV(3, "SUB_FIN_FRONT_APV", "待财务审核采购定金"),

    SUB_CASH_FRONT_APV(4, "SUB_CASH_FRONT_APV", "待出纳支付采购定金"),

    BUYER_FOLLOW_ORDER(5, "BUYER_FOLLOW_ORDER", "待采购补充跟单状态"),

    FACTORY_ORDERED(6, "FACTORY_ORDERED", "工厂生产中——已下单"),

    FACTORY_MATERIEL(7, "FACTORY_MATERIEL", "工厂生产中——已下料"),

    FACTORY_PRINTING(8, "FACTORY_PRINTING", "工厂生产中——已印刷"),

    FACTORY_PACKAGE(9, "FACTORY_PACKAGE", "工厂生产中——已车缝"),

    FACTORY_INVOICE(10, "FACTORY_INVOICE", "工厂生产中——已发货"),

    DISCARDED(11, "DISCARDED", "作废");

    MaterielOrderStatusEnum(int code, String codeName, String dec) {
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

    public static MaterielOrderStatusEnum valueOf(int code) {
        MaterielOrderStatusEnum[] consumerScopeEnums = MaterielOrderStatusEnum.values();
        for (MaterielOrderStatusEnum consumerScopeEnum : consumerScopeEnums) {
            if (consumerScopeEnum.getCode() == code) {
                return consumerScopeEnum;
            }
        }
        return null;
    }
}
