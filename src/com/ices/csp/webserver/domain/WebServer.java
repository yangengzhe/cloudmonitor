/**
 * 
 */
package com.ices.csp.webserver.domain;

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

import com.ices.csp.software.domain.Software;
import com.ices.csp.webserver.dto.WebServerConditionDto;




/**
 * @author MFC
 *
 */
@Entity
@Table(name = "csp_webserver")
public class WebServer implements Serializable{
	private static final long serialVersionUID = 1L;
	@Column(name = "id", nullable = false)
	@Id
	private Integer id;//标识(关键字)
	
	@Column(name = "name", nullable = false)
	private String name;//ws名称
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type")
	private Software type;//ws类型
	
	@Column(name = "state")
	private String state;//状态，“open”：运行，“close”：关闭	
		
	@Column(name = "ip")
	private String ip;//ip地址
	
	@Column(name = "port")
	private Integer port;//端口号
	
	@Column(name = "webapps")
	private String webapps;//ws发布地址
	
	@Column(name = "storage")
	private String storage;//ws存储地址
	
	@Column(name = "protocol")
	private String protocol;//协议，默认HTTP/1.1
	
	@Column(name = "maxThreads")
	private Integer maxThreads;//最大线程数
	
	@Column(name = "minSpareThreads")
	private Integer minSpareThreads;//最小空闲线程数
	
	@Column(name = "maxSpareThreads")
	private Integer maxSpareThreads;//最大空闲线程数
	
	@Column(name = "acceptCount")
	private Integer acceptCount;//排队请求数
	
	@Column(name = "connectionTimeout")
	private Integer connectionTimeout;//排队请求数
	
	@Column(name = "demo")
	private String demo;//描述

	//////////////////////////////////////////////////////////////////////////
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

	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getWebapps() {
		return webapps;
	}

	public void setWebapps(String webapps) {
		this.webapps = webapps;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public Integer getMaxThreads() {
		return maxThreads;
	}

	public void setMaxThreads(Integer maxThreads) {
		this.maxThreads = maxThreads;
	}

	public Integer getMinSpareThreads() {
		return minSpareThreads;
	}

	public void setMinSpareThreads(Integer minSpareThreads) {
		this.minSpareThreads = minSpareThreads;
	}

	public Integer getMaxSpareThreads() {
		return maxSpareThreads;
	}

	public void setMaxSpareThreads(Integer maxSpareThreads) {
		this.maxSpareThreads = maxSpareThreads;
	}

	public Integer getAcceptCount() {
		return acceptCount;
	}

	public void setAcceptCount(Integer acceptCount) {
		this.acceptCount = acceptCount;
	}

	public Integer getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(Integer connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public String getDemo() {
		return demo;
	}

	public void setDemo(String demo) {
		this.demo = demo;
	}
	/////////////////////////////////////////////////////////////////////////////
	/*
	 * 新增操作
	 * @param webServer
	 * 
	 */
	public static void create(WebServer webServer) {
		ThreadLocalManager.em().persist(webServer);
	}
	//////////////////////////////////////////////////////////////////////////
	/*
	 * 修改操作
	 * @param webServer
	 * 
	 */
	public static void update(WebServer webServer) {
		ThreadLocalManager.em().merge(webServer);
	}
	////////////////////////////////////////////////////////////////////////////
	/*
	 * 删除操作
	 * @param webServer
	 */
	public static void delete(WebServer webServer) {
		ThreadLocalManager.em().remove(webServer);
	}
	////////////////////////////////////////////////////////////////////////////
	/*
	 * 查询操作
	 * 根据关键字查询
	 * @param id
	 * @return WebServer
	 */
	public static WebServer query(Integer id) {
		return (WebServer) ThreadLocalManager.em().find(WebServer.class, id);
	}
	////////////////////////////////////////////////////////////////////////////
	/*
	 * 查询操作
	 * 查询所有
	 * @return List<Software>
	 */
	public static List<WebServer> query() {
		StringBuffer querySb = new StringBuffer("");
		querySb.append(" SELECT c FROM WebServer c ");
		TypedQuery<WebServer> query = ThreadLocalManager.em().createQuery(
				querySb.toString(), WebServer.class);
		return query.getResultList();
	}
	////////////////////////////////////////////////////////////////////////////
	/*
	 * 查询操作
	 * 根据条件查询
	 * @param dto 
	 * @param pb
	 * @return List<WebServer>
	 */
	public static List<WebServer> query(WebServerConditionDto dto, PagingBean pb) {
		if (dto == null) {
			return new ArrayList<WebServer>();
		}

		StringBuffer jpqlByCondition = new StringBuffer();
		jpqlByCondition.append("SELECT webServer FROM WebServer webServer WHERE 1=1 ");

		//统计记录个数
		StringBuffer sqlCount = new StringBuffer();
		sqlCount.append("select count(webServer) from WebServer webServer where 1=1 ");

		
		if (dto.getIp() != null) {
			jpqlByCondition.append(" and webServer.ip LIKE :ip ");
			sqlCount.append(" and webServer.ip LIKE :ip ");
		}

		if (dto.getName() != null) {
			jpqlByCondition.append(" and webServer.name LIKE :name ");
			sqlCount.append(" and webServer.name LIKE :name ");
		}
				
		TypedQuery<WebServer> queryTypeByCondition = ThreadLocalManager.em()
				.createQuery(jpqlByCondition.toString(), WebServer.class);

		TypedQuery<Long> countQuery = ThreadLocalManager.em().createQuery(
				sqlCount.toString(), Long.class);

		if (dto.getIp() != null) {
			queryTypeByCondition
					.setParameter("ip", "%" + dto.getIp() + "%");
			countQuery.setParameter("ip", "%" + dto.getIp() + "%");
		}
		
		if (dto.getName() != null) {
			queryTypeByCondition
					.setParameter("name", "%" + dto.getName() + "%");
			countQuery.setParameter("name", "%" + dto.getName() + "%");
		}
				
		return pb.getResultList(countQuery, queryTypeByCondition);
	}	
	
	public static List<WebServer> findwebserver(Software web) {


		StringBuffer jpqlByCondition = new StringBuffer();
		jpqlByCondition.append("SELECT webServer FROM WebServer webServer WHERE 1=1 ");
		if (web != null) {
			jpqlByCondition.append(" and webServer.type = :web ");
		}	
		TypedQuery<WebServer> queryTypeByCondition = ThreadLocalManager.em()
				.createQuery(jpqlByCondition.toString(), WebServer.class);
		if (web != null) {
			queryTypeByCondition
					.setParameter("web",web);
		}		
		return queryTypeByCondition.getResultList();
	}	
	
	
	

}
