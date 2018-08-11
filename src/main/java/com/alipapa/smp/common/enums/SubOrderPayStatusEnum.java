package com.alipapa.smp.common.enums;

/**
 * 产品订单支付状态枚举
 */
public enum SubOrderPayStatusEnum {

    UN_PAY(0, "UN_PAY", "未支付"),

    SUB_FRONT_PAY(1, "FRONT_PAY", "已支付定金"),

    FIN_TAIL_APV(3, "FIN_TAIL_APV", "待财务审核采购尾款"),

    CASH_TAIL_PAYING(3, "CASH_TAIL_PAYING", "待出纳支付采购尾款"),

    SUB_TAIL_PAYED(4, "TAIL_PAYED", "尾款支付完成"),

    SUCCESS(5, "SUCCESS", "支付成功");


    SubOrderPayStatusEnum(int code, String codeName, String dec) {
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

    public static SubOrderPayStatusEnum valueOf(int code) {
        SubOrderPayStatusEnum[] consumerScopeEnums = SubOrderPayStatusEnum.values();
        for (SubOrderPayStatusEnum consumerScopeEnum : consumerScopeEnums) {
            if (consumerScopeEnum.getCode() == code) {
                return consumerScopeEnum;
            }
        }
        return null;
    }
}
