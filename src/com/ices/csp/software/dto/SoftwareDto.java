/**
 * 
 */
package com.ices.csp.software.dto;

import org.cspframework.core.dto.annotation.DtoClass;
import org.cspframework.core.dto.annotation.DtoProperty;

import com.ices.csp.software.domain.Software;


/**
 * 数据传输类-软件
 * @author Fanchao Meng
 *
 */
@DtoClass(entities={Software.class})
public class SoftwareDto {
	@DtoProperty(entityClass=Software.class)
	private Integer id;//关键字
	
	@DtoProperty(entityClass=Software.class)
	private String code;//编码
	
	@DtoProperty(entityClass=Software.class)
	private String name;//名称
	
	@DtoProperty(entityClass=Software.class)
	private String version;//版本
	
	@DtoProperty(entityClass=Software.class, entityProperty="type.id")
	private Integer typeId;//软件类型Id
	
	@DtoProperty(entityClass=Software.class, entityProperty="type.name")
	private String typeName;//软件类型Name
	
	@DtoProperty(entityClass=Software.class)
	private String provider;//提供者
	
	@DtoProperty(entityClass=Software.class)
	private Float size;//大小
	
	@DtoProperty(entityClass=Software.class)
	private String codeUrl;//代码位置
	
	@DtoProperty(entityClass=Software.class)
	private String docUrl;//文档位置
	
	@DtoProperty(entityClass=Software.class)
	private String demo;//描述

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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public Float getSize() {
		return size;
	}

	public void setSize(Float size) {
		this.size = size;
	}

	public String getCodeUrl() {
		return codeUrl;
	}

	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}

	public String getDocUrl() {
		return docUrl;
	}

	public void setDocUrl(String docUrl) {
		this.docUrl = docUrl;
	}

	public String getDemo() {
		return demo;
	}

	public void setDemo(String demo) {
		this.demo = demo;
	}
	
	

}
