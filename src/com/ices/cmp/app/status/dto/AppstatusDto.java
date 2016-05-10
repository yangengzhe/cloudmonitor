package com.ices.cmp.app.status.dto;

import org.cspframework.core.dto.annotation.DtoClass;
import org.cspframework.core.dto.annotation.DtoProperty;

import com.ices.cmp.app.status.domain.Appstatus;

@DtoClass(entities = Appstatus.class)
public class AppstatusDto {
    @DtoProperty(entityClass = Appstatus.class)
    private Integer id;//服务器id
    
    @DtoProperty(entityClass=Appstatus.class,entityProperty="app.id")
    private Integer appid;
    
    @DtoProperty(entityClass = Appstatus.class)
    private Integer responsetime;//服务器id
    
    @DtoProperty(entityClass = Appstatus.class)
    private Integer memory;//服务器id
    
    @DtoProperty(entityClass = Appstatus.class)
    private Integer concurrent;//服务器id
    
    @DtoProperty(entityClass = Appstatus.class)
    private String timestamp;//服务器id
    
    @DtoProperty(entityClass = Appstatus.class)
    private String desc;//服务器id
    private String date;
    
    
    public String getDate() {
        return date;
    }


    
    public void setDate(String date) {
        this.date = date;
    }


    public Integer getId() {
        return id;
    }

    
    public void setId(Integer id) {
        this.id = id;
    }

    
    public Integer getAppid() {
        return appid;
    }

    
    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    
    public Integer getResponsetime() {
        return responsetime;
    }

    
    public void setResponsetime(Integer responsetime) {
        this.responsetime = responsetime;
    }

    
    public Integer getMemory() {
        return memory;
    }

    
    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    
    public Integer getConcurrent() {
        return concurrent;
    }

    
    public void setConcurrent(Integer concurrent) {
        this.concurrent = concurrent;
    }

    
    public String getTimestamp() {
        return timestamp;
    }

    
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    
    public String getDesc() {
        return desc;
    }

    
    public void setDesc(String desc) {
        this.desc = desc;
    }
}
