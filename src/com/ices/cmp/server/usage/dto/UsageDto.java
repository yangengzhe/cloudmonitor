package com.ices.cmp.server.usage.dto;

import org.cspframework.core.dto.annotation.DtoClass;
import org.cspframework.core.dto.annotation.DtoProperty;

import com.ices.cmp.server.usage.domain.Usage;


@DtoClass(entities = Usage.class)
public class UsageDto {
    @DtoProperty(entityClass = Usage.class)
    private Integer id;//服务器id
    
    @DtoProperty(entityClass=Usage.class,entityProperty="server.id")
    private Integer serverid;
    
    @DtoProperty(entityClass=Usage.class,entityProperty="server.ip")
    private String ip;
    
    @DtoProperty(entityClass = Usage.class,entityProperty="server.cpu")
    private Integer cpu_enable;//服务器id
    
    @DtoProperty(entityClass = Usage.class,entityProperty="server.memory")
    private Integer mem_enable;//服务器id
    
    @DtoProperty(entityClass = Usage.class,entityProperty="server.bandwidth")
    private Integer net_enable;//服务器id
    
    @DtoProperty(entityClass = Usage.class)
    private Integer cpu;//服务器id
    
    @DtoProperty(entityClass = Usage.class,entityProperty="memory")
    private Integer mem;//服务器id
    
    @DtoProperty(entityClass = Usage.class,entityProperty="bandwidth")
    private Integer net;//服务器id
    
    @DtoProperty(entityClass = Usage.class)
    private String timestamp;//服务器id
    
    @DtoProperty(entityClass = Usage.class)
    private String desc;//服务器id

    
    public Integer getId() {
        return id;
    }

    
    public void setId(Integer id) {
        this.id = id;
    }

    
    public Integer getServerid() {
        return serverid;
    }

    
    public void setServerid(Integer serverid) {
        this.serverid = serverid;
    }

    

    
    
    public String getIp() {
        return ip;
    }


    
    public void setIp(String ip) {
        this.ip = ip;
    }


    public Integer getCpu_enable() {
        return cpu_enable;
    }

    
    public void setCpu_enable(Integer cpu_enable) {
        this.cpu_enable = cpu_enable;
    }

    
    public Integer getMem_enable() {
        return mem_enable;
    }

    
    public void setMem_enable(Integer mem_enable) {
        this.mem_enable = mem_enable;
    }

    
    public Integer getNet_enable() {
        return net_enable;
    }

    
    public void setNet_enable(Integer net_enable) {
        this.net_enable = net_enable;
    }

    
    public Integer getCpu() {
        return cpu;
    }

    
    public void setCpu(Integer cpu) {
        this.cpu = cpu;
    }

    
    public Integer getMem() {
        return mem;
    }

    
    public void setMem(Integer mem) {
        this.mem = mem;
    }

    
    public Integer getNet() {
        return net;
    }

    
    public void setNet(Integer net) {
        this.net = net;
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
