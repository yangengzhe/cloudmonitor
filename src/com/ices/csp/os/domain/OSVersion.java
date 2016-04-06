package com.ices.csp.os.domain;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import org.cspframework.common.PagingBean;
import org.cspframework.core.jpa.ThreadLocalManager;




@Entity
@Table(name = "csp_osversion")
@NamedQueries({ @NamedQuery(name = OSVersion.ALL, query = "SELECT c FROM OSVersion c WHERE 1 = 1") })
public class OSVersion implements Serializable{
	public static final String ALL = "com.ices.csp.os.domain.OSVersion.findAll";
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;// 操作系统版本id，标识，主关键字

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "osId")
	private OS os;// 所属操作系统，多对一
	
	
	@Column(name = "code")
	private String code;// 版本编码，唯一，非空

	@Column(name = "name")
	private String name;// 版本名称，非空
	
	@Column(name = "descri")
	private String desc;// 版本说明
	
	public void insert() {

		em().persist(this);
	}

	public void delete() {

		em().remove(this);
	}
	
	// 根据id查询
	public static OSVersion findById(Integer id) {
		// TODO Auto-generated method stub
		return em().find(OSVersion.class, id);
	}
	public static List<OSVersion> findOSVersionByosname(String osname) {
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("SELECT c FROM OSVersion c WHERE 1=1 AND c.os.name = :osname ");
		TypedQuery<OSVersion> query = em().createQuery(queryStr.toString(), OSVersion.class);
		query.setParameter("osname", osname);
		return query.getResultList();
	}
	public static OSVersion findByname(String name) {
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("SELECT c FROM OSVersion c WHERE 1=1 AND c.name = :name ");
		TypedQuery<OSVersion> query = em().createQuery(queryStr.toString(), OSVersion.class);
		query.setParameter("name", name);
		return query.getResultList().get(0);
	}
	public static List<OSVersion> findByDuplicate(Integer id,String code) {
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("SELECT c FROM OSVersion c WHERE 1=1 AND c.code = :code ");
		if (id != null && id.intValue() > 0) {
			queryStr.append(" AND c.id <> :id ");
		}
		TypedQuery<OSVersion> query = em().createQuery(queryStr.toString(), OSVersion.class);
		if (id != null && id.intValue() > 0) {
			query.setParameter("id", id);
		}
		query.setParameter("code", code);
		return query.getResultList();
	}
	public static List<OSVersion> findAllByOSId(Integer id,PagingBean pb) {
			StringBuffer queryStr = new StringBuffer();
			queryStr.append( "SELECT c FROM OSVersion c where c.os.id = :id ");
			StringBuffer sqlCount = new StringBuffer();
			sqlCount.append("select count(c) from OSVersion  c where 1=1 AND c.os.id = :id ");
			TypedQuery<OSVersion> query = em().createQuery(queryStr.toString(), OSVersion.class);
			TypedQuery<Long> countQuery = ThreadLocalManager.em().createQuery(sqlCount.toString(), Long.class);
			query.setParameter("id", id);
			countQuery.setParameter("id", id);
			return pb.getResultList(countQuery, query);
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OS getOs() {
		return os;
	}

	public void setOs(OS os) {
		this.os = os;
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
