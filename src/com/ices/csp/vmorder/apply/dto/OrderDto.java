package com.ices.csp.vmorder.apply.dto;

import org.cspframework.core.dto.annotation.DtoClass;
import org.cspframework.core.dto.annotation.DtoProperty;

import com.ices.csp.vmorder.domain.VMItem;

@DtoClass(entities = VMItem.class)
public class OrderDto {
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
	
	@DtoProperty(entityClass=VMItem.class,entityProperty="vmorder.id")
	private Integer vmorderId;//
	
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
	 * 租用模式 0:按需、1:预留(包年包月)
	 */
	@DtoProperty(entityClass=VMItem.class, entityProperty="rentType.value", readOnly=true)
	private String rentType;
	/**
	 * 开始时间
	 */
	@DtoProperty(entityClass = VMItem.class)
	private String initDate;
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
	public Integer getVmorderId() {
		return vmorderId;
	}
	public void setVmorderId(Integer vmorderId) {
		this.vmorderId = vmorderId;
	}

	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getOsversion() {
		return osversion;
	}
	public void setOsversion(String osversion) {
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
