package com.alipapa.smp.common.enums;

/**
 * （抛弃，回收）再跟进规则
 */
public enum DisCardRulesEnum {

    Reclaim(1, "Reclaim", "业务员被回收的客户N天不能再次跟进"),

    Discard(2, "Discard", "业务员抛弃的客户N天不能再次跟进");

    DisCardRulesEnum(int code, String codeName, String dec) {
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

    public static DisCardRulesEnum valueOf(int code) {
        DisCardRulesEnum[] consumerScopeEnums = DisCardRulesEnum.values();
        for (DisCardRulesEnum consumerScopeEnum : consumerScopeEnums) {
            if (consumerScopeEnum.getCode() == code) {
                return consumerScopeEnum;
            }
        }
        return null;
    }

    public static DisCardRulesEnum getValueByCodeName(String codeName) {
        DisCardRulesEnum[] consumerScopeEnums = DisCardRulesEnum.values();
        for (DisCardRulesEnum consumerScopeEnum : consumerScopeEnums) {
            if (consumerScopeEnum.getCodeName() == codeName) {
                return consumerScopeEnum;
            }
        }
        return null;
    }
}
