package com.alipapa.smp.common.enums;

/**
 * （抛弃，回收）再跟进规则
 */
public enum FellowUpRulesEnum {

    // private Integer isDel;// 0正常，1y已回收  2已抛弃

    Reclaim(1, "Reclaim", "回收"),

    Discard(2, "Discard", "抛弃");

    FellowUpRulesEnum(int code, String codeName, String dec) {
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

    public static FellowUpRulesEnum valueOf(int code) {
        FellowUpRulesEnum[] consumerScopeEnums = FellowUpRulesEnum.values();
        for (FellowUpRulesEnum consumerScopeEnum : consumerScopeEnums) {
            if (consumerScopeEnum.getCode() == code) {
                return consumerScopeEnum;
            }
        }
        return null;
    }
}
