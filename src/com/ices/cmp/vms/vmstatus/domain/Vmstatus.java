package com.ices.cmp.vms.vmstatus.domain;

import static org.cspframework.core.jpa.ThreadLocalManager.em;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import com.ices.cmp.server.status.domain.Status;
import com.ices.cmp.vms.myvms.domain.Vms;

@Entity
@Table(name = "cmp_vmstatus")
public class Vmstatus   implements Serializable{
private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;// 节点id
    
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, optional = false)
    @JoinColumn(name = "vmid")
    private Vms vms;// 所属节点，多对一
    
    @Column(name = "cpu")
    private Integer cpu;
    
    @Column(name = "memory")
    private Integer memory;
    
    @Column(name = "disk")
    private Integer disk;
    
    @Column(name = "upload")
    private Integer upload;
    
    @Column(name = "download")
    private Integer download;
    
    @Column(name = "timestamp")
    private String timestamp;
    
    @Column(name = "descri")
    private String desc;

    
    public Integer getId() {
        return id;
    }

    
    public void setId(Integer id) {
        this.id = id;
    }

    
    public Vms getVms() {
        return vms;
    }

    
    public void setVms(Vms vms) {
        this.vms = vms;
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
    // 查询所有记录
    public static List<Vmstatus> findAll(Integer id) {
        StringBuffer queryStr = new StringBuffer();
        queryStr.append( "SELECT c FROM Vmstatus c where 1=1 and c.vms.id=:id");
        TypedQuery<Vmstatus> query = em().createQuery(queryStr.toString(), Vmstatus.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
    
}
