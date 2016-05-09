package com.ices.cmp.vms.myvms.domain;

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
import com.ices.cmp.server.myserver.domain.Server;

@Entity
@Table(name = "cmp_vminfo")
public class Vms implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;// 服务器id，标识，主关键字
    
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, optional = false)
    @JoinColumn(name = "serverid")
    private Server server;
    
    @Column(name = "code")
    private String code;// 服务器编码，唯一，非空

    @Column(name = "name")
    private String name;// 服务器名称，非空
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "state")
    private ServerState state;// 状态，0:停机；1:开机
    
    @Column(name = "ip")
    private String ip;// IP地址
    
    @Column(name = "cpu")
    private Integer cpu;// cpu核数
    
    @Column(name = "memory")
    private Integer memory;// 内存大小M
    
    @Column(name = "disk")
    private Integer disk;// 内存大小M
    
    @Column(name = "bandwidth")
    private Integer bandwidth;// 带宽大小M
    
    @Column(name = "descri")
    private String desc;// 说明

    
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
    
    public void insert() {

        em().persist(this);
    }

    public void delete() {

        em().remove(this);
    }
    // 查询所有记录
    public static List<Vms> findAllVms(PagingBean pb) {
        StringBuffer queryStr = new StringBuffer();
        queryStr.append( "SELECT c FROM Vms c");
        StringBuffer sqlCount = new StringBuffer();
        sqlCount.append("select count(c) from Vms c where 1=1 ");
        TypedQuery<Vms> query = em().createQuery(queryStr.toString(), Vms.class);
        TypedQuery<Long> countQuery = ThreadLocalManager.em().createQuery(sqlCount.toString(), Long.class);
        
        return pb.getResultList(countQuery, query);
    }
    
    public static Vms findById(Integer id) {
        StringBuffer queryStr = new StringBuffer();
        queryStr.append("SELECT c FROM Vms c WHERE 1=1 AND c.id=:id");
        TypedQuery<Vms> query = em().createQuery(queryStr.toString(), Vms.class);
        query.setParameter("id", id);
        return query.getResultList().get(0);
    }
    
    public static List<Vms> findByParam(String vmsName) {
        StringBuffer queryStr = new StringBuffer();
        queryStr.append("SELECT c FROM Vms c WHERE 1=1 ");
        if (vmsName != ""&&vmsName!=null) {
            queryStr.append(" AND c.name LIKE :vmsName ");
        }
        TypedQuery<Vms> query = em().createQuery(queryStr.toString(), Vms.class);
        if (vmsName != ""&&vmsName!=null) {
            query.setParameter("vmsName", "%" + vmsName + "%");
        }
        return query.getResultList();
    }
    
    //根据
    public static List<Vms> findByDuplicate(Integer id,String code) {
        StringBuffer queryStr = new StringBuffer();
        queryStr.append("SELECT c FROM Vms c WHERE 1=1 AND c.code = :code ");
        if (id != null && id.intValue() > 0) {
            queryStr.append(" AND c.id <> :id ");
        }
        TypedQuery<Vms> query = em().createQuery(queryStr.toString(), Vms.class);
        if (id != null && id.intValue() > 0) {
            query.setParameter("id", id);
        }
        query.setParameter("code", code);
        return query.getResultList();
    }
    
  //根据serverId查找vm列表，查找当前server下的vm
    public static List<Vms> findVmsByServerId(Integer serverId) {
        StringBuffer queryStr = new StringBuffer();
        queryStr.append("SELECT c FROM Vms c WHERE 1=1 AND c.server.id = :serverId");
        TypedQuery<Vms> query = em().createQuery(queryStr.toString(), Vms.class);
        query.setParameter("serverId", serverId);
        return query.getResultList();
    }
}
