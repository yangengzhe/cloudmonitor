package com.ices.csp.os.dto;

import java.util.List;

import org.cspframework.core.dto.annotation.DtoClass;
import org.cspframework.core.dto.annotation.DtoProperty;

import com.ices.csp.os.domain.OS;

@DtoClass(entities = OS.class)
public class OSDto {
	/**
	 * 主键 操作系统id
	 */
	@DtoProperty(entityClass = OS.class)
	private Integer id;

	/**
	 * 操作系统编码，唯一，非空
	 */

	@DtoProperty(entityClass = OS.class)
	private String code;
	/**
	 * 操作系统名称，非空
	 */
	@DtoProperty(entityClass = OS.class)
	private String name;	
	/**
	 * 说明
	 */
	@DtoProperty(entityClass = OS.class)
	private String desc;
	
	private List<OSVersionDto> veisions;
	
	
	public List<OSVersionDto> getVeisions() {
		return veisions;
	}
	public void setVeisions(List<OSVersionDto> veisions) {
		this.veisions = veisions;
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
