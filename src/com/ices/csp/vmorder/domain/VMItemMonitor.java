package com.ices.csp.vmorder.domain;

import static org.cspframework.core.jpa.ThreadLocalManager.em;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

@Entity
@Table(name = "csp_monitor")
public class VMItemMonitor implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;// 监控信息id，标识，主关键字
		
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "vmitemMark")
	private VMItem vmitem;// 云主机订单标示
	
	@Column(name = "cpuusagerate")
	private Double cpuusagerate;// cpu使用率
	
	@Column(name = "memusagerate")
	private Double memusagerate;// 内存使用率
	
	@Column(name = "timepoint")
	private String timepoint;// 时间点
	
	public void insert() {

		em().persist(this);
	}

	public void delete() {

		em().remove(this);
	}
	
	// 根据id查询
	public static VMItemMonitor findById(Integer id) {
		return em().find(VMItemMonitor.class, id);
	}
	public static List<VMItemMonitor> findByVMItemid(Integer vmitemMark) {
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("SELECT c FROM VMItemMonitor c WHERE 1=1 ");
		if (vmitemMark!=null) {
			queryStr.append(" AND c.vmitem.id = :vmitemMark ");
		}
		TypedQuery<VMItemMonitor> query = em().createQuery(queryStr.toString(), VMItemMonitor.class);

		if (vmitemMark!=null) {
			query.setParameter("vmitemMark", vmitemMark);
		}
		return query.getResultList();
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public VMItem getVmitem() {
		return vmitem;
	}

	public void setVmitem(VMItem vmitem) {
		this.vmitem = vmitem;
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
