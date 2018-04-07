package com.alipapa.smp.common.enums;

/**
 * 活动类型
 */
public enum CategoryCode {

    Source(1, "ConsumerSource", "客户来源"),

    Type(2, "ConsumerType", "客户类型"),

    country(3, "ConsumerCountry", "客户国籍"),

    level(4, "ConsumerLevel", "客户等级"),

    order(5, "ConsumerOrder", "是否有订单"),

    intention(6, "ConsumerIntention", "客户意向");
    
    CategoryCode(int code, String codeName, String dec) {
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

    public static CategoryCode valueOf(int code) {
        CategoryCode[] categoryCodes = CategoryCode.values();
        for (CategoryCode categoryCode : categoryCodes) {
            if (categoryCode.getCode() == code) {
                return categoryCode;
            }
        }
        return null;
    }
}
