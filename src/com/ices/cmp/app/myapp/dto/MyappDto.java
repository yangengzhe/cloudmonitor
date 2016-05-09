package com.ices.cmp.app.myapp.dto;

import org.cspframework.core.dto.annotation.DtoClass;
import org.cspframework.core.dto.annotation.DtoProperty;

import com.ices.cmp.app.myapp.domain.Myapp;

@DtoClass(entities = Myapp.class)
public class MyappDto {
    
    @DtoProperty(entityClass = Myapp.class)
    private Integer id;//服务器id
    
    @DtoProperty(entityClass = Myapp.class,entityProperty="vms.code")
    private String vmCode;
    
    @DtoProperty(entityClass = Myapp.class)
    private String code;
    
    @DtoProperty(entityClass = Myapp.class)
    private String name;
    
    @DtoProperty(entityClass = Myapp.class,entityProperty="state.value", readOnly=true)
    private String state;
    
    @DtoProperty(entityClass = Myapp.class)
    private String desc;

    
    public Integer getId() {
        return id;
    }

    
    public void setId(Integer id) {
        this.id = id;
    }

    
    public String getVmCode() {
        return vmCode;
    }

    
    public void setVmCode(String vmCode) {
        this.vmCode = vmCode;
    }

    
    public String getCode() {
        return code;
    }

    
    public void setCode(String code) {
        this.code = code;
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    
    public String getState() {
        return state;
    }

    
    public void setState(String state) {
        this.state = state;
    }


    
    public String getDesc() {
        return desc;
    }


    
    public void setDesc(String desc) {
        this.desc = desc;
    }

    
}
