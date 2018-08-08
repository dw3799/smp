package com.alipapa.smp.common.enums;

/**
 * 订单支付状态枚举
 */
public enum OrderPayStatusEnum {

    UN_PAY(0, "UN_PAY", "未支付"),

    FRONT_PAY(1, "FRONT_PAY", "已支付定金"),

    TAIL_PAYING(3, "TAIL_PAYING", "尾款支付中"),

    TAIL_PAYED(4, "TAIL_PAYED", "尾款支付完成"),

    SUCCESS(5, "SUCCESS", "支付成功");

    OrderPayStatusEnum(int code, String codeName, String dec) {
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

    public static OrderPayStatusEnum valueOf(int code) {
        OrderPayStatusEnum[] consumerScopeEnums = OrderPayStatusEnum.values();
        for (OrderPayStatusEnum consumerScopeEnum : consumerScopeEnums) {
            if (consumerScopeEnum.getCode() == code) {
                return consumerScopeEnum;
            }
        }
        return null;
    }
}
