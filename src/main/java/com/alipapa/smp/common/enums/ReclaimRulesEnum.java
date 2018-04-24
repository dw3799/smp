package com.alipapa.smp.common.enums;

import com.alipapa.smp.utils.StringUtil;

/**
 * 客户回收规则
 */
public enum ReclaimRulesEnum {

            /*    30	ConsumerReclaimRules	新客户非公一人跟进	30	1	1	1	2018-03-14 20:01:46	2018-03-14 20:02:27
            31	ConsumerReclaimRules	新客户非公多人跟进	30	2	1	1	2018-03-14 20:01:46	2018-03-14 20:02:27
            32	ConsumerReclaimRules	新客户公共一人跟进	30	3	1	1	2018-03-14 20:01:46	2018-03-14 20:02:27
            33	ConsumerReclaimRules	新客户公共多人跟进	30	4	1	1	2018-03-14 20:01:46	2018-03-14 20:02:27
            34	ConsumerReclaimRules	老客户非公一人跟进	60	5	1	1	2018-03-14 20:01:46	2018-03-14 20:02:27
            35	ConsumerReclaimRules	老客户非公多人跟进	60	6	1	1	2018-03-14 20:01:46	2018-03-14 20:02:27*/


    NEW_PRIVATE_ONE(1, "NEW_PRIVATE_ONE", "N天未跟进的非公共资源池-单人跟进-未下单客户将被回收"),

    NEW_PROTECTED_MANY(2, "NEW_PROTECTED_MANY", "N天未跟进的非公共资源池-多人跟进-未下单客户将被回收"),

    NEW_PUBLIC_ONE(3, "NEW_PUBLIC_ONE", "N天未跟进的公共资源池-单人跟进-未下单客户将被回收"),

    NEW_PUBLIC_MANY(4, "NEW_PUBLIC_MANY", "N天未跟进的公共资源池-多人跟进-未下单客户将被回收"),

    DEAL_BOTH_ONE(5, "DEAL_BOTH_ONE", "N天未跟进的-单人跟进-已下单客户将被回收"),

    DEAL_BOTH_MANY(6, "DEAL_BOTH_MANY", "N天未跟进的-多人跟进-已下单客户将被回收");

    ReclaimRulesEnum(int code, String codeName, String dec) {
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

    public static ReclaimRulesEnum valueOf(int code) {
        ReclaimRulesEnum[] consumerScopeEnums = ReclaimRulesEnum.values();
        for (ReclaimRulesEnum consumerScopeEnum : consumerScopeEnums) {
            if (consumerScopeEnum.getCode() == code) {
                return consumerScopeEnum;
            }
        }
        return null;
    }

    public static ReclaimRulesEnum getValueByCodeName(String codeName) {
        if (StringUtil.isEmptyString(codeName)) {
            return null;
        }

        ReclaimRulesEnum[] consumerScopeEnums = ReclaimRulesEnum.values();
        for (ReclaimRulesEnum consumerScopeEnum : consumerScopeEnums) {
            if (codeName.equals(consumerScopeEnum.getCodeName())) {
                return consumerScopeEnum;
            }
        }
        return null;
    }
}
