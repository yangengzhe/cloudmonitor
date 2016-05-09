package com.ices.cmp.vms.vmstatus.dto;

import java.io.Serializable;

import org.cspframework.core.dto.annotation.DtoClass;
import org.cspframework.core.dto.annotation.DtoProperty;

import com.ices.cmp.server.status.domain.Status;
import com.ices.cmp.vms.vmstatus.domain.Vmstatus;

@DtoClass(entities = Vmstatus.class)
public class VmstatusDto{
    @DtoProperty(entityClass = Vmstatus.class)
    private Integer id;//服务器id
    
    @DtoProperty(entityClass=Vmstatus.class,entityProperty="vms.id")
    private String vmid;//服务器所属节点名称

    @DtoProperty(entityClass = Vmstatus.class)
    private Integer cpu;//服务器id
    
    @DtoProperty(entityClass = Vmstatus.class)
    private Integer memory;//服务器id
    
    @DtoProperty(entityClass = Vmstatus.class)
    private Integer disk;//服务器id
    
    @DtoProperty(entityClass = Vmstatus.class)
    private Integer upload;//服务器id
    
    @DtoProperty(entityClass = Vmstatus.class)
    private Integer download;//服务器id
    
    @DtoProperty(entityClass = Vmstatus.class)
    private String timestamp;//服务器id
    
    @DtoProperty(entityClass = Vmstatus.class)
    private String desc;//服务器id
    
    private Integer net_upload;
    private Integer net_download;
    private Integer mem;
    private String date;
    
    
    public Integer getNet_upload() {
        return net_upload;
    }


    
    public void setNet_upload(Integer net_upload) {
        this.net_upload = net_upload;
    }


    
    public Integer getNet_download() {
        return net_download;
    }


    
    public void setNet_download(Integer net_download) {
        this.net_download = net_download;
    }


    
    public Integer getMem() {
        return mem;
    }


    
    public void setMem(Integer mem) {
        this.mem = mem;
    }


    
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

    
    public String getVmid() {
        return vmid;
    }

    
    public void setVmid(String vmid) {
        this.vmid = vmid;
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

    
    public Integer getUpload() {
        return upload;
    }

    
    public void setUpload(Integer upload) {
        this.upload = upload;
    }

    
    public Integer getDownload() {
        return download;
    }

    
    public void setDownload(Integer download) {
        this.download = download;
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
