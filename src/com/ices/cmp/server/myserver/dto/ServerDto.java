package com.ices.cmp.server.myserver.dto;


import org.cspframework.core.dto.annotation.DtoClass;
import org.cspframework.core.dto.annotation.DtoProperty;

import com.ices.cmp.server.myserver.domain.Server;


@DtoClass(entities = Server.class)
public class ServerDto {
	/**
	 * 主键 节点id
	 */
	@DtoProperty(entityClass = Server.class)
	private Integer id;//服务器id
	
	@DtoProperty(entityClass = Server.class)
	private String code;// 服务器编码，唯一，非空

	@DtoProperty(entityClass = Server.class)
	private String name;// 服务器名称，非空
	
	@DtoProperty(entityClass=Server.class,entityProperty="node.name")
	private String nodeName;//服务器所属节点名称

	@DtoProperty(entityClass = Server.class)
	private String vplatform;// 虚拟化平台
	
	@DtoProperty(entityClass = Server.class)
	private String ip;// IP地址
	
	@DtoProperty(entityClass=Server.class, entityProperty="state.value", readOnly=true)
	private String state;// 状态，0:停机；1:开机

	@DtoProperty(entityClass = Server.class)
    private Integer cpu;//服务器cpu
	
	@DtoProperty(entityClass = Server.class)
    private Integer memory;//服务器memory
	
	@DtoProperty(entityClass = Server.class)
    private Integer bandwidth;//服务器bandwidth
	
	@DtoProperty(entityClass = Server.class)
    private String superroute;//服务器superroute

	@DtoProperty(entityClass = Server.class)
	private String desc;// 说明

    
    public Integer getId() {
        return id;
    }

    
    public void setId(Integer id) {
        this.id = id;
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

    
    public String getNodeName() {
        return nodeName;
    }

    
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    

    
    
    public String getVplatform() {
        return vplatform;
    }


    
    public void setVplatform(String vplatform) {
        this.vplatform = vplatform;
    }


    public String getIp() {
        return ip;
    }

    
    public void setIp(String ip) {
        this.ip = ip;
    }

    
    public String getState() {
        return state;
    }

    
    public void setState(String state) {
        this.state = state;
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

    
    public Integer getBandwidth() {
        return bandwidth;
    }

    
    public void setBandwidth(Integer bandwidth) {
        this.bandwidth = bandwidth;
    }

    
    public String getSuperroute() {
        return superroute;
    }

    
    public void setSuperroute(String superroute) {
        this.superroute = superroute;
    }

    
    public String getDesc() {
        return desc;
    }

    
    public void setDesc(String desc) {
        this.desc = desc;
    }

	
}
