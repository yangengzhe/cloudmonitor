package com.ices.csp.server.domain;

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

import com.ices.csp.common.enumitem.IsVirtual;
import com.ices.csp.common.enumitem.ServerCategory;
import com.ices.csp.common.enumitem.ServerState;
import com.ices.csp.common.enumitem.ServerType;
import com.ices.csp.node.domain.Node;


@Entity
@Table(name = "csp_server")
@NamedQueries({ @NamedQuery(name = Server.ALL, query = "SELECT c FROM Server c WHERE 1 = 1") })
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
	* 0:计算；1:存储
	 */
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "type")
	private ServerType type;// 服务器类型，0:计算；1:存储
	/**
	* 0:未虚拟化；1:已虚拟化
	 */
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "flag")
	private IsVirtual flag;// 是否虚拟化

	@Column(name = "vpaltform")
	private String vpaltform;// 虚拟化平台
	
	@Column(name = "IP")
	private String ip;// IP地址
	/**
	* 0:停机；1:开机
	 */
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "state")
	private ServerState state;// 状态，0:停机；1:开机

	@Column(name = "user")
	private String user;// 登陆名
	
	@Column(name = "password")
	private String password;// 密码

	@Column(name = "descri")
	private String desc;// 说明

	@Column(name = "producer")
	private String producer;// 生产商
	
	@Column(name = "model")
	private String model;// 型号
	/**
	* 0:机架；1:塔式；2:刀片
	 */
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "category")
	private ServerCategory category;// 类别，0:机架；1:塔式；2:刀片

	@Column(name = "structure")
	private String structure;// 结构
	
	@Column(name = "CPUType")
	private String cpuType;// CPU类型

	@Column(name = "CPUModel")
	private String cpuModel;// CPU型号

	@Column(name = "CPUFrequency")
	private Float cpuFrequency;// CPU频率
	
	@Column(name = "CPUAcount")
	private Integer cpuAcount;// CPU数量

	@Column(name = "treeBuffer")
	private Float treeBuffer;// 三级缓存

	@Column(name = "busStand")
	private String busStand;// 总线规格
	
	@Column(name = "CPUCores")
	private Integer cpuCores;// CPU核数

	@Column(name = "CPUThreads")
	private Integer cpuThreads;// CPU线程数

	@Column(name = "memSlots")
	private Integer memSlots;//内存插槽数
	
	@Column(name = "allocatedMemSlots")
	private Integer allocatedMemSlots;//已使用内存插槽数
	
	@Column(name = "maxMemCapacity")
	private Float maxMemCapacity;// 最大内存容量
	
	@Column(name = "memCapacity")
	private Float memCapacity;// 内存容量

	@Column(name = "stanDiskCapacity")
	private Float stanDiskCapacity;// 标配硬盘容量

	@Column(name = "maxDiskCapacity")
	private Float maxDiskCapacity;// 最大硬盘容量
	
	@Column(name = "diskRacks")
	private Integer diskRacks;// 硬盘架数

	@Column(name = "diskRacksNorm")
	private String diskRacksNorm;// 硬盘架规格

	@Column(name = "allocatedDiskRacks")
	private Integer allocatedDiskRacks;//已使用硬盘架数
	
	@Column(name = "diskCapacity")
	private Float diskCapacity;// 硬盘容量
	
	@Column(name = "netCarAcount")
	private Integer netCarAcount;// 网卡数量

	@Column(name = "bandwidth")
	private Float bandwidth;// 网卡带宽
	
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
		return em().find(Server.class, id);
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

	public ServerType getType() {
		return type;
	}

	public void setType(ServerType type) {
		this.type = type;
	}

	public IsVirtual getFlag() {
		return flag;
	}

	public void setFlag(IsVirtual flag) {
		this.flag = flag;
	}

	public String getVpaltform() {
		return vpaltform;
	}

	public void setVpaltform(String vpaltform) {
		this.vpaltform = vpaltform;
	}



	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public ServerState getState() {
		return state;
	}

	public void setState(ServerState state) {
		this.state = state;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public ServerCategory getCategory() {
		return category;
	}

	public void setCategory(ServerCategory category) {
		this.category = category;
	}

	public String getStructure() {
		return structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}




	public Float getTreeBuffer() {
		return treeBuffer;
	}

	public void setTreeBuffer(Float treeBuffer) {
		this.treeBuffer = treeBuffer;
	}

	public String getBusStand() {
		return busStand;
	}

	public void setBusStand(String busStand) {
		this.busStand = busStand;
	}


	public String getCpuType() {
		return cpuType;
	}

	public void setCpuType(String cpuType) {
		this.cpuType = cpuType;
	}

	public String getCpuModel() {
		return cpuModel;
	}

	public void setCpuModel(String cpuModel) {
		this.cpuModel = cpuModel;
	}

	public Float getCpuFrequency() {
		return cpuFrequency;
	}

	public void setCpuFrequency(Float cpuFrequency) {
		this.cpuFrequency = cpuFrequency;
	}

	public Integer getCpuAcount() {
		return cpuAcount;
	}

	public void setCpuAcount(Integer cpuAcount) {
		this.cpuAcount = cpuAcount;
	}

	public Integer getCpuCores() {
		return cpuCores;
	}

	public void setCpuCores(Integer cpuCores) {
		this.cpuCores = cpuCores;
	}

	public Integer getCpuThreads() {
		return cpuThreads;
	}

	public void setCpuThreads(Integer cpuThreads) {
		this.cpuThreads = cpuThreads;
	}

	public Integer getMemSlots() {
		return memSlots;
	}

	public void setMemSlots(Integer memSlots) {
		this.memSlots = memSlots;
	}

	public Integer getAllocatedMemSlots() {
		return allocatedMemSlots;
	}

	public void setAllocatedMemSlots(Integer allocatedMemSlots) {
		this.allocatedMemSlots = allocatedMemSlots;
	}

	public Float getMaxMemCapacity() {
		return maxMemCapacity;
	}

	public void setMaxMemCapacity(Float maxMemCapacity) {
		this.maxMemCapacity = maxMemCapacity;
	}

	public Float getMemCapacity() {
		return memCapacity;
	}

	public void setMemCapacity(Float memCapacity) {
		this.memCapacity = memCapacity;
	}

	public Float getStanDiskCapacity() {
		return stanDiskCapacity;
	}

	public void setStanDiskCapacity(Float stanDiskCapacity) {
		this.stanDiskCapacity = stanDiskCapacity;
	}

	public Float getMaxDiskCapacity() {
		return maxDiskCapacity;
	}

	public void setMaxDiskCapacity(Float maxDiskCapacity) {
		this.maxDiskCapacity = maxDiskCapacity;
	}

	public Integer getDiskRacks() {
		return diskRacks;
	}

	public void setDiskRacks(Integer diskRacks) {
		this.diskRacks = diskRacks;
	}

	public String getDiskRacksNorm() {
		return diskRacksNorm;
	}

	public void setDiskRacksNorm(String diskRacksNorm) {
		this.diskRacksNorm = diskRacksNorm;
	}

	public Integer getAllocatedDiskRacks() {
		return allocatedDiskRacks;
	}

	public void setAllocatedDiskRacks(Integer allocatedDiskRacks) {
		this.allocatedDiskRacks = allocatedDiskRacks;
	}

	public Float getDiskCapacity() {
		return diskCapacity;
	}

	public void setDiskCapacity(Float diskCapacity) {
		this.diskCapacity = diskCapacity;
	}

	public Integer getNetCarAcount() {
		return netCarAcount;
	}

	public void setNetCarAcount(Integer netCarAcount) {
		this.netCarAcount = netCarAcount;
	}

	public Float getBandwidth() {
		return bandwidth;
	}

	public void setBandwidth(Float bandwidth) {
		this.bandwidth = bandwidth;
	}
	
	

}
