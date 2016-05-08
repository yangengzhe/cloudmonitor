package com.ices.cmp.server.myserver.domain;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import org.cspframework.common.PagingBean;
import org.cspframework.core.jpa.ThreadLocalManager;

import com.ices.cmp.common.enumitem.ServerState;
import com.ices.cmp.server.node.domain.Node;


@Entity
@Table(name = "cmp_serverinfo")
public class Server implements Serializable{
	public static final String ALL = "com.ices.csp.server.domain.Server.findAll";
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;// 服务器id，标识，主关键字

	@Column(name = "code")
	private String code;// 服务器编码，唯一，非空

	@Column(name = "name")
	private String name;// 服务器名称，非空
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "nodeId")
	private Node node;// 所属节点，多对一
	
	/**
	* 0:停机；1:开机
	*/
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "state")
	private ServerState state;// 状态，0:停机；1:开机

	@Column(name = "vplatform")
	private String vplatform;// 虚拟化平台
	
	@Column(name = "ip")
	private String ip;// IP地址
	
	@Column(name = "cpu")
    private Integer cpu;// cpu核数
	
	@Column(name = "memory")
    private Integer memory;// 内存大小M
	
	@Column(name = "bandwidth")
    private Integer bandwidth;// 带宽大小M
	
	@Column(name = "superroute")
    private String superroute;// 上一级路由id
	
	@Column(name = "descri")
	private String desc;// 说明
	
	public void insert() {

		em().persist(this);
	}

	public void delete() {

		em().remove(this);
	}
	// 查询所有记录
	public static List<Server> findAllServer(PagingBean pb) {
		StringBuffer queryStr = new StringBuffer();
		queryStr.append( "SELECT c FROM Server c");
		StringBuffer sqlCount = new StringBuffer();
		sqlCount.append("select count(c) from Server  c where 1=1 ");
		TypedQuery<Server> query = em().createQuery(queryStr.toString(), Server.class);
		TypedQuery<Long> countQuery = ThreadLocalManager.em().createQuery(sqlCount.toString(), Long.class);
		
		return pb.getResultList(countQuery, query);
	}
	
	// 根据id查询
	public static Server findById(Integer id) {
	    StringBuffer queryStr = new StringBuffer();
        queryStr.append("SELECT c FROM Server c WHERE 1=1 AND c.id=:id");
        TypedQuery<Server> query = em().createQuery(queryStr.toString(), Server.class);
        query.setParameter("id", id);
		return query.getResultList().get(0);
	}
	
	public static List<Server> findByParam(String nodeName, String serverName) {
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("SELECT c FROM Server c WHERE 1=1 ");
		if (nodeName != ""&&nodeName!=null) {
			queryStr.append("AND c.node.name = :nodeName ");
		}
		if (serverName != ""&&serverName!=null) {
			queryStr.append(" AND c.name LIKE :serverName ");
		}
		TypedQuery<Server> query = em().createQuery(queryStr.toString(), Server.class);
		if (nodeName != ""&&nodeName!=null) {
			query.setParameter("nodeName", nodeName);
		}
		if (serverName != ""&&serverName!=null) {
			query.setParameter("serverName", "%" + serverName + "%");
		}
		return query.getResultList();
	}
	//根据nodeId查找server列表，查找当前node下的server
	public static List<Server> findServersByNodeId(Integer nodeId) {
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("SELECT c FROM Server c WHERE 1=1 ");
		if (nodeId != null && nodeId.intValue() > 0) {
			queryStr.append("AND c.node.id = :nodeId ");
		}
		TypedQuery<Server> query = em().createQuery(queryStr.toString(), Server.class);
		if (nodeId != null && nodeId.intValue() > 0) {
			query.setParameter("nodeId", nodeId);
		}
		return query.getResultList();
	}
	//根据nodename查找server列表，查找当前node下的server
	public static List<Server> findServerBynodeName(String nodeName) {
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("SELECT c FROM Server c WHERE 1=1 ");
		if (nodeName != "") {
			queryStr.append("AND c.node.name = :nodeName ");
		}
		TypedQuery<Server> query = em().createQuery(queryStr.toString(), Server.class);
		if (nodeName != "") {
			query.setParameter("nodeName", nodeName);
		}
		return query.getResultList();
	}
	
	//根据
    public static List<Server> findByDuplicate(Integer id,String code) {
        StringBuffer queryStr = new StringBuffer();
        queryStr.append("SELECT c FROM Server c WHERE 1=1 AND c.code = :code ");
        if (id != null && id.intValue() > 0) {
            queryStr.append(" AND c.id <> :id ");
        }
        TypedQuery<Server> query = em().createQuery(queryStr.toString(), Server.class);
        if (id != null && id.intValue() > 0) {
            query.setParameter("id", id);
        }
        query.setParameter("code", code);
        return query.getResultList();
    }
    
  //根据上级路由获得Server
    public static List<Server> findBySuperroute(String superroute) {
        StringBuffer queryStr = new StringBuffer();
        queryStr.append("SELECT c FROM Server c WHERE 1=1 AND c.superroute = :superroute ");
        TypedQuery<Server> query = em().createQuery(queryStr.toString(), Server.class);
        query.setParameter("superroute", superroute);
        return query.getResultList();
    }

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

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

    
    public ServerState getState() {
        return state;
    }

    
    public void setState(ServerState state) {
        this.state = state;
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
