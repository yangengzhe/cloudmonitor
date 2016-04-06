package com.ices.csp.os.domain;

import static org.cspframework.core.jpa.ThreadLocalManager.em;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import org.cspframework.common.PagingBean;
import org.cspframework.core.jpa.ThreadLocalManager;



@Entity
@Table(name = "csp_os")
@NamedQueries({ @NamedQuery(name = OS.ALL, query = "SELECT c FROM OS c WHERE 1 = 1") })
public class OS implements Serializable{
	public static final String ALL = "com.ices.csp.os.domain.OS.findAll";
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;// 操作系统id，标识，主关键字

	@Column(name = "code")
	private String code;// 操作系统编码，唯一，非空

	@Column(name = "name")
	private String name;// 操作系统名称，非空
	
	@Column(name = "descri")
	private String desc;// 操作系统说明
	public void insert() {

		em().persist(this);
	}

	public void delete() {

		em().remove(this);
	}
	
	// 根据id查询
	public static OS findById(Integer id) {
		// TODO Auto-generated method stub
		return em().find(OS.class, id);
	}
	public static List<OS> findByDuplicate(Integer id,String code) {
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("SELECT c FROM OS c WHERE 1=1 AND c.code = :code ");
		if (id != null && id.intValue() > 0) {
			queryStr.append(" AND c.id <> :id ");
		}
		TypedQuery<OS> query = em().createQuery(queryStr.toString(), OS.class);
		if (id != null && id.intValue() > 0) {
			query.setParameter("id", id);
		}
		query.setParameter("code", code);
		return query.getResultList();
	}
	// 查询所有记录
	public static List<OS> findAll(PagingBean pb) {
		StringBuffer queryStr = new StringBuffer();
		queryStr.append( "SELECT c FROM OS c");
		StringBuffer sqlCount = new StringBuffer();
		sqlCount.append("select count(c) from OS  c where 1=1 ");
		TypedQuery<OS> query = em().createQuery(queryStr.toString(), OS.class);
		TypedQuery<Long> countQuery = ThreadLocalManager.em().createQuery(sqlCount.toString(), Long.class);
		
		return pb.getResultList(countQuery, query);
	}
	//根据操作系统name查询
	public static List<OS> findByName(String name) {
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("SELECT c FROM OS c WHERE 1=1 AND c.name LIKE :name ");
		TypedQuery<OS> query = em().createQuery(queryStr.toString(), OS.class);
		query.setParameter("name", "%" + name + "%");
		List<OS> list = query.getResultList();

		return list;
	}
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
