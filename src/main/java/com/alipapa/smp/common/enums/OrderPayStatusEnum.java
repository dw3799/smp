package com.alipapa.smp.common.enums;

/**
 * 订单支付状态枚举
 */
public enum OrderPayStatusEnum {

    UN_PAY(0, "UN_PAY", "未支付"),

    FRONT_PAY_APPROVE(1, "FRONT_PAY_APPROVE", "支付定金审核中"),

    FRONT_PAY(2, "FRONT_PAY", "已支付定金"),

    TAIL_PAYING(3, "TAIL_PAYING", "尾款支付中"),

    TAIL_CASH_APV(4, "TAIL_CASH_APV", "出纳确认尾款中"),

    TAIL_PAYED(5, "TAIL_PAYED", "尾款支付完成"),

    SUCCESS(6, "SUCCESS", "支付成功"),

    TAIL_PAY_FAILED(7, "TAIL_PAY_FAILED", "尾款支付失败");

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
