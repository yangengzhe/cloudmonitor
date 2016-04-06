package com.ices.csp.vmorder.domain;

import static org.cspframework.core.jpa.ThreadLocalManager.em;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import org.cspframework.common.PagingBean;
import org.cspframework.core.jpa.ThreadLocalManager;
import org.cspframework.manage.enterprise.domain.Enterprise;
import org.cspframework.manage.user.domain.LoginUser;
import org.cspframework.manage.user.domain.User;

import com.ices.csp.common.enumitem.AuditOpinion;
import com.ices.csp.common.enumitem.VMOrderState;

@Entity
@Table(name = "csp_vmorder")
public class VMOrder implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;// 云主机订单id，标识，主关键字

	@Column(name = "number")
	private String number;// 订单编号，唯一，非空
	/**
	* 0:applying 申请中 1:audited 已审批 2:dispatched 已下达 3: canceled 已取消 4: finished 已完成
	 */
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "state")
	private VMOrderState state;// 状态 ，0:applying 申请中 1:audited 已审批 2:dispatched 已下达 3: canceled 已取消 4: finished 已完成
	
	@Column(name = "amount")
	private Integer amount;// 数量
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "enterpriseId")
	private Enterprise enterprise;// 租户
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "applicantId")
	private User applicant;// 申请人
	
	@Column(name = "applyDate")
	private String applyDate;// 申请日期
	
	@Column(name = "applyDesc")
	private String applyDesc;// 申请说明
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "auditorId")
	private User auditor;// 审批人
	
	@Column(name = "auditDate")
	private String auditDate;// 审批日期
	/**
	* 0:未通过；1:通过；2:部分通过
	 */
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "auditOpinion")
	private AuditOpinion auditOpinion;// 审批意见 0:未通过；1:通过；2:部分通过
	
	@Column(name = "auditDesc")
	private String auditDesc;// 审批说明
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "dispatcherId")
	private User dispatcher;// 下达人
	
	@Column(name = "dispatchDate")
	private String dispatchDate;// 下达日期
	
	public static List<VMOrder> findByParam(String number, VMOrderState state,String formdate,String todate) {
		LoginUser user = User.getCurrentUser();
		User me = User.findById(user.getUserId());
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("SELECT c FROM VMOrder c WHERE 1=1 and c.applicant = :me ");
		if (!"".equals(number)&&number!=null) {
			queryStr.append("AND c.number LIKE :number ");
		}
		if (state!=null) {
			queryStr.append(" AND c.state = :state ");
		}
		if (!"".equals(formdate)&&formdate!=null) {
			queryStr.append(" AND c.applyDate >  :formdate");
		}
		if (!"".equals(todate)&&todate!=null) {
			queryStr.append(" AND c.applyDate <  :todate");
		}
		queryStr.append(" ORDER BY c.applyDate desc");
		TypedQuery<VMOrder> query = em().createQuery(queryStr.toString(), VMOrder.class);
		query.setParameter("me", me);
		if (!"".equals(number)&&number!=null) {
			query.setParameter("number", "%" + number + "%");
		}
		if (state!=null) {
			query.setParameter("state", state);
		}
		if (!"".equals(formdate)&&formdate!=null) {
			query.setParameter("formdate", formdate);
		}
		if (!"".equals(todate)&&todate!=null) {
			query.setParameter("todate", todate);
		}
		return query.getResultList();
	}
	public static List<VMOrder> findByParam2(String number, Enterprise enterprise,String formdate,String todate) {
		VMOrderState state  = VMOrderState.finished;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("SELECT c FROM VMOrder c WHERE 1=1 and c.state = :state");
		if (!"".equals(number)&&number!=null) {
			queryStr.append(" AND c.number LIKE :number ");
		}
		if (enterprise!=null) {
			queryStr.append(" AND c.enterprise = :enterprise ");
		}
		if (!"".equals(formdate)&&formdate!=null) {
			queryStr.append(" AND c.applyDate >  :formdate");
		}
		if (!"".equals(todate)&&todate!=null) {
			queryStr.append(" AND c.applyDate <  :todate");
		}
		queryStr.append(" ORDER BY c.applyDate desc");
		TypedQuery<VMOrder> query = em().createQuery(queryStr.toString(), VMOrder.class);
		query.setParameter("state", state);
		if (!"".equals(number)&&number!=null) {
			query.setParameter("number", "%" + number + "%");
		}
		if (enterprise!=null) {
			query.setParameter("enterprise", enterprise);
		}
		if (!"".equals(formdate)&&formdate!=null) {
			query.setParameter("formdate", formdate);
		}
		if (!"".equals(todate)&&todate!=null) {
			query.setParameter("todate", todate);
		}
		return query.getResultList();
	}
	
	public static List<VMOrder> findAll(PagingBean pb) {
		LoginUser user = User.getCurrentUser();
		User me = User.findById(user.getUserId());
		StringBuffer queryStr = new StringBuffer();
		queryStr.append( "SELECT c FROM VMOrder c where c.applicant = :me");
		StringBuffer sqlCount = new StringBuffer();
		sqlCount.append("select count(c) from VMOrder  c where 1=1 and c.applicant = :me");
		TypedQuery<VMOrder> query = em().createQuery(queryStr.toString(), VMOrder.class);
		TypedQuery<Long> countQuery = ThreadLocalManager.em().createQuery(sqlCount.toString(), Long.class);
		query.setParameter("me", me);
		countQuery.setParameter("me", me);
		return pb.getResultList(countQuery, query);
	}
	public static List<VMOrder> findAllFinalorders(PagingBean pb) {
		VMOrderState state  = VMOrderState.finished;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append( "SELECT c FROM VMOrder c where c.state = :state");
		StringBuffer sqlCount = new StringBuffer();
		sqlCount.append("select count(c) from VMOrder  c where 1=1 and c.state = :state");
		TypedQuery<VMOrder> query = em().createQuery(queryStr.toString(), VMOrder.class);
		TypedQuery<Long> countQuery = ThreadLocalManager.em().createQuery(sqlCount.toString(), Long.class);
		query.setParameter("state", state);
		countQuery.setParameter("state", state);
		return pb.getResultList(countQuery, query);
	}
	public static List<VMOrder> findVMOrderISApply(PagingBean pb) {
		VMOrderState state  = VMOrderState.applying;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append( "SELECT c FROM VMOrder c where c.state = :state");
		StringBuffer sqlCount = new StringBuffer();
		sqlCount.append("select count(c) from VMOrder  c where 1=1 and c.state = :state");
		TypedQuery<VMOrder> query = em().createQuery(queryStr.toString(), VMOrder.class);
		TypedQuery<Long> countQuery = ThreadLocalManager.em().createQuery(sqlCount.toString(), Long.class);
		query.setParameter("state", state);
		countQuery.setParameter("state", state);
		return pb.getResultList(countQuery, query);
	}
	public static List<VMOrder> findVMOrderForDispatch(PagingBean pb) {
		VMOrderState state  = VMOrderState.audited;
		VMOrderState state2  = VMOrderState.canceled;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append( "SELECT c FROM VMOrder c where c.state = :state or c.state = :state2");
		StringBuffer sqlCount = new StringBuffer();
		sqlCount.append("select count(c) from VMOrder  c where 1=1 and c.state = :state or c.state = :state2");
		TypedQuery<VMOrder> query = em().createQuery(queryStr.toString(), VMOrder.class);
		TypedQuery<Long> countQuery = ThreadLocalManager.em().createQuery(sqlCount.toString(), Long.class);
		query.setParameter("state", state);
		countQuery.setParameter("state", state);
		query.setParameter("state2", state2);
		countQuery.setParameter("state2", state2);
		return pb.getResultList(countQuery, query);
	}
	public static List<VMOrder> findVMOrderForCreatevms(PagingBean pb) {
		VMOrderState state  = VMOrderState.dispatched;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append( "SELECT c FROM VMOrder c where c.state = :state ");
		StringBuffer sqlCount = new StringBuffer();
		sqlCount.append("select count(c) from VMOrder  c where 1=1 and c.state = :state ");
		TypedQuery<VMOrder> query = em().createQuery(queryStr.toString(), VMOrder.class);
		TypedQuery<Long> countQuery = ThreadLocalManager.em().createQuery(sqlCount.toString(), Long.class);
		query.setParameter("state", state);
		countQuery.setParameter("state", state);
		return pb.getResultList(countQuery, query);
	}
	public static List<VMOrder> findByParamForAudit(String number, Enterprise enterprise,String formdate,String todate) {
		VMOrderState state  = VMOrderState.applying;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("SELECT c FROM VMOrder c WHERE 1=1 and c.state = :state");
		if (!"".equals(number)&&number!=null) {
			queryStr.append(" AND c.number LIKE :number ");
		}
		if (enterprise!=null) {
			queryStr.append(" AND c.enterprise = :enterprise ");
		}
		if (!"".equals(formdate)&&formdate!=null) {
			queryStr.append(" AND c.applyDate >  :formdate");
		}
		if (!"".equals(todate)&&todate!=null) {
			queryStr.append(" AND c.applyDate <  :todate");
		}
		queryStr.append(" ORDER BY c.applyDate desc");
		TypedQuery<VMOrder> query = em().createQuery(queryStr.toString(), VMOrder.class);
		query.setParameter("state", state);
		if (!"".equals(number)&&number!=null) {
			query.setParameter("number", "%" + number + "%");
		}
		if (enterprise!=null) {
			query.setParameter("enterprise", enterprise);
		}
		if (!"".equals(formdate)&&formdate!=null) {
			query.setParameter("formdate", formdate);
		}
		if (!"".equals(todate)&&todate!=null) {
			query.setParameter("todate", todate);
		}
		return query.getResultList();
	}
	public static List<VMOrder> findByParamForDispatch(String number, Enterprise enterprise,String formdate,String todate) {
		VMOrderState state  = VMOrderState.audited;
		VMOrderState state2  = VMOrderState.canceled;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("SELECT c FROM VMOrder c WHERE 1=1 and (c.state = :state or c.state = :state2)");
		if (!"".equals(number)&&number!=null) {
			queryStr.append(" AND c.number LIKE :number ");
		}
		if (enterprise!=null) {
			queryStr.append(" AND c.enterprise = :enterprise ");
		}
		if (!"".equals(formdate)&&formdate!=null) {
			queryStr.append(" AND c.applyDate >  :formdate");
		}
		if (!"".equals(todate)&&todate!=null) {
			queryStr.append(" AND c.applyDate <  :todate");
		}
		queryStr.append(" ORDER BY c.applyDate desc");
		TypedQuery<VMOrder> query = em().createQuery(queryStr.toString(), VMOrder.class);
		query.setParameter("state", state);
		query.setParameter("state2", state2);
		if (!"".equals(number)&&number!=null) {
			query.setParameter("number", "%" + number + "%");
		}
		if (enterprise!=null) {
			query.setParameter("enterprise", enterprise);
		}
		if (!"".equals(formdate)&&formdate!=null) {
			query.setParameter("formdate", formdate);
		}
		if (!"".equals(todate)&&todate!=null) {
			query.setParameter("todate", todate);
		}
		return query.getResultList();
	}
	public static List<VMOrder> findByParamForCreatevms(String number, Enterprise enterprise,String formdate,String todate) {
		VMOrderState state  = VMOrderState.dispatched;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("SELECT c FROM VMOrder c WHERE 1=1 and c.state = :state");
		if (!"".equals(number)&&number!=null) {
			queryStr.append(" AND c.number LIKE :number ");
		}
		if (enterprise!=null) {
			queryStr.append(" AND c.enterprise = :enterprise ");
		}
		if (!"".equals(formdate)&&formdate!=null) {
			queryStr.append(" AND c.applyDate >  :formdate");
		}
		if (!"".equals(todate)&&todate!=null) {
			queryStr.append(" AND c.applyDate <  :todate");
		}
		queryStr.append(" ORDER BY c.applyDate desc");
		TypedQuery<VMOrder> query = em().createQuery(queryStr.toString(), VMOrder.class);
		query.setParameter("state", state);
		if (!"".equals(number)&&number!=null) {
			query.setParameter("number", "%" + number + "%");
		}
		if (enterprise!=null) {
			query.setParameter("enterprise", enterprise);
		}
		if (!"".equals(formdate)&&formdate!=null) {
			query.setParameter("formdate", formdate);
		}
		if (!"".equals(todate)&&todate!=null) {
			query.setParameter("todate", todate);
		}
		return query.getResultList();
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

	public VMOrderState getState() {
		return state;
	}

	public void setState(VMOrderState state) {
		this.state = state;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public User getApplicant() {
		return applicant;
	}

	public void setApplicant(User applicant) {
		this.applicant = applicant;
	}



	public String getApplyDesc() {
		return applyDesc;
	}

	public void setApplyDesc(String applyDesc) {
		this.applyDesc = applyDesc;
	}

	public User getAuditor() {
		return auditor;
	}

	public void setAuditor(User auditor) {
		this.auditor = auditor;
	}



	public AuditOpinion getAuditOpinion() {
		return auditOpinion;
	}

	public void setAuditOpinion(AuditOpinion auditOpinion) {
		this.auditOpinion = auditOpinion;
	}

	public String getAuditDesc() {
		return auditDesc;
	}

	public void setAuditDesc(String auditDesc) {
		this.auditDesc = auditDesc;
	}

	public User getDispatcher() {
		return dispatcher;
	}

	public void setDispatcher(User dispatcher) {
		this.dispatcher = dispatcher;
	}



	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}

	public String getDispatchDate() {
		return dispatchDate;
	}

	public void setDispatchDate(String dispatchDate) {
		this.dispatchDate = dispatchDate;
	}

	public void insert() {

		em().persist(this);
	}

	public void delete() {

		em().remove(this);
	}
	// 根据id查询
	public static VMOrder findById(Integer id) {
		return em().find(VMOrder.class, id);
	}
	

}
