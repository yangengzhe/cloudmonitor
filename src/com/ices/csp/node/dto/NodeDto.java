package com.ices.csp.node.dto;

import org.cspframework.core.dto.annotation.DtoClass;
import org.cspframework.core.dto.annotation.DtoProperty;

import com.ices.csp.node.domain.Node;




@DtoClass(entities = Node.class)
public class NodeDto {
	/**
	 * 主键 节点id
	 */
	@DtoProperty(entityClass = Node.class)
	private Integer id;

	/**
	 * 节点编码，唯一，非空
	 */

	@DtoProperty(entityClass = Node.class)
	private String code;
	/**
	 * 节点名称，非空
	 */
	@DtoProperty(entityClass = Node.class)
	private String name;

	/**
	 * 负责人
	 */
	@DtoProperty(entityClass = Node.class)
	private String principal;
	/**
	 * 电话
	 */
	@DtoProperty(entityClass = Node.class)
	private String telephone;
	/**
	 * 手机
	 */
	@DtoProperty(entityClass = Node.class)
	private String mobile;
	/**
	 * 邮箱
	 */
	@DtoProperty(entityClass = Node.class)
	private String email;
	/**
	 * 节点地址
	 */
	@DtoProperty(entityClass = Node.class)
	private String address;
	
	/**
	 * 说明
	 */
	@DtoProperty(entityClass = Node.class)
	private String desc;

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

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
