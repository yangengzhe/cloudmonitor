package com.ices.cmp.server.status.domain;

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

import org.cspframework.common.PagingBean;
import org.cspframework.core.jpa.ThreadLocalManager;

import com.ices.cmp.server.myserver.domain.Server;
import com.ices.cmp.server.route.domain.Route;

@Entity
@Table(name = "cmp_serverstatus")
public class Status implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;// 节点id
    
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, optional = false)
    @JoinColumn(name = "serverid")
    private Server server;// 所属节点，多对一
    
    @Column(name = "cpu")
    private Integer cpu;
    
    @Column(name = "memory")
    private Integer memory;
    
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

    
    public Server getServer() {
        return server;
    }

    
    public void setServer(Server server) {
        this.server = server;
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
    public static List<Status> findAll(Integer id) {
        StringBuffer queryStr = new StringBuffer();
        queryStr.append( "SELECT c FROM Status c where 1=1 and c.server.id=:id");
        TypedQuery<Status> query = em().createQuery(queryStr.toString(), Status.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
    
}
