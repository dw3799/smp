package com.alipapa.smp.common.enums;

public enum SysDictManageEnum {

    Currency(1, "Currency", "币种"),

    PayChannel(2, "PayChannel", "支付渠道"),

    TransPort(3, "TransPort", "运输方式"),

    ConsumerSource(4, "ConsumerSource", "客户来源"),

    ConsumerType(5, "ConsumerType", "客户类型"),

    ConsumerCountry(6, "ConsumerCountry", "国籍");


    SysDictManageEnum(int code, String codeName, String dec) {
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

    public static SysDictManageEnum valueOf(int code) {
        SysDictManageEnum[] categoryCodes = SysDictManageEnum.values();
        for (SysDictManageEnum categoryCode : categoryCodes) {
            if (categoryCode.getCode() == code) {
                return categoryCode;
            }
        }
        return null;
    }


}
