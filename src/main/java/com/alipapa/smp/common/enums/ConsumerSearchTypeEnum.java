package com.alipapa.smp.common.enums;

/**
 * 客户查询类型
 */
public enum ConsumerSearchTypeEnum {
    Potential(1, "Potential", "潜在客户"),

    Deal(2, "Deal", "成交客户"),

    MyConsumer(3, "MyConsumer", "我的客户"),

    PublicPool(4, "PublicPool", "资源池/抢客户");


    ConsumerSearchTypeEnum(int code, String codeName, String dec) {
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

    public static ConsumerSearchTypeEnum valueOf(int code) {
        ConsumerSearchTypeEnum[] consumerScopeEnums = ConsumerSearchTypeEnum.values();
        for (ConsumerSearchTypeEnum consumerSearchTypeEnum : consumerScopeEnums) {
            if (consumerSearchTypeEnum.getCode() == code) {
                return consumerSearchTypeEnum;
            }
        }
        return null;
    }
}
