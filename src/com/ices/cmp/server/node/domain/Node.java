package com.ices.cmp.server.node.domain;

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
@Table(name = "cmp_nodeinfo")
@NamedQueries({ @NamedQuery(name = Node.ALL, query = "SELECT c FROM Node c WHERE 1 = 1") })
public class Node implements Serializable {
	/*-------------------数据字典定义--------------------*/
	public static final String ALL = "com.ices.csp.node.domain.Node.findAll";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;// 节点id

	@Column(name = "code")
	private String code;// 节点编码，唯一，非空

	@Column(name = "name")
	private String name;// 节点名称，非空

	@Column(name = "principal")
	private String principal;// 负责人

	@Column(name = "telephone")
	private String telephone;// 电话
	
	@Column(name = "mobile")
	private String mobile;// 手机
	
	@Column(name = "email")
	private String email;// 邮箱
	
	@Column(name = "address")
	private String address;// 节点地址
	
	@Column(name = "descri")
	private String desc;// 说明

	public void insert() {

		em().persist(this);
	}

	public void delete() {

		em().remove(this);
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
	// 查询所有记录
    public static List<Node> findAll(PagingBean pb) {
        StringBuffer queryStr = new StringBuffer();
        queryStr.append( "SELECT c FROM Node c");
        StringBuffer sqlCount = new StringBuffer();
        sqlCount.append("select count(c) from Node  c where 1=1 ");
//      String a=queryStr.toString();
//      TypedQuery<Node> queryTypeByCondition = ThreadLocalManager.em().createQuery(a, Node.class);
//      TypedQuery<Node> queryTypeByCondition = ThreadLocalManager.em().createQuery((queryStr.toString()), Node.class);
        TypedQuery<Node> query = em().createQuery(queryStr.toString(), Node.class);
        TypedQuery<Long> countQuery = ThreadLocalManager.em().createQuery(sqlCount.toString(), Long.class);
        
        return pb.getResultList(countQuery, query);
    }
 // 查询所有记录
    public static List<Node> findAll() {
        StringBuffer queryStr = new StringBuffer();
        queryStr.append( "SELECT c FROM Node c");
//      String a=queryStr.toString();
//      TypedQuery<Node> queryTypeByCondition = ThreadLocalManager.em().createQuery(a, Node.class);
//      TypedQuery<Node> queryTypeByCondition = ThreadLocalManager.em().createQuery((queryStr.toString()), Node.class);
        TypedQuery<Node> query = em().createQuery(queryStr.toString(), Node.class);
        return query.getResultList();
    }
//	public static List<Node> findAll(PagingBean pb) {
//		StringBuffer queryStr = new StringBuffer();
//		queryStr.append("SELECT c FROM Node c WHERE 1=1 ");
////		TypedQuery<Test> query = em().createQuery(a, Test.class);
//		TypedQuery<Node> query = em().createQuery(queryStr.toString(), Node.class);
//		return query.getResultList();
//	}
	//根据节点name查询
	public static List<Node> findByName(String name) {
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("SELECT c FROM Node c WHERE 1=1 AND c.name LIKE :name ");
		TypedQuery<Node> query = em().createQuery(queryStr.toString(), Node.class);
		query.setParameter("name", "%" + name + "%");
		List<Node> list = query.getResultList();

		return list;
	}
	//根据节点name查询
	public static Node findByNameCurrent(String name) {
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("SELECT c FROM Node c WHERE 1=1 AND c.name = :name ");
		TypedQuery<Node> query = em().createQuery(queryStr.toString(), Node.class);
		query.setParameter("name", name);
		Node list = query.getResultList().get(0);

		return list;
	}
	// 根据id查询
	public static Node findById(Integer id) {
		// TODO Auto-generated method stub
		return em().find(Node.class, id);
	}
	//根据code查找
	public static List<Node> findByDuplicate(Integer id,String code) {
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("SELECT c FROM Node c WHERE 1=1 AND c.code = :code ");
		if (id != null && id.intValue() > 0) {
			queryStr.append(" AND c.id <> :id ");
		}
		TypedQuery<Node> query = em().createQuery(queryStr.toString(), Node.class);
		if (id != null && id.intValue() > 0) {
			query.setParameter("id", id);
		}
		query.setParameter("code", code);
		return query.getResultList();
	}
}
