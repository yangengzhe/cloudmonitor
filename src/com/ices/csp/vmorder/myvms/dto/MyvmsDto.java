package com.ices.csp.vmorder.myvms.dto;

import org.cspframework.core.dto.annotation.DtoClass;
import org.cspframework.core.dto.annotation.DtoProperty;

import com.ices.csp.vmorder.domain.VMItem;

@DtoClass(entities = VMItem.class)
public class MyvmsDto {
	/**
	 * 云主机订单id，标识，主关键字
	 */
	@DtoProperty(entityClass = VMItem.class)
	private Integer id;
	/**
	 *  序号
	 */
	@DtoProperty(entityClass = VMItem.class)
	private Integer no;
	//订单编号，vmorder
	@DtoProperty(entityClass=VMItem.class,entityProperty="vmorder.number")
	private String number;//
	// 云主机编号 vmitem
	@DtoProperty(entityClass = VMItem.class)
	private String vmnumber;
	// 云主机名
	@DtoProperty(entityClass = VMItem.class)
	private String vmname;
	/**
	 * 云主机状态  0:applying 申请中  1:created 已创建 2:canceled 已取消  3:expired 已到期
	 */
	@DtoProperty(entityClass=VMItem.class, entityProperty="state.value", readOnly=true)
	private String state;
	
	/**
	 * 运行状态 0:关机；1:开机；2：待机
	 */
	@DtoProperty(entityClass=VMItem.class, entityProperty="runstate.value", readOnly=true)
	private String runstate;
	/**
	 * 节点名称
	 */
	@DtoProperty(entityClass=VMItem.class,entityProperty="node.name")
	private String node;	
	/**
	 * 节点名称
	 */
	@DtoProperty(entityClass=VMItem.class,entityProperty="node.name")
	private String nodeName;
	/**
	 * 版本名称
	 */
	@DtoProperty(entityClass=VMItem.class,entityProperty="osversion.name")
	private String osversion;
	// IP地址
	@DtoProperty(entityClass = VMItem.class)
	private String ip;
	// 登陆名
	@DtoProperty(entityClass = VMItem.class)
	private String accout;
	/**
	 * CPU核数 单位：个
	 */
	@DtoProperty(entityClass = VMItem.class)
	private Integer cpu;
	/**
	 * 内存大小 单位：GB
	 */
	@DtoProperty(entityClass = VMItem.class)
	private Integer mem;	
	/**
	 * 硬盘大小 单位：GB
	 */
	@DtoProperty(entityClass = VMItem.class)
	private Integer disk;
	/**
	 * 网络接入模式  0:内网、1:联通、2:电信
	 */
	@DtoProperty(entityClass=VMItem.class, entityProperty="netType.value", readOnly=true)
	private String netType;
	/**
	 * 网络带宽
	 */
	@DtoProperty(entityClass = VMItem.class)
	private Float bandwidth;
	/**
	 *  0:不创建；1:创建
	 */
	@DtoProperty(entityClass=VMItem.class, entityProperty="iscreated.value", readOnly=true)
	private String iscreated;
	/**
	 * 租用模式 0:按需、1:预留(包年包月)
	 */
	@DtoProperty(entityClass=VMItem.class, entityProperty="rentType.value", readOnly=true)
	private String rentType;
	/**
	 * 开始时间
	 */
	@DtoProperty(entityClass = VMItem.class)
	private String initDate;
	// 密码
	@DtoProperty(entityClass = VMItem.class)
	private String password;
	// 子网掩码
	@DtoProperty(entityClass = VMItem.class)
	private String subnetMask;
	// 默认网关
	@DtoProperty(entityClass = VMItem.class)
	private String gateway;
	// 首选DNS服务器
	@DtoProperty(entityClass = VMItem.class)
	private String dnsServer1;
	// 备选DNS服务器
	@DtoProperty(entityClass = VMItem.class)
	private String dnsServer2;
	// 域名
	@DtoProperty(entityClass = VMItem.class)
	private String dns;

	private String renttime;// 租用时间 ，后台配置
	

	private String config;//配置（cpu、内存、硬盘、带宽），后台返回时进行设置
	private String valid;//有效期，后台设置
	private String remaintime;//剩余时间，后台设置
	private String serverName;
	
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	/**
	 * 租期
	 */
	@DtoProperty(entityClass = VMItem.class)
	private Integer tenancy;	
	/**
	 * 租期单位 0:小时、1:天、2:月、3:年
	 */
	@DtoProperty(entityClass=VMItem.class, entityProperty="unit.value", readOnly=true)
	private String unit;
	
	
	
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getIscreated() {
		return iscreated;
	}
	public void setIscreated(String iscreated) {
		this.iscreated = iscreated;
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
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getRunstate() {
		return runstate;
	}
	public void setRunstate(String runstate) {
		this.runstate = runstate;
	}
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public String getOsversion() {
		return osversion;
	}
	public void setOsversion(String osversion) {
		this.osversion = osversion;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getAccout() {
		return accout;
	}
	public void setAccout(String accout) {
		this.accout = accout;
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
	public String getNetType() {
		return netType;
	}
	public void setNetType(String netType) {
		this.netType = netType;
	}
	public Float getBandwidth() {
		return bandwidth;
	}
	public void setBandwidth(Float bandwidth) {
		this.bandwidth = bandwidth;
	}
	public String getRentType() {
		return rentType;
	}
	public void setRentType(String rentType) {
		this.rentType = rentType;
	}
	public String getInitDate() {
		return initDate;
	}
	public void setInitDate(String initDate) {
		this.initDate = initDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getRenttime() {
		return renttime;
	}
	public void setRenttime(String renttime) {
		this.renttime = renttime;
	}
	public String getConfig() {
		return config;
	}
	public void setConfig(String config) {
		this.config = config;
	}
	public String getValid() {
		return valid;
	}
	public void setValid(String valid) {
		this.valid = valid;
	}
	public String getRemaintime() {
		return remaintime;
	}
	public void setRemaintime(String remaintime) {
		this.remaintime = remaintime;
	}
	public Integer getTenancy() {
		return tenancy;
	}
	public void setTenancy(Integer tenancy) {
		this.tenancy = tenancy;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
	
}
