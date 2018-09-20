package com.alipapa.smp.common.enums;

/**
 * 订单状态枚举
 */
public enum SubOrderStatusEnum {

    CREATE(0, "CREATE", "已创建"),

    BUYER_ORDER(1, "BUYER_ORDER", "待采购下单"),

    SPR_BUYER_APV(2, "SPR_BUYER_APV", "待采购主管审核采购订单"),

    SUB_FIN_FRONT_APV(3, "SUB_FIN_FRONT_APV", "待财务审核采购定金"),

    SUB_CASH_FRONT_APV(4, "SUB_CASH_FRONT_APV", "待出纳支付采购定金"),

    BUYER_FOLLOW_ORDER(5, "BUYER_FOLLOW_ORDER", "待采购补充跟单状态"),

    QUALITY_CHECk(6, "QUALITY_CHECk", "待质检"),

    INVOICE_APPLY(7, "INVOICE_APPLY", "待提交发货申请"),

    FIN_INVOICE_APV(8, "FIN_INVOICE_APV", "待财务审核发货申请"),

    INVOICE_DELIVER(9, "INVOICE_DELIVER", "待寻找运输渠道"),

    READY_OUT(10, "READY_OUT", "待出库"),

    COMPLETE(11, "COMPLETE", "订单完成"),

    CLOSE(12, "CLOSE", "订单关闭");

    /*
待采购下单
待采购主管审核采购订单
待财务审核采购定金
待出纳支付采购定金
待采购补充跟单状态
工厂生产中





待质检
待提交发货申请
待财务审核发货申请
待寻找运输渠道
待出库


待财务审核采购尾款
待出纳支付采购尾款
订单完成
订单关闭

    */

    SubOrderStatusEnum(int code, String codeName, String dec) {
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

    public static SubOrderStatusEnum valueOf(int code) {
        SubOrderStatusEnum[] consumerScopeEnums = SubOrderStatusEnum.values();
        for (SubOrderStatusEnum consumerScopeEnum : consumerScopeEnums) {
            if (consumerScopeEnum.getCode() == code) {
                return consumerScopeEnum;
            }
        }
        return null;
    }
}
