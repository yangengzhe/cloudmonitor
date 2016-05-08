package com.ices.cmp.menu.dto;


public class CmpMenuDto {
    private Integer id;
    private String name;
    private Boolean leaf;
    private Integer serviceId;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Boolean getLeaf() {
        return leaf;
    }
    
    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }
    
    public Integer getServiceId() {
        return serviceId;
    }
    
    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }
    
}
