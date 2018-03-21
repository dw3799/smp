package com.alipapa.smp.permission.vo;

import java.util.List;

public class PermissionItemVo {
    private Long id;

    private String name;

    private String icon;

    private String url;

    private List<PermissionItemVo> childs;


    public List<PermissionItemVo> getChilds() {
        return childs;
    }

    public void setChilds(List<PermissionItemVo> childs) {
        this.childs = childs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

}