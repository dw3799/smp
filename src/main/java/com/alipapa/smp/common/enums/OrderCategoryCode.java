package com.alipapa.smp.common.enums;

/**
 * 活动类型
 */
public enum OrderCategoryCode {
    Currency(1, "Currency", "币种"),

    OrderType(2, "OrderType", "订单类型");

    
    OrderCategoryCode(int code, String codeName, String dec) {
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

    public static OrderCategoryCode valueOf(int code) {
        OrderCategoryCode[] categoryCodes = OrderCategoryCode.values();
        for (OrderCategoryCode categoryCode : categoryCodes) {
            if (categoryCode.getCode() == code) {
                return categoryCode;
            }
        }
        return null;
    }
}
