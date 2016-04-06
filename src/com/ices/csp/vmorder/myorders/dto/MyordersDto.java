package com.ices.csp.vmorder.myorders.dto;

import org.cspframework.core.dto.annotation.DtoClass;
import org.cspframework.core.dto.annotation.DtoProperty;

import com.ices.csp.vmorder.domain.VMOrder;

@DtoClass(entities = VMOrder.class)
public class MyordersDto {
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
	 * 0:applying 申请中 1:audited 已审批 2:dispatched 已下达 3: canceled 已取消 4: finished 已完成
	 */
	@DtoProperty(entityClass=VMOrder.class, entityProperty="state.value", readOnly=true)
	private String state;
	/**
	 *  数量
	 */
	@DtoProperty(entityClass = VMOrder.class)
	private Integer amount;
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
	
	/**
	 *  审批人
	 */
	@DtoProperty(entityClass = VMOrder.class,entityProperty="auditor.name")
	private String auditor;
	
	/**
	 * 审批日期
	 */
	@DtoProperty(entityClass = VMOrder.class)
	private String auditDate;
	
	/**
	 * 审批意见 0:未通过；1:通过；2:部分通过
	 */
	@DtoProperty(entityClass = VMOrder.class, entityProperty="auditOpinion.value", readOnly=true)
	private String auditOpinion;
	/**
	 * 审批说明
	 */
	@DtoProperty(entityClass = VMOrder.class)
	private String auditDesc;
	/**
	 * 下达人
	 */
	@DtoProperty(entityClass = VMOrder.class,entityProperty="dispatcher.name")
	private String dispatcher;
	
	/**
	 * 下达日期
	 */
	@DtoProperty(entityClass = VMOrder.class)
	private String dispatchDate;
	
	
	public String getAuditDesc() {
		return auditDesc;
	}
	public void setAuditDesc(String auditDesc) {
		this.auditDesc = auditDesc;
	}
	public String getAuditor() {
		return auditor;
	}
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	public String getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}
	public String getAuditOpinion() {
		return auditOpinion;
	}
	public void setAuditOpinion(String auditOpinion) {
		this.auditOpinion = auditOpinion;
	}
	public String getDispatcher() {
		return dispatcher;
	}
	public void setDispatcher(String dispatcher) {
		this.dispatcher = dispatcher;
	}
	public String getDispatchDate() {
		return dispatchDate;
	}
	public void setDispatchDate(String dispatchDate) {
		this.dispatchDate = dispatchDate;
	}
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
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
	
	

}
