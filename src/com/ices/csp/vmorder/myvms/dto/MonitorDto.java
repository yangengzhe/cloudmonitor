package com.ices.csp.vmorder.myvms.dto;

import org.cspframework.core.dto.annotation.DtoClass;
import org.cspframework.core.dto.annotation.DtoProperty;

import com.ices.csp.vmorder.domain.VMItemMonitor;

@DtoClass(entities = VMItemMonitor.class)
public class MonitorDto {
	/**
	 * 监控信息id，标识，主关键字
	 */
	@DtoProperty(entityClass = VMItemMonitor.class)
	private Integer id;
	/**
	 * 云主机订单标示
	 */
	@DtoProperty(entityClass=VMItemMonitor.class,entityProperty="vmitem.ip")
	private String vmitemMark;//
	// cpu使用率
	@DtoProperty(entityClass = VMItemMonitor.class)
	private Double cpuusagerate;
	// 内存使用率
	@DtoProperty(entityClass = VMItemMonitor.class)
	private Double memusagerate;
	// 时间点
	@DtoProperty(entityClass = VMItemMonitor.class)
	private String timepoint;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVmitemMark() {
		return vmitemMark;
	}
	public void setVmitemMark(String vmitemMark) {
		this.vmitemMark = vmitemMark;
	}
	public Double getCpuusagerate() {
		return cpuusagerate;
	}
	public void setCpuusagerate(Double cpuusagerate) {
		this.cpuusagerate = cpuusagerate;
	}
	public Double getMemusagerate() {
		return memusagerate;
	}
	public void setMemusagerate(Double memusagerate) {
		this.memusagerate = memusagerate;
	}
	public String getTimepoint() {
		return timepoint;
	}
	public void setTimepoint(String timepoint) {
		this.timepoint = timepoint;
	}
	
}
