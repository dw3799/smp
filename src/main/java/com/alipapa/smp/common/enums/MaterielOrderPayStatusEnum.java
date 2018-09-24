package com.alipapa.smp.common.enums;

/**
 * 产品订单支付状态枚举
 */
public enum MaterielOrderPayStatusEnum {

    UN_PAY(0, "UN_PAY", "未支付"),

    SUB_FIN_FRONT_APV(1, "SUB_FIN_FRONT_APV", "待财务审核采购定金"),

    SUB_CASH_FRONT_APV(2, "SUB_CASH_FRONT_APV", "待出纳支付采购定金"),

    SUB_FRONT_PAY(3, "FRONT_PAY", "已支付定金"),

    FIN_TAIL_APV(4, "FIN_TAIL_APV", "待财务审核采购尾款"),

    CASH_TAIL_PAYING(5, "CASH_TAIL_PAYING", "待出纳支付采购尾款"),

    SUB_TAIL_PAYED(6, "TAIL_PAYED", "尾款支付完成"),

    SUCCESS(7, "SUCCESS", "支付成功");


    MaterielOrderPayStatusEnum(int code, String codeName, String dec) {
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

    public static MaterielOrderPayStatusEnum valueOf(int code) {
        MaterielOrderPayStatusEnum[] consumerScopeEnums = MaterielOrderPayStatusEnum.values();
        for (MaterielOrderPayStatusEnum consumerScopeEnum : consumerScopeEnums) {
            if (consumerScopeEnum.getCode() == code) {
                return consumerScopeEnum;
            }
        }
        return null;
    }
}
