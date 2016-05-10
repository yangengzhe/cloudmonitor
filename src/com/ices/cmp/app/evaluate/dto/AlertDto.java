package com.ices.cmp.app.evaluate.dto;

import org.cspframework.core.dto.annotation.DtoClass;
import org.cspframework.core.dto.annotation.DtoProperty;

import com.ices.cmp.app.evaluate.domain.Alert;

@DtoClass(entities = Alert.class)
public class AlertDto {
    @DtoProperty(entityClass = Alert.class)
    private Integer id;//服务器id
    
    @DtoProperty(entityClass = Alert.class,entityProperty="app.id")
    private Integer appid;
    
    @DtoProperty(entityClass = Alert.class, entityProperty="state.value", readOnly=true)
    private String state;
    
    @DtoProperty(entityClass = Alert.class)
    private String msg;
    
    @DtoProperty(entityClass = Alert.class)
    private String code;
    
    @DtoProperty(entityClass = Alert.class)
    private String timestamp;
    
    @DtoProperty(entityClass = Alert.class)
    private String desc;
    
    private String date;

    
    
    public String getCode() {
        return code;
    }


    
    public void setCode(String code) {
        this.code = code;
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

    
    public String getState() {
        return state;
    }

    
    public void setState(String state) {
        this.state = state;
    }

    
    public String getMsg() {
        return msg;
    }

    
    public void setMsg(String msg) {
        this.msg = msg;
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

    
    public void setDesc(String des) {
        this.desc = des;
    }

    
    public String getDate() {
        return date;
    }

    
    public void setDate(String date) {
        this.date = date;
    }
}
