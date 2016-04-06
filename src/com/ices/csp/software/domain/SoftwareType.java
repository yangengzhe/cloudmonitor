/**
 * 
 */
package com.ices.csp.software.domain;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.TypedQuery;

import org.cspframework.common.PagingBean;
import org.cspframework.core.jpa.ThreadLocalManager;

import com.ices.csp.software.dto.SoftwareTypeCondition;

/**
 * 软件类型
 * @author Fanchao Meng
 *
 */
@Entity
@Table(name = "csp_softwaretype")
@NamedQueries({@javax.persistence.NamedQuery(name="getSoftwareTypes", query="SELECT c FROM SoftwareType c")})
public class SoftwareType implements Serializable{
	private static final long serialVersionUID = -1005868007476890001L;
	public static final String getSoftwareTypes = "org.cspframework.manage.software.domain.SoftwareType.getAll";

	
	@Column(name = "id", nullable = false)
	@Id
	//@GeneratedValue(strategy = GenerationType.TABLE, generator =" org.cspframework.manage.software.domain.SoftwareType")
	private Integer id;//关键字
	
	@Column(name = "code", nullable = false)
	private String code;//软件类型编码
	
	@Column(name = "name", nullable = false)
	private String name;//软件类型名称
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fatherid")
	private SoftwareType parent;//父类型
	
	@Column(name = "demo")
	private String demo;//描述
    
	//////////////////////////////////////////////////////////////
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

	
	public SoftwareType getParent() {
		return parent;
	}

	public void setParent(SoftwareType parent) {
		this.parent = parent;
	}

	public String getDemo() {
		return demo;
	}

	public void setDemo(String demo) {
		this.demo = demo;
	}

	//////////////////////////////////////////////////////////////
	//新增
	public static void insert(SoftwareType softwareType) {
		ThreadLocalManager.em().persist(softwareType);
	}
	//查询
	public static List<SoftwareType> getAll() {
		StringBuffer querySb = new StringBuffer("");
		querySb.append(" SELECT c FROM SoftwareType c ");
		
		TypedQuery<SoftwareType> query = ThreadLocalManager.em().createQuery(
				querySb.toString(), SoftwareType.class);
		return query.getResultList();
	}
	///////////////////////////////////////////////////////////////////////////
	/*
	 * 查询操作
	 * 根据条件查询
	 * @param dto 
	 * @param pb
	 * @return List<SoftwareType>
	 */
	public static List<SoftwareType> query(SoftwareTypeCondition dto, PagingBean pb) {
		if (dto == null) {
			return new ArrayList<SoftwareType>();
		}

		StringBuffer jpqlByCondition = new StringBuffer();
		jpqlByCondition.append("SELECT softwareType FROM SoftwareType softwareType WHERE 1=1 ");

		//统计记录个数
		StringBuffer sqlCount = new StringBuffer();
		sqlCount.append("select count(softwareType) from SoftwareType softwareType where 1=1 ");

		
		if (dto.getCode() != null) {
			jpqlByCondition.append(" and softwareType.code LIKE :code ");
			sqlCount.append(" and softwareType.code LIKE :code ");
		}

		if (dto.getName() != null) {
			jpqlByCondition.append(" and softwareType.name LIKE :name ");
			sqlCount.append(" and softwareType.name LIKE :name ");
		}
		
		TypedQuery<SoftwareType> queryTypeByCondition = ThreadLocalManager.em()
				.createQuery(jpqlByCondition.toString(), SoftwareType.class);

		TypedQuery<Long> countQuery = ThreadLocalManager.em().createQuery(
				sqlCount.toString(), Long.class);

		if (dto.getCode() != null) {
			queryTypeByCondition
					.setParameter("code", "%" + dto.getCode() + "%");
			countQuery.setParameter("code", "%" + dto.getCode() + "%");
		}
		
		if (dto.getName() != null) {
			queryTypeByCondition
					.setParameter("name", "%" + dto.getName() + "%");
			countQuery.setParameter("name", "%" + dto.getName() + "%");
		}
		
		return pb.getResultList(countQuery, queryTypeByCondition);
	}
	//////////////////////////////////////////////////////////////////////////
	/*
	 * 查询操作
	 * 根据关键字查询
	 * @param id
	 * @return SoftwareType
	 */
	public static SoftwareType query(Integer id) {
		return (SoftwareType) ThreadLocalManager.em().find(SoftwareType.class, id);
	}
	/////////////////////////////////////////////////////////////////////////////
	
	
	
	
	////////////////////////////////////////////////////////////////////////////
	/*
	 * 新增操作
	 * 
	 */
	public static void add(SoftwareType softwareType) {
		ThreadLocalManager.em().persist(softwareType);
	}
	///////////////////////////////////////////////////////////////////////////
	/*
	 * 删除操作
	 */
	public static void delete(SoftwareType softwareType) {
		ThreadLocalManager.em().remove(softwareType);
	}
	/////////////////////////////////////////////////////////////////////////////
	/*
	 * 修改操作
	 */
	public static void update(SoftwareType softwareType) {
		ThreadLocalManager.em().merge(softwareType);
	}

}
