package com.ices.csp.vmorder.domain;

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
import org.cspframework.manage.enterprise.domain.Enterprise;
import org.cspframework.manage.user.domain.LoginUser;
import org.cspframework.manage.user.domain.User;

import com.ices.csp.common.enumitem.IsCreated;
import com.ices.csp.common.enumitem.NetType;
import com.ices.csp.common.enumitem.RentType;
import com.ices.csp.common.enumitem.RunState;
import com.ices.csp.common.enumitem.TenancyUnit;
import com.ices.csp.common.enumitem.VMItemState;
import com.ices.csp.node.domain.Node;
import com.ices.csp.os.domain.OSVersion;
import com.ices.csp.server.domain.Server;

@Entity
@Table(name = "csp_vmitem")
public class VMItem implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;// 云主机订单id，标识，主关键字

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "vmOrderId")
	private VMOrder vmorder;// 云主机订单

	@Column(name = "no")
	private Integer no;// 序号
	/**
	* 0:applying 申请中  1:created 已创建 2:canceled 已取消  3:expired 已到期
	 */
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "state")
	private VMItemState state;// 云主机状态  0:applying 申请中  1:created 已创建 2:canceled 已取消  3:expired 已到期
	/**
	*  0:不创建；1:创建
	 */
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "IsCreated")
	private IsCreated iscreated;// 是否创建 0:不创建；1:创建
	/**
	*  0:关机；1:开机；2：待机
	 */
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "runState")
	private RunState runstate;// 运行状态 0:关机；1:开机；2：待机

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "nodeId")
	private Node node;// 节点
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "serverId")
	private Server server;// 服务器
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "osVersionId")
	private OSVersion osversion;// 操作系统(版本)
	
	@Column(name = "cpu")
	private Integer cpu;// CPU核数 单位：个
	
	@Column(name = "mem")
	private Integer mem;// 内存大小 单位：GB
	
	@Column(name = "disk")
	private Integer disk;// 硬盘大小 单位：GB
	/**
	*   0:内网、1:联通、2:电信
	 */
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "netType")
	private NetType netType;// 网络接入模式  0:内网、1:联通、2:电信
	
	@Column(name = "bandwidth")
	private Float bandwidth;// 网络带宽
	/**
	*   0:按需、1:预留(包年包月)
	 */
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "rentType")
	private RentType rentType;// 租用模式 0:按需、1:预留(包年包月)
	
	@Column(name = "initDate")
	private String initDate;// 开始时间
	
	@Column(name = "tenancy")
	private Integer tenancy;// 租期
	/**
	*   0:小时、1:天、2:月、3:年
	 */
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "unit")
	private TenancyUnit unit;// 租期单位 0:小时、1:天、2:月、3:年
	
	@Column(name = "vmNumber")
	private String vmnumber;// 云主机编号
	
	@Column(name = "vmName")
	private String vmname;// 云主机名
	
	@Column(name = "accout")
	private String accout;// 登陆名
	
	@Column(name = "password")
	private String password;// 密码
	
	@Column(name = "IP")
	private String ip;// IP地址
	
	@Column(name = "subnetMask")
	private String subnetMask;// 子网掩码
	
	@Column(name = "Gateway")
	private String gateway;// 默认网关
	
	@Column(name = "DNSServer1")
	private String dnsServer1;// 首选DNS服务器
	
	@Column(name = "DNSServer2")
	private String dnsServer2;// 备选DNS服务器
	
	@Column(name = "DNS")
	private String dns;// 域名
	
	@Column(name = "uuid")
	private String uuid;// 红山虚拟化中的id，虚拟机的唯一标识
	
	public void insert() {

		em().persist(this);
	}

	public void delete() {

		em().remove(this);
	}
	public static List<VMItem> findByParam(String number, Node node,String vmname, VMItemState state,String formdate,String todate,RunState runstate) {
		LoginUser user = User.getCurrentUser();
		User me = User.findById(user.getUserId());
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("SELECT c FROM VMItem c WHERE 1=1 and c.vmorder.applicant = :me ");
		if (!"".equals(number)&&number!=null) {
			queryStr.append("AND c.vmorder.number LIKE :number ");
		}
		if (node!=null) {
			queryStr.append(" AND c.node = :node ");
		}
		if (!"".equals(vmname)&&vmname!=null) {
			queryStr.append("AND c.vmname LIKE :vmname ");
		}
		if (state!=null) {
			queryStr.append(" AND c.state = :state ");
		}
		if (!"".equals(formdate)&&formdate!=null) {
			queryStr.append(" AND c.initDate >  :formdate");
		}
		if (!"".equals(todate)&&todate!=null) {
			queryStr.append(" AND c.initDate <  :todate");
		}
		if (runstate!=null) {
			queryStr.append(" AND c.runstate = :runstate ");
		}
		queryStr.append(" ORDER BY c.initDate desc");
		TypedQuery<VMItem> query = em().createQuery(queryStr.toString(), VMItem.class);
		query.setParameter("me", me);
		if (!"".equals(number)&&number!=null) {
			query.setParameter("number", "%" + number + "%");
		}
		if (node!=null) {
			query.setParameter("node", node);
		}
		if (!"".equals(vmname)&&vmname!=null) {
			query.setParameter("vmname", "%" + vmname + "%");
		}
		if (state!=null) {
			query.setParameter("state", state);
		}
		if (!"".equals(formdate)&&formdate!=null) {
			query.setParameter("formdate", formdate);
		}
		if (!"".equals(todate)&&todate!=null) {
			query.setParameter("todate", todate);
		}
		if (runstate!=null) {
			query.setParameter("runstate", runstate);
		}
		return query.getResultList();
	}
	public static List<VMItem> findByParam2(String number, Node node,String vmname, Enterprise enterprise,String formdate,String todate,RunState runstate) {
		VMItemState state = VMItemState.created;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("SELECT c FROM VMItem c WHERE 1=1 and c.state = :state ");
		if (!"".equals(number)&&number!=null) {
			queryStr.append("AND c.vmorder.number LIKE :number ");
		}
		if (node!=null) {
			queryStr.append(" AND c.node = :node ");
		}
		if (!"".equals(vmname)&&vmname!=null) {
			queryStr.append("AND c.vmname LIKE :vmname ");
		}
		if (enterprise!=null) {
			queryStr.append(" AND c.vmorder.enterprise = :enterprise ");
		}
		if (!"".equals(formdate)&&formdate!=null) {
			queryStr.append(" AND c.initDate >  :formdate");
		}
		if (!"".equals(todate)&&todate!=null) {
			queryStr.append(" AND c.initDate <  :todate");
		}
		if (runstate!=null) {
			queryStr.append(" AND c.runstate = :runstate ");
		}
		queryStr.append(" ORDER BY c.initDate desc");
		TypedQuery<VMItem> query = em().createQuery(queryStr.toString(), VMItem.class);
		query.setParameter("state", state);
		if (!"".equals(number)&&number!=null) {
			query.setParameter("number", "%" + number + "%");
		}
		if (node!=null) {
			query.setParameter("node", node);
		}
		if (!"".equals(vmname)&&vmname!=null) {
			query.setParameter("vmname", "%" + vmname + "%");
		}
		if (enterprise!=null) {
			query.setParameter("enterprise", enterprise);
		}
		if (!"".equals(formdate)&&formdate!=null) {
			query.setParameter("formdate", formdate);
		}
		if (!"".equals(todate)&&todate!=null) {
			query.setParameter("todate", todate);
		}
		if (runstate!=null) {
			query.setParameter("runstate", runstate);
		}
		return query.getResultList();
	}
	public static List<VMItem> findAll(PagingBean pb) {
		LoginUser user = User.getCurrentUser();
		User me = User.findById(user.getUserId());
		StringBuffer queryStr = new StringBuffer();
		queryStr.append( "SELECT c FROM VMItem c where c.vmorder.applicant = :me");
		StringBuffer sqlCount = new StringBuffer();
		sqlCount.append("select count(c) from VMItem  c where 1=1 and c.vmorder.applicant = :me");
		TypedQuery<VMItem> query = em().createQuery(queryStr.toString(), VMItem.class);
		TypedQuery<Long> countQuery = ThreadLocalManager.em().createQuery(sqlCount.toString(), Long.class);
		query.setParameter("me", me);
		countQuery.setParameter("me", me);
		return pb.getResultList(countQuery, query);
	}
	public static List<VMItem> findAllFinalMyvms(PagingBean pb) {
		VMItemState state = VMItemState.created;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append( "SELECT c FROM VMItem c where c.state = :state");
		StringBuffer sqlCount = new StringBuffer();
		sqlCount.append("select count(c) from VMItem  c where 1=1 and c.state = :state");
		TypedQuery<VMItem> query = em().createQuery(queryStr.toString(), VMItem.class);
		TypedQuery<Long> countQuery = ThreadLocalManager.em().createQuery(sqlCount.toString(), Long.class);
		query.setParameter("state", state);
		countQuery.setParameter("state", state);
		return pb.getResultList(countQuery, query);
	}
	public static List<VMItem> findItemsByOrderId(Integer orderid) {
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("SELECT c FROM VMItem c WHERE 1=1 ");
		if (orderid!=null) {
			queryStr.append(" AND c.vmorder.id = :orderid ");
		}
		TypedQuery<VMItem> query = em().createQuery(queryStr.toString(), VMItem.class);

		if (orderid!=null) {
			query.setParameter("orderid", orderid);
		}
		return query.getResultList();
	}
	// 根据id查询
	public static VMItem findById(Integer id) {
		return em().find(VMItem.class, id);
	}

	public String getDns() {
		return dns;
	}

	public void setDns(String dns) {
		this.dns = dns;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public VMOrder getVmorder() {
		return vmorder;
	}

	public void setVmorder(VMOrder vmorder) {
		this.vmorder = vmorder;
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public VMItemState getState() {
		return state;
	}

	public void setState(VMItemState state) {
		this.state = state;
	}

	public IsCreated getIscreated() {
		return iscreated;
	}

	public void setIscreated(IsCreated iscreated) {
		this.iscreated = iscreated;
	}

	public RunState getRunstate() {
		return runstate;
	}

	public void setRunstate(RunState runstate) {
		this.runstate = runstate;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public OSVersion getOsversion() {
		return osversion;
	}

	public void setOsversion(OSVersion osversion) {
		this.osversion = osversion;
	}

	public Integer getCpu() {
		return cpu;
	}

	public void setCpu(Integer cpu) {
		this.cpu = cpu;
	}

	public Integer getMem() {
		return mem;
	}

	public void setMem(Integer mem) {
		this.mem = mem;
	}

	public Integer getDisk() {
		return disk;
	}

	public void setDisk(Integer disk) {
		this.disk = disk;
	}

	public NetType getNetType() {
		return netType;
	}

	public void setNetType(NetType netType) {
		this.netType = netType;
	}

	public Float getBandwidth() {
		return bandwidth;
	}

	public void setBandwidth(Float bandwidth) {
		this.bandwidth = bandwidth;
	}

	public RentType getRentType() {
		return rentType;
	}

	public void setRentType(RentType rentType) {
		this.rentType = rentType;
	}



	public String getInitDate() {
		return initDate;
	}

	public void setInitDate(String initDate) {
		this.initDate = initDate;
	}

	public Integer getTenancy() {
		return tenancy;
	}

	public void setTenancy(Integer tenancy) {
		this.tenancy = tenancy;
	}

	public TenancyUnit getUnit() {
		return unit;
	}

	public void setUnit(TenancyUnit unit) {
		this.unit = unit;
	}

	public String getVmnumber() {
		return vmnumber;
	}

	public void setVmnumber(String vmnumber) {
		this.vmnumber = vmnumber;
	}

	public String getVmname() {
		return vmname;
	}

	public void setVmname(String vmname) {
		this.vmname = vmname;
	}

	public String getAccout() {
		return accout;
	}

	public void setAccout(String accout) {
		this.accout = accout;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSubnetMask() {
		return subnetMask;
	}

	public void setSubnetMask(String subnetMask) {
		this.subnetMask = subnetMask;
	}

	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	public String getDnsServer1() {
		return dnsServer1;
	}

	public void setDnsServer1(String dnsServer1) {
		this.dnsServer1 = dnsServer1;
	}

	public String getDnsServer2() {
		return dnsServer2;
	}

	public void setDnsServer2(String dnsServer2) {
		this.dnsServer2 = dnsServer2;
	}

	public Server getServer() {
		return server;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setServer(Server server) {
		this.server = server;
	}
	

}
