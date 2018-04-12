package com.alipapa.smp.common.enums;

/**
 * 客户作用域
 */
public enum ConsumerScopeEnum {
    Private(1, "Private", "私有"),

    Protected(2, "Protected", "多人持有"),

    Public(3, "Public", "资源池");

    ConsumerScopeEnum(int code, String codeName, String dec) {
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

    public static ConsumerScopeEnum valueOf(int code) {
        ConsumerScopeEnum[] consumerScopeEnums = ConsumerScopeEnum.values();
        for (ConsumerScopeEnum consumerScopeEnum : consumerScopeEnums) {
            if (consumerScopeEnum.getCode() == code) {
                return consumerScopeEnum;
            }
        }
        return null;
    }
}
