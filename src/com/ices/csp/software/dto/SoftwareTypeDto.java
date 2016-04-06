/**
 * 
 */
package com.ices.csp.software.dto;

import org.cspframework.core.dto.annotation.DtoClass;
import org.cspframework.core.dto.annotation.DtoProperty;

import com.ices.csp.software.domain.SoftwareType;


/**
 * @author Fanchao Meng
 *
 */
@DtoClass(entities={SoftwareType.class})
public class SoftwareTypeDto {
	@DtoProperty(entityClass=SoftwareType.class)
	private Integer id;
	
	@DtoProperty(entityClass=SoftwareType.class)
	private String code;
	
	@DtoProperty(entityClass=SoftwareType.class)
	private String name;
	
	@DtoProperty(entityClass=SoftwareType.class, entityProperty="parent.id")
	private Integer parentId;
	  
	@DtoProperty(entityClass=SoftwareType.class, entityProperty="parent.name")
	private String parentName;
	
	@DtoProperty(entityClass=SoftwareType.class)
	private String demo;

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

	
	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getDemo() {
		return demo;
	}

	public void setDemo(String demo) {
		this.demo = demo;
	}
	
}
