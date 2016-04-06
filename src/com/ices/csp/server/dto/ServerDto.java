package com.ices.csp.server.dto;


import org.cspframework.core.dto.annotation.DtoClass;
import org.cspframework.core.dto.annotation.DtoProperty;

import com.ices.csp.server.domain.Server;

@DtoClass(entities = Server.class)
public class ServerDto {
	/**
	 * 主键 节点id
	 */
	@DtoProperty(entityClass = Server.class)
	private Integer id;//服务器id
	
	@DtoProperty(entityClass = Server.class)
	private String code;// 服务器编码，唯一，非空

	@DtoProperty(entityClass = Server.class)
	private String name;// 服务器名称，非空
	
	@DtoProperty(entityClass=Server.class,entityProperty="node.name")
	private String nodeName;//服务器所属节点名称
	/**
	* 0:计算；1:存储
	 */
	@DtoProperty(entityClass=Server.class, entityProperty="type.value", readOnly=true)
	private String type;// 服务器类型，0:计算；1:存储
	/**
	* 0:未虚拟化；1:已虚拟化
	 */
	@DtoProperty(entityClass=Server.class, entityProperty="flag.value", readOnly=true)
	private String flag;// 是否虚拟化

	@DtoProperty(entityClass = Server.class)
	private String vpaltform;// 虚拟化平台
	
	@DtoProperty(entityClass = Server.class)
	private String ip;// IP地址
	/**
	* 0:停机；1:开机
	 */
	@DtoProperty(entityClass=Server.class, entityProperty="state.value", readOnly=true)
	private String state;// 状态，0:停机；1:开机

	@DtoProperty(entityClass = Server.class)
	private String user;// 登陆名
	
	@DtoProperty(entityClass = Server.class)
	private String password;// 密码

	@DtoProperty(entityClass = Server.class)
	private String desc;// 说明

	@DtoProperty(entityClass = Server.class)
	private String producer;// 生产商
	
	@DtoProperty(entityClass = Server.class)
	private String model;// 型号
	/**
	* 0:机架；1:塔式；2:刀片
	 */
	@DtoProperty(entityClass=Server.class, entityProperty="category.value", readOnly=true)
	private String category;// 类别，0:机架；1:塔式；2:刀片

	@DtoProperty(entityClass = Server.class)
	private String structure;// 结构
	
	@DtoProperty(entityClass = Server.class)
	private String cpuType;// CPU类型

	@DtoProperty(entityClass = Server.class)
	private String cpuModel;// CPU型号

	@DtoProperty(entityClass = Server.class)
	private Float cpuFrequency;// CPU频率
	
	@DtoProperty(entityClass = Server.class)
	private Integer cpuAcount;// CPU数量

	@DtoProperty(entityClass = Server.class)
	private Float treeBuffer;// 三级缓存

	@DtoProperty(entityClass = Server.class)
	private String busStand;// 总线规格
	
	@DtoProperty(entityClass = Server.class)
	private Integer cpuCores;// CPU核数

	@DtoProperty(entityClass = Server.class)
	private Integer cpuThreads;// CPU线程数

	@DtoProperty(entityClass = Server.class)
	private Integer memSlots;//内存插槽数
	
	@DtoProperty(entityClass = Server.class)
	private Integer allocatedMemSlots;//已使用内存插槽数
	
	@DtoProperty(entityClass = Server.class)
	private Float maxMemCapacity;// 最大内存容量
	
	@DtoProperty(entityClass = Server.class)
	private Float memCapacity;// 内存容量

	@DtoProperty(entityClass = Server.class)
	private Float stanDiskCapacity;// 标配硬盘容量

	@DtoProperty(entityClass = Server.class)
	private Float maxDiskCapacity;// 最大硬盘容量
	
	@DtoProperty(entityClass = Server.class)
	private Integer diskRacks;// 硬盘架数

	@DtoProperty(entityClass = Server.class)
	private String diskRacksNorm;// 硬盘架规格

	@DtoProperty(entityClass = Server.class)
	private Integer allocatedDiskRacks;//已使用硬盘架数
	
	@DtoProperty(entityClass = Server.class)
	private Float diskCapacity;// 硬盘容量
	
	@DtoProperty(entityClass = Server.class)
	private Integer netCarAcount;// 网卡数量

	@DtoProperty(entityClass = Server.class)
	private Float bandwidth;// 网卡带宽

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

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
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
