package com.ices.cmp.app.myapp.domain;

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

import com.ices.cmp.common.enumitem.ServerState;
import com.ices.cmp.vms.myvms.domain.Vms;

@Entity
@Table(name = "cmp_appinfo")
public class Myapp implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;// 服务器id，标识，主关键字
    
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, optional = false)
    @JoinColumn(name = "vmid")
    private Vms vms;
    
    @Column(name = "code")
    private String code;
    
    @Column(name = "name")
    private String name;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "state")
    private ServerState state;// 状态，0:停机；1:开机
    
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

    
    
    public ServerState getState() {
        return state;
    }


    
    public void setState(ServerState state) {
        this.state = state;
    }


    
    public String getDesc() {
        return desc;
    }


    
    public void setDesc(String desc) {
        this.desc = desc;
    }


    public void insert() {

        em().persist(this);
    }

    public void delete() {

        em().remove(this);
    }
    // 查询所有记录
    public static List<Myapp> findAllMyapp(PagingBean pb) {
        StringBuffer queryStr = new StringBuffer();
        queryStr.append( "SELECT c FROM Myapp c");
        StringBuffer sqlCount = new StringBuffer();
        sqlCount.append("select count(c) from Myapp c where 1=1 ");
        TypedQuery<Myapp> query = em().createQuery(queryStr.toString(), Myapp.class);
        TypedQuery<Long> countQuery = ThreadLocalManager.em().createQuery(sqlCount.toString(), Long.class);
        
        return pb.getResultList(countQuery, query);
    }
    
    public static Myapp findById(Integer id) {
        StringBuffer queryStr = new StringBuffer();
        queryStr.append("SELECT c FROM Myapp c WHERE 1=1 AND c.id=:id");
        TypedQuery<Myapp> query = em().createQuery(queryStr.toString(), Myapp.class);
        query.setParameter("id", id);
        return query.getResultList().get(0);
    }
    
    public static List<Myapp> findByParam(String myappName) {
        StringBuffer queryStr = new StringBuffer();
        queryStr.append("SELECT c FROM Myapp c WHERE 1=1 ");
        if (myappName != ""&&myappName!=null) {
            queryStr.append(" AND c.name LIKE :myappName ");
        }
        TypedQuery<Myapp> query = em().createQuery(queryStr.toString(), Myapp.class);
        if (myappName != ""&&myappName!=null) {
            query.setParameter("myappName", "%" + myappName + "%");
        }
        return query.getResultList();
    }
    
    //根据
    public static List<Myapp> findByDuplicate(Integer id,String code) {
        StringBuffer queryStr = new StringBuffer();
        queryStr.append("SELECT c FROM Myapp c WHERE 1=1 AND c.code = :code ");
        if (id != null && id.intValue() > 0) {
            queryStr.append(" AND c.id <> :id ");
        }
        TypedQuery<Myapp> query = em().createQuery(queryStr.toString(), Myapp.class);
        if (id != null && id.intValue() > 0) {
            query.setParameter("id", id);
        }
        query.setParameter("code", code);
        return query.getResultList();
    }
}
