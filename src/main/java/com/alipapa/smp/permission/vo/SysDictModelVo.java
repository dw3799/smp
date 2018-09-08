package com.alipapa.smp.permission.vo;

public class SysDictModelVo {
    private Long id;

    private String categoryCode;

    private String categoryTitle;

    private String dictText;

    private String dictValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode == null ? null : categoryCode.trim();
    }


    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getDictText() {
        return dictText;
    }

    public void setDictText(String dictText) {
        this.dictText = dictText == null ? null : dictText.trim();
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }
}