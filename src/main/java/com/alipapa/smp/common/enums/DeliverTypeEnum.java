package com.alipapa.smp.common.enums;

/**
 * 运输方式枚举
 */
public enum DeliverTypeEnum {

    Sea(0, "Sea", "海运"),

    Air(1, "Air", "空运"),

    Grand(2, "Grand", "陆运");

    DeliverTypeEnum(int code, String codeName, String dec) {
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

    public static DeliverTypeEnum valueOf(int code) {
        DeliverTypeEnum[] consumerScopeEnums = DeliverTypeEnum.values();
        for (DeliverTypeEnum consumerScopeEnum : consumerScopeEnums) {
            if (consumerScopeEnum.getCode() == code) {
                return consumerScopeEnum;
            }
        }
        return null;
    }
}
