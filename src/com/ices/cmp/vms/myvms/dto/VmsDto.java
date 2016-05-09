package com.ices.cmp.vms.myvms.dto;

import org.cspframework.core.dto.annotation.DtoClass;
import org.cspframework.core.dto.annotation.DtoProperty;

import com.ices.cmp.server.myserver.domain.Server;
import com.ices.cmp.vms.myvms.domain.Vms;

@DtoClass(entities = Vms.class)
public class VmsDto {
    
    @DtoProperty(entityClass = Vms.class)
    private Integer id;//服务器id
    
    @DtoProperty(entityClass = Vms.class,entityProperty="server.code")
    private String serverCode;
    
    @DtoProperty(entityClass = Vms.class)
    private String code;
    
    @DtoProperty(entityClass = Vms.class)
    private String name;
    
    @DtoProperty(entityClass = Vms.class,entityProperty="state.value", readOnly=true)
    private String state;// 状态，0:停机；1:开机
    
    @DtoProperty(entityClass = Vms.class)
    private String ip;//服务器cpu
    
    @DtoProperty(entityClass = Vms.class)
    private Integer cpu;//服务器cpu
    
    @DtoProperty(entityClass = Vms.class)
    private Integer memory;//服务器memory
    
    @DtoProperty(entityClass = Vms.class)
    private Integer disk;//服务器bandwidth
    
    @DtoProperty(entityClass = Vms.class)
    private Integer bandwidth;//服务器bandwidth

    @DtoProperty(entityClass = Vms.class)
    private String desc;// 说明

    
    public Integer getId() {
        return id;
    }

    
    public void setId(Integer id) {
        this.id = id;
    }

    

    
    
    public String getServerCode() {
        return serverCode;
    }


    
    public void setServerCode(String serverCode) {
        this.serverCode = serverCode;
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

    
    public String getIp() {
        return ip;
    }

    
    public void setIp(String ip) {
        this.ip = ip;
    }

    
    public Integer getCpu() {
        return cpu;
    }

    
    public void setCpu(Integer cpu) {
        this.cpu = cpu;
    }

    
    public Integer getMemory() {
        return memory;
    }

    
    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    
    public Integer getDisk() {
        return disk;
    }

    
    public void setDisk(Integer disk) {
        this.disk = disk;
    }

    
    public Integer getBandwidth() {
        return bandwidth;
    }

    
    public void setBandwidth(Integer bandwidth) {
        this.bandwidth = bandwidth;
    }

    
    public String getDesc() {
        return desc;
    }

    
    public void setDesc(String desc) {
        this.desc = desc;
    }
}
