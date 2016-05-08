package com.ices.cmp.topology.server.dto;


public class TopoServerDto {
    private Integer id;
    private Integer father;
    private String name;
    private String type;
    private Integer status;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getFather() {
        return father;
    }
    
    public void setFather(Integer father) {
        this.father = father;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public Integer getStatus() {
        return status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
}
