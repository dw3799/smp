package com.alipapa.smp.common.enums;

/**
 * 订单状态枚举
 */
public enum OrderStatusEnum {

    CREATE(0, "CREATE", "已创建"),

    UN_SUBMIT(1, "UN_SUBMIT", "待提交订单申请"),

    SPR_APV(2, "SPR_APV", "业务主管审批中"),

    CASH_FRONT_APV(3, "CASH_FRONT_APV", "出纳审核定金中"),

    FIN_FRONT_APV(4, "FIN_FRONT_APV", "财务审核定金中"),

    DELIVERY(5, "DELIVERY", "订单交付中"),

    COMPLETE(6, "COMPLETE", "订单完成"),

    CLOSE(7, "CLOSE", "订单关闭");

    /*
    待提交订单申请
            待业务主管审批
    待客户缴纳定金
            待出纳审核定金
    待财务审核定金
            订单交付中

    待客户支付尾款
            待出纳审核尾款
    待支付采购尾款

            订单完成
    订单关闭
    */

    OrderStatusEnum(int code, String codeName, String dec) {
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

    public static OrderStatusEnum valueOf(int code) {
        OrderStatusEnum[] consumerScopeEnums = OrderStatusEnum.values();
        for (OrderStatusEnum consumerScopeEnum : consumerScopeEnums) {
            if (consumerScopeEnum.getCode() == code) {
                return consumerScopeEnum;
            }
        }
        return null;
    }
}
