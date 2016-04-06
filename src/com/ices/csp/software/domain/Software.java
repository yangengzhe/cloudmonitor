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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import org.cspframework.common.PagingBean;
import org.cspframework.core.jpa.ThreadLocalManager;

import com.ices.csp.software.dto.SoftwareConditionDto;


/**
 * 软件
 * @author Fanchao Meng
 *
 */
@Entity
@Table(name = "csp_software")
public class Software implements Serializable{
	@Column(name = "id", nullable = false)
	@Id
	private Integer id;//标识(关键字)
	
	@Column(name = "code", nullable = false)
	private String code;//软件编码
	
	@Column(name = "name", nullable = false)
	private String name;//软件名称
	
	@Column(name = "version")
	private String version;//版本
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type")
	private SoftwareType type;//软件类型
	
	@Column(name = "provider")
	private String provider;//提供者
	
	@Column(name = "size")
	private Float size;//大小
	
	@Column(name = "codeurl")
	private String codeUrl;//代码位置
	
	@Column(name = "docurl")
	private String docUrl;//文档位置
	
	@Column(name = "demo")
	private String demo;//描述
	
	////////////////////////////////////////////////////////////////////////////
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

	public SoftwareType getType() {
		return type;
	}

	public void setType(SoftwareType type) {
		this.type = type;
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
		
	////////////////////////////////////////////////////////////////////////////
	/*
	 * 新增操作
	 * @param software
	 * 
	 */
	public static void create(Software software) {
		ThreadLocalManager.em().persist(software);
	}
	//////////////////////////////////////////////////////////////////////////
	/*
	 * 修改操作
	 * @param software
	 * 
	 */
	public static void update(Software software) {
		ThreadLocalManager.em().merge(software);
	}
	////////////////////////////////////////////////////////////////////////////
	/*
	 * 删除操作
	 * @param software
	 */
	public static void delete(Software software) {
		ThreadLocalManager.em().remove(software);
	}
	////////////////////////////////////////////////////////////////////////////
	/*
	 * 查询操作
	 * 根据关键字查询
	 * @param id
	 * @return Software
	 */
	public static Software query(Integer id) {
		return (Software) ThreadLocalManager.em().find(Software.class, id);
	}
	////////////////////////////////////////////////////////////////////////////
	/*
	 * 查询操作
	 * 查询所有
	 * @return List<Software>
	 */
	public static List<Software> query() {
		StringBuffer querySb = new StringBuffer("");
		querySb.append(" SELECT c FROM Software c ");
		TypedQuery<Software> query = ThreadLocalManager.em().createQuery(
				querySb.toString(), Software.class);
		return query.getResultList();
	}
	////////////////////////////////////////////////////////////////////////////
	/*
	 * 查询操作
	 * 根据软件类型Id查询
	 * @param typeId
	 * @return List<Software>
	 */	
	public static List<Software> getSoftwares(String code) {
		if(code== null){
			return new ArrayList<Software>();
		}
		StringBuffer jpqlByCondition = new StringBuffer();
		jpqlByCondition.append("SELECT software FROM Software software WHERE 1=1 ");
		
		jpqlByCondition.append(" and software.type.code = :code ");
		
		TypedQuery<Software> queryTypeByCondition = ThreadLocalManager.em()
				.createQuery(jpqlByCondition.toString(), Software.class);
		queryTypeByCondition.setParameter("code",  code );
		return queryTypeByCondition.getResultList();
	}
	
	public static Software findByname(String name) {
		if(name== null){
			return new Software();
		}
		StringBuffer jpqlByCondition = new StringBuffer();
		jpqlByCondition.append("SELECT software FROM Software software WHERE 1=1 ");
		
		jpqlByCondition.append(" and software.name = :name ");
		
		TypedQuery<Software> queryTypeByCondition = ThreadLocalManager.em()
				.createQuery(jpqlByCondition.toString(), Software.class);
		queryTypeByCondition.setParameter("name",  name );
		return queryTypeByCondition.getResultList().get(0);
	}
	////////////////////////////////////////////////////////////////////////////
	/*
	 * 查询操作
	 * 根据条件查询
	 * @param dto 
	 * @param pb
	 * @return List<Software>
	 */
	public static List<Software> query(SoftwareConditionDto dto, PagingBean pb) {
		if (dto == null) {
			return new ArrayList<Software>();
		}

		StringBuffer jpqlByCondition = new StringBuffer();
		jpqlByCondition.append("SELECT software FROM Software software WHERE 1=1 ");

		//统计记录个数
		StringBuffer sqlCount = new StringBuffer();
		sqlCount.append("select count(software) from Software software where 1=1 ");

		
		if (dto.getCode() != null) {
			jpqlByCondition.append(" and software.code LIKE :code ");
			sqlCount.append(" and software.code LIKE :code ");
		}

		if (dto.getName() != null) {
			jpqlByCondition.append(" and software.name LIKE :name ");
			sqlCount.append(" and software.name LIKE :name ");
		}
		
		if (dto.getTypeId() != null) {
			jpqlByCondition.append(" and software.type.id = :typeId ");
			sqlCount.append(" and software.type.id = :typeId ");
		}
		
		TypedQuery<Software> queryTypeByCondition = ThreadLocalManager.em()
				.createQuery(jpqlByCondition.toString(), Software.class);

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
		
		if(dto.getTypeId() != null){
			queryTypeByCondition
					.setParameter("typeId",  dto.getTypeId() );
			countQuery.setParameter("typeId",  dto.getTypeId() );
		}
		
		return pb.getResultList(countQuery, queryTypeByCondition);
	}
	
	
	

}
