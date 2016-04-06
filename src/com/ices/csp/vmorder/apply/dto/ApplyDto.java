package com.ices.csp.vmorder.apply.dto;

import java.util.List;

import org.cspframework.core.dto.annotation.DtoClass;
import org.cspframework.core.dto.annotation.DtoProperty;

import com.ices.csp.vmorder.domain.VMOrder;

@DtoClass(entities = VMOrder.class)
public class ApplyDto {
	/**
	 * 云主机订单id，标识，主关键字
	 */
	@DtoProperty(entityClass = VMOrder.class)
	private Integer id;
	/**
	 * 订单编号，唯一，非空
	 */
	@DtoProperty(entityClass = VMOrder.class)
	private String number;
	/**
	 *  租户
	 */
	@DtoProperty(entityClass=VMOrder.class,entityProperty="enterprise.name")
	private String enterprise;	
	/**
	 * 申请人
	 */
	@DtoProperty(entityClass=VMOrder.class,entityProperty="applicant.name")
	private String applicant;
	/**
	 * 申请日期
	 */
	@DtoProperty(entityClass = VMOrder.class)
	private String applyDate;
	/**
	 * 申请说明
	 */
	@DtoProperty(entityClass = VMOrder.class)
	private String applyDesc;	

	
	private List<OrderDto> orders;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public String getEnterprise() {
		return enterprise;
	}


	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
	}


	public String getApplicant() {
		return applicant;
	}


	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}


	public String getApplyDate() {
		return applyDate;
	}


	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}


	public String getApplyDesc() {
		return applyDesc;
	}


	public void setApplyDesc(String applyDesc) {
		this.applyDesc = applyDesc;
	}


	public List<OrderDto> getOrders() {
		return orders;
	}


	public void setOrders(List<OrderDto> orders) {
		this.orders = orders;
	}
	
	
}
