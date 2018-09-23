package com.alipapa.smp.common.enums;

/**
 * 物料工厂状态枚举
 */
public enum MaterielFactoryStatusEnum {
    FACTORY_ORDERED(6, "FACTORY_ORDERED", "已下单"),

    FACTORY_MATERIEL(7, "FACTORY_MATERIEL", "已下料"),

    FACTORY_PRINTING(8, "FACTORY_PRINTING", "已印刷"),

    FACTORY_PACKAGE(9, "FACTORY_PACKAGE", "已车缝"),

    FACTORY_INVOICE(10, "FACTORY_INVOICE", "已发货");

    MaterielFactoryStatusEnum(int code, String codeName, String dec) {
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

    public static MaterielFactoryStatusEnum valueOf(int code) {
        MaterielFactoryStatusEnum[] consumerScopeEnums = MaterielFactoryStatusEnum.values();
        for (MaterielFactoryStatusEnum consumerScopeEnum : consumerScopeEnums) {
            if (consumerScopeEnum.getCode() == code) {
                return consumerScopeEnum;
            }
        }
        return null;
    }
}
