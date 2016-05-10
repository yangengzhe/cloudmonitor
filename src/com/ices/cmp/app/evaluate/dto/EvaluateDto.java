package com.ices.cmp.app.evaluate.dto;

import org.cspframework.core.dto.annotation.DtoClass;
import org.cspframework.core.dto.annotation.DtoProperty;

import com.ices.cmp.app.evaluate.domain.Evaluate;

@DtoClass(entities = Evaluate.class)
public class EvaluateDto {
    @DtoProperty(entityClass = Evaluate.class)
    private Integer id;//服务器id
    
    @DtoProperty(entityClass=Evaluate.class,entityProperty="app.id")
    private Integer appid;
    
    @DtoProperty(entityClass = Evaluate.class)
    private Integer responsetime;//服务器id
    
    @DtoProperty(entityClass = Evaluate.class)
    private Integer memory;//服务器id
    
    @DtoProperty(entityClass = Evaluate.class,entityProperty="concurrent")
    private Integer concurrent;//服务器id
    
    @DtoProperty(entityClass = Evaluate.class)
    private Integer net;//服务器id
    
    @DtoProperty(entityClass = Evaluate.class)
    private Double error;//服务器id
    
    @DtoProperty(entityClass = Evaluate.class)
    private Double thoughput;//服务器id
    
    @DtoProperty(entityClass = Evaluate.class)
    private String timestamp;//服务器id
    
    @DtoProperty(entityClass = Evaluate.class)
    private String desc;//服务器id
    private String date;
    
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

    public Integer getNet() {
        return net;
    }
    
    public void setNet(Integer net) {
        this.net = net;
    }
    
    public Double getError() {
        return error;
    }
    
    public void setError(Double error) {
        this.error = error;
    }
    
    public Double getThoughput() {
        return thoughput;
    }
    
    public void setThoughput(Double thoughput) {
        this.thoughput = thoughput;
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
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
}
