package com.ices.cmp.server.route.domain;

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

import com.ices.cmp.server.node.domain.Node;

@Entity
@Table(name = "cmp_routeinfo")
public class Route implements Serializable{
 
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;// 节点id
    
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, optional = false)
    @JoinColumn(name = "nodeid")
    private Node node;// 所属节点，多对一
    
    @Column(name = "code")
    private String code;// 节点编码，唯一，非空
    
    @Column(name = "name")
    private String name;// 节点编码，唯一，非空
    
    @Column(name = "superroute")
    private String superroute;// 节点编码，唯一，非空
    
    @Column(name = "descri")
    private String desc;// 节点编码，唯一，非空

    public void insert() {

        em().persist(this);
    }

    public void delete() {

        em().remove(this);
    }
    
    public Integer getId() {
        return id;
    }

    
    public void setId(Integer id) {
        this.id = id;
    }

    
    public Node getNode() {
        return node;
    }

    
    public void setNode(Node node) {
        this.node = node;
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

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    
    
 // 查询所有记录
    public static List<Route> findAll(PagingBean pb,int nodeid) {
        StringBuffer queryStr = new StringBuffer();
        queryStr.append( "SELECT c FROM Route c where 1=1 ");
        StringBuffer sqlCount = new StringBuffer();
        sqlCount.append("select count(c) from Route  c where 1=1 ");
        if(nodeid!=0){
            queryStr.append(" and c.node.id = :nodeid");
            sqlCount.append(" and c.node.id = :nodeid");
        }
        TypedQuery<Route> query = em().createQuery(queryStr.toString(), Route.class);
        query.setParameter("nodeid", nodeid);
        TypedQuery<Long> countQuery = ThreadLocalManager.em().createQuery(sqlCount.toString(), Long.class);
        countQuery.setParameter("nodeid", nodeid);
        return pb.getResultList(countQuery, query);
    }
  //根据节点name查询
    public static List<Route> findByName(String name) {
        StringBuffer queryStr = new StringBuffer();
        queryStr.append("SELECT c FROM Route c WHERE 1=1 AND c.name LIKE :name ");
        TypedQuery<Route> query = em().createQuery(queryStr.toString(), Route.class);
        query.setParameter("name", "%" + name + "%");
        List<Route> list = query.getResultList();

        return list;
    }
  //根据节点code查询
    public static Route findByCode(String code) {
        StringBuffer queryStr = new StringBuffer();
        queryStr.append("SELECT c FROM Route c WHERE 1=1 AND c.code= :code ");
        TypedQuery<Route> query = em().createQuery(queryStr.toString(), Route.class);
        query.setParameter("code", code);

        return query.getResultList().get(0);
    }
  //根据节点name查询
    public static Route findByNameCurrent(String name) {
        StringBuffer queryStr = new StringBuffer();
        queryStr.append("SELECT c FROM Route c WHERE 1=1 AND c.name = :name ");
        TypedQuery<Route> query = em().createQuery(queryStr.toString(), Route.class);
        query.setParameter("name", name);
        Route list = query.getResultList().get(0);

        return list;
    }
    
    // 根据id查询
    public static Route findById(Integer id) {
        // TODO Auto-generated method stub
        return em().find(Route.class, id);
    }
    //根据code查找
    public static List<Route> findByDuplicate(Integer id,String code) {
        StringBuffer queryStr = new StringBuffer();
        queryStr.append("SELECT c FROM Route c WHERE 1=1 AND c.code = :code ");
        if (id != null && id.intValue() > 0) {
            queryStr.append(" AND c.id <> :id ");
        }
        TypedQuery<Route> query = em().createQuery(queryStr.toString(), Route.class);
        if (id != null && id.intValue() > 0) {
            query.setParameter("id", id);
        }
        query.setParameter("code", code);
        return query.getResultList();
    }
  //根据Superroute
    public static List<Route> findBySuperroute(String superroute) {
        StringBuffer queryStr = new StringBuffer();
        queryStr.append("SELECT c FROM Route c WHERE 1=1 AND c.superroute = :superroute ");
        TypedQuery<Route> query = em().createQuery(queryStr.toString(), Route.class);
        query.setParameter("superroute", superroute);
        return query.getResultList();
    }
}
