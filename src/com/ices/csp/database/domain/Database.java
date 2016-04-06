/**
 * 
 */
package com.ices.csp.database.domain;

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

import com.ices.csp.database.dto.DatabaseConditionDto;
import com.ices.csp.software.domain.Software;


/**
 * @author MFC
 *
 */
@Entity
@Table(name = "csp_database")
public class Database implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "id", nullable = false)
	@Id
	private Integer id;//标识(关键字)
	
	@Column(name = "name", nullable = false)
	private String name;//数据库名称
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type")
	private Software type;//数据库类型
	
	@Column(name = "ip")
	private String ip;//ip地址
	
	@Column(name = "port")
	private Integer port;//端口号
	
	@Column(name = "storage")
	private String storage;//存储地址
	
	@Column(name = "demo")
	private String demo;//描述

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Software getType() {
		return type;
	}

	public void setType(Software type) {
		this.type = type;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getDemo() {
		return demo;
	}

	public void setDemo(String demo) {
		this.demo = demo;
	}
	//////////////////////////////////////////////////////////////////////////////
	
	/*
	 * 新增操作
	 * @param database
	 * 
	 */
	public static void create(Database database) {
		ThreadLocalManager.em().persist(database);
	}
	//////////////////////////////////////////////////////////////////////////
	/*
	 * 修改操作
	 * @param database
	 * 
	 */
	public static void update(Database database) {
		ThreadLocalManager.em().merge(database);
	}
	////////////////////////////////////////////////////////////////////////////
	/*
	 * 删除操作
	 * @param webServer
	 */
	public static void delete(Database database) {
		ThreadLocalManager.em().remove(database);
	}
	////////////////////////////////////////////////////////////////////////////
	/*
	 * 查询操作
	 * 根据关键字查询
	 * @param id
	 * @return Database
	 */
	public static Database query(Integer id) {
		return (Database) ThreadLocalManager.em().find(Database.class, id);
	}
	////////////////////////////////////////////////////////////////////////////
	/*
	 * 查询操作
	 * 查询所有
	 * @return List<Database>
	 */
	public static List<Database> query() {
		StringBuffer querySb = new StringBuffer("");
		querySb.append(" SELECT c FROM Database c ");
		TypedQuery<Database> query = ThreadLocalManager.em().createQuery(
				querySb.toString(), Database.class);
		return query.getResultList();
	}
	/////////////////////////////////////////////////////////////////////////////
	/*
	 * 查询操作
	 * 根据条件查询
	 * @param dto 
	 * @param pb
	 * @return List<WebServer>
	 */
	public static List<Database> query(DatabaseConditionDto dto, PagingBean pb) {
		if (dto == null) {
			return new ArrayList<Database>();
		}

		StringBuffer jpqlByCondition = new StringBuffer();
		jpqlByCondition.append("SELECT database FROM Database database WHERE 1=1 ");

		//统计记录个数
		StringBuffer sqlCount = new StringBuffer();
		sqlCount.append("select count(database) from Database database where 1=1 ");

		
		
		if (dto.getName() != null) {
			jpqlByCondition.append(" and database.name LIKE :name ");
			sqlCount.append(" and database.name LIKE :name ");
		}
		if (dto.getIp() != null) {
			jpqlByCondition.append(" and database.ip LIKE :ip ");
			sqlCount.append(" and database.ip LIKE :ip ");
		}
				
		TypedQuery<Database> queryTypeByCondition = ThreadLocalManager.em()
				.createQuery(jpqlByCondition.toString(), Database.class);

		TypedQuery<Long> countQuery = ThreadLocalManager.em().createQuery(
				sqlCount.toString(), Long.class);

		
		
		if (dto.getName() != null) {
			queryTypeByCondition
					.setParameter("name", "%" + dto.getName() + "%");
			countQuery.setParameter("name", "%" + dto.getName() + "%");
		}
		if (dto.getIp() != null) {
			queryTypeByCondition
					.setParameter("ip", "%" + dto.getIp() + "%");
			countQuery.setParameter("ip", "%" + dto.getIp() + "%");
		}
				
		return pb.getResultList(countQuery, queryTypeByCondition);
	}	
	public static List<Database> finddbserver(Software web) {


		StringBuffer jpqlByCondition = new StringBuffer();
		jpqlByCondition.append("SELECT d FROM Database d WHERE 1=1 ");
		if (web != null) {
			jpqlByCondition.append(" and d.type = :web ");
		}	
		TypedQuery<Database> queryTypeByCondition = ThreadLocalManager.em()
				.createQuery(jpqlByCondition.toString(), Database.class);
		if (web != null) {
			queryTypeByCondition
					.setParameter("web",web);
		}		
		return queryTypeByCondition.getResultList();
	}

}
