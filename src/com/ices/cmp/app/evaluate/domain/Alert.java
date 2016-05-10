package com.ices.cmp.app.evaluate.domain;

import static org.cspframework.core.jpa.ThreadLocalManager.em;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import org.cspframework.common.PagingBean;
import org.cspframework.core.jpa.ThreadLocalManager;

import com.ices.cmp.app.myapp.domain.Myapp;
import com.ices.cmp.common.enumitem.AlertState;

@Entity
@Table(name = "cmp_alert")
public class Alert implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;// 服务器id，标识，主关键字
    
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, optional = false)
    @JoinColumn(name = "appid")
    private Myapp app;
    
    @Column(name = "code")
    private String code;
    
    /**
     * 0:未读；1:已读
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type")
    private AlertState state;
    
    @Column(name = "msg")
    private String msg;
    
    @Column(name = "timestamp")
    private String timestamp;
    
    @Column(name = "descri")
    private String desc;
    
    public void insert() {

        em().persist(this);
    }

    public void delete() {

        em().remove(this);
    }
    
    public void update() {

        em().merge(this);
    }
    
 // 查询所有记录
    public static List<Alert> findAllAlert(PagingBean pb) {
        StringBuffer queryStr = new StringBuffer();
        queryStr.append( "SELECT c FROM Alert c");
        StringBuffer sqlCount = new StringBuffer();
        sqlCount.append("select count(c) from Alert c where 1=1 ");
        TypedQuery<Alert> query = em().createQuery(queryStr.toString(), Alert.class);
        TypedQuery<Long> countQuery = ThreadLocalManager.em().createQuery(sqlCount.toString(), Long.class);
        
        return pb.getResultList(countQuery, query);
    }
    
 // 根据id查询
    public static Alert findById(Integer id) {
        StringBuffer queryStr = new StringBuffer();
        queryStr.append("SELECT c FROM Alert c WHERE 1=1 AND c.id=:id");
        TypedQuery<Alert> query = em().createQuery(queryStr.toString(), Alert.class);
        query.setParameter("id", id);
        return query.getResultList().get(0);
    }

    
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

    
    public String getCode() {
        return code;
    }

    
    public void setCode(String code) {
        this.code = code;
    }

    
    public AlertState getState() {
        return state;
    }

    
    public void setState(AlertState state) {
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
}
