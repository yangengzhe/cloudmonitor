package com.ices.cmp.server.usage.domain;

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

import com.ices.cmp.server.myserver.domain.Server;

@Entity
@Table(name = "cmp_serverusage")
public class Usage implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;// 服务器id，标识，主关键字
    
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, optional = false)
    @JoinColumn(name = "serverid")
    private Server server;// 所属节点，多对一
    
    @Column(name = "cpu")
    private Integer cpu;
    
    @Column(name = "memory")
    private Integer memory;
    
    @Column(name = "bandwidth")
    private Integer bandwidth;
    
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

    
    public Integer getBandwidth() {
        return bandwidth;
    }

    
    public void setBandwidth(Integer bandwidth) {
        this.bandwidth = bandwidth;
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
    public static List<Usage> findAll(Integer id) {
        StringBuffer queryStr = new StringBuffer();
        queryStr.append( "SELECT c FROM Usage c where 1=1 and c.server.id=:id");
        TypedQuery<Usage> query = em().createQuery(queryStr.toString(), Usage.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
}
