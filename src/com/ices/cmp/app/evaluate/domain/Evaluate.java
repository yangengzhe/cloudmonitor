package com.ices.cmp.app.evaluate.domain;

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

import com.ices.cmp.app.myapp.domain.Myapp;

@Entity
@Table(name = "cmp_appmodel")
public class Evaluate implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;// 服务器id，标识，主关键字
    
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, optional = false)
    @JoinColumn(name = "appid")
    private Myapp app;
    
    @Column(name = "memory")
    private Integer memory;
    
    @Column(name = "responsetime")
    private Integer responsetime;
    
    @Column(name = "concurrent")
    private Integer concurrent;
    
    @Column(name = "net")
    private Integer net;
    
    @Column(name = "error")
    private Double error;
    
    @Column(name = "thoughput")
    private Double thoughput;    
    
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

    
    public Myapp getApp() {
        return app;
    }

    
    public void setApp(Myapp app) {
        this.app = app;
    }

    
    public Integer getMemory() {
        return memory;
    }

    
    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    
    public Integer getResponsetime() {
        return responsetime;
    }

    
    public void setResponsetime(Integer responsetime) {
        this.responsetime = responsetime;
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
    
    // 查询所有记录
    public static List<Evaluate> findAll(Integer id) {
        StringBuffer queryStr = new StringBuffer();
        queryStr.append( "SELECT c FROM Evaluate c where 1=1 and c.app.id=:id");
        TypedQuery<Evaluate> query = em().createQuery(queryStr.toString(), Evaluate.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
}
