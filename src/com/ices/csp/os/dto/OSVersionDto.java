package com.ices.csp.os.dto;

import org.cspframework.core.dto.annotation.DtoClass;
import org.cspframework.core.dto.annotation.DtoProperty;

import com.ices.csp.os.domain.OSVersion;

@DtoClass(entities = OSVersion.class)
public class OSVersionDto {
	/**
	 * 主键 版本id
	 */
	@DtoProperty(entityClass = OSVersion.class)
	private Integer id;

	/**
	 * 版本编码，唯一，非空
	 */

	@DtoProperty(entityClass = OSVersion.class)
	private String code;
	/**
	 * 版本名称，非空
	 */
	@DtoProperty(entityClass = OSVersion.class)
	private String name;	
	/**
	 * 说明
	 */
	@DtoProperty(entityClass = OSVersion.class)
	private String desc;
	
	@DtoProperty(entityClass=OSVersion.class,entityProperty="os.id")
	private Integer osId;//
	
	
	
	public Integer getOsId() {
		return osId;
	}
	public void setOsId(Integer osId) {
		this.osId = osId;
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
