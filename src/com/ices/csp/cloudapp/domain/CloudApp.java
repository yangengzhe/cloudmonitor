/**
 * 
 */
package com.ices.csp.cloudapp.domain;

import static org.cspframework.core.jpa.ThreadLocalManager.em;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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

import com.ices.csp.common.enumitem.cloudapp.CloudAppState;
import com.ices.csp.common.enumitem.cloudapp.CloudAppType;
import com.ices.csp.common.enumitem.cloudapp.WSDeploy;
import com.ices.csp.database.domain.Database;
import com.ices.csp.software.domain.Software;
import com.ices.csp.webserver.domain.WebServer;

/**
 * @author MFC ZZY
 *
 */
@Entity
@Table(name = "clsas_cloudapp")
public class CloudApp implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "id", nullable = false)
	@Id
	private Integer id;//标识(关键字)
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "enterpriseid")
	private Enterprise enterprise;//所属租户
	
	@Column(name = "name", nullable = false)
	private String name;//云应用名称
	
	@Column(name = "version")
	private String version;//版本
	/**
	* 0:本地部署，1:云平台部署
	 */
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "type")
	private CloudAppType type;//部署模式，0:本地部署，1:云平台部署
	/**
	* 0:registered(已注册)，1:configured(已配置)，2:deployed(已部署)，3:unloaded(已卸载)
	 */
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "state")
	private CloudAppState state;//状态，registered(已注册)，configured(已配置)，deployed(已部署)，unloaded(已卸载)
	
	@Column(name = "provider")
	private String provider;//提供者（对应登陆用户）
	
	@Column(name = "picture")
	private String picture;//图片
	
	@Column(name = "document")
	private String document;//文档
	
	@Column(name = "registerTime")
	private String registerTime ;//注册布时间
	
	@Column(name = "beginTime")
	private String beginTime ;//开始时间
	
	@Column(name = "endTime")
	private String endTime ;//结束时间
	
	@Column(name = "demo")
	private String demo;//描述
	////////////////////////////////////////////////////////////////////////
	//应用服务器信息
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wsType")
	private Software wsType;//应用服务器类型
	
	@Column(name = "wsVersion")
	private String wsVersion;//应用服务器版本
	
	@Column(name = "appSize")
	private Float appSize;//应用大小
	
	@Column(name = "concurrentUsers")
	private Integer concurrentUsers;//并发用户数
	/**
	* 部署方式，0 共享，1独立
	 */
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "wsdeploy")
	private WSDeploy wsdeploy;//部署方式，0 共享，1独立
	
	@Column(name = "wsName")
	private String wsName;//应用实例名
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "webServer")
	private WebServer webServer;//应用服务器
	
	@Column(name = "url")
	private String url;//应用url=http://appIp:appPort/path/service
		
	@Column(name = "appip")
	private String appIp;//应用IP地址
	
	@Column(name = "appport")
	private Integer appPort;//应用端口号
	
	@Column(name = "dns")
	private String dns;//DNS
	
	@Column(name = "wsCode")
	private String wsCode;//应用代码
	
	
	/////////////////////////////////////////////////////////////////////
	//数据库信息
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dbType")
	private Software dbType;//数据库类型
	
	@Column(name = "dbVersion")
	private String dbVersion;//数据库版本
	
	@Column(name = "dbSize")
	private Float dbSize;//数据库大小上限
	
	@Column(name = "maxTransactions")
	private Integer maxTransactions;//最大并发事务数
	
	/**
	* 部署方式，0 共享，1独立
	 */
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "dbStore")
	private WSDeploy dbStore;//存储方式，0 共享，1独立
	
	@Column(name = "dbName")
	private String dbName;//数据名
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "database1")
	private Database database;//数据库
	
	@Column(name = "dbip")
	private String dbIp;//数据库-IP地址
	
	@Column(name = "dbport")
	private Integer dbPort;//数据库-端口号
	
	@Column(name = "username")
	private String username;//数据库-用户名
	
	@Column(name = "password")
	private String password;//数据库-密码
	
	@Column(name = "dbFile")
	private String dbFile;//数据库文件
	
	public void insert() {

		em().persist(this);
	}

	public void delete() {

		em().remove(this);
	}
	// 根据id查询
	public static CloudApp findById(Integer id) {
		// TODO Auto-generated method stub
		return em().find(CloudApp.class, id);
	}
	// 查询所有记录
	public static List<CloudApp> findAll(PagingBean pb) {
		StringBuffer queryStr = new StringBuffer();
		queryStr.append( "SELECT c FROM CloudApp c");
		StringBuffer sqlCount = new StringBuffer();
		sqlCount.append("select count(c) from CloudApp  c where 1=1 ");
		TypedQuery<CloudApp> query = em().createQuery(queryStr.toString(), CloudApp.class);
		TypedQuery<Long> countQuery = ThreadLocalManager.em().createQuery(sqlCount.toString(), Long.class);
		
		return pb.getResultList(countQuery, query);
	}
	public static List<CloudApp> findAllmyapp(PagingBean pb) {
		LoginUser user = User.getCurrentUser();
		User me = User.findById(user.getUserId());
		String name = me.getName();
		StringBuffer queryStr = new StringBuffer();
		queryStr.append( "SELECT c FROM CloudApp c WHERE 1=1 and c.provider = :name");
		StringBuffer sqlCount = new StringBuffer();
		sqlCount.append("select count(c) from CloudApp  c where 1=1 and c.provider = :name");
		TypedQuery<CloudApp> query = em().createQuery(queryStr.toString(), CloudApp.class);
		TypedQuery<Long> countQuery = ThreadLocalManager.em().createQuery(sqlCount.toString(), Long.class);
		query.setParameter("name", name);
		countQuery.setParameter("name", name);
		return pb.getResultList(countQuery, query);
	}
	public static List<CloudApp> findByParam(String name,String formdate,String todate) {
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("SELECT c FROM CloudApp c WHERE 1=1 ");
		if (!"".equals(name)&&name!=null) {
			queryStr.append("AND c.name LIKE :name ");
		}
		if (!"".equals(formdate)&&formdate!=null) {
			queryStr.append(" AND c.registerTime >  :formdate");
		}
		if (!"".equals(todate)&&todate!=null) {
			queryStr.append(" AND c.registerTime <  :todate");
		}
		queryStr.append(" ORDER BY c.registerTime desc");
		TypedQuery<CloudApp> query = em().createQuery(queryStr.toString(), CloudApp.class);
		if (!"".equals(name)&&name!=null) {
			query.setParameter("name", "%" + name + "%");
		}
		if (!"".equals(formdate)&&formdate!=null) {
			query.setParameter("formdate", formdate);
		}
		if (!"".equals(todate)&&todate!=null) {
			query.setParameter("todate", todate);
		}
		return query.getResultList();
	}
	public static List<CloudApp> searchmyapp(String name,CloudAppState state) {
		LoginUser user = User.getCurrentUser();
		User me = User.findById(user.getUserId());
		String pname = me.getName();
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("SELECT c FROM CloudApp c WHERE 1=1 and c.provider = :pname ");
		if (!"".equals(name)&&name!=null) {
			queryStr.append("AND c.name LIKE :name ");
		}
		if (!"".equals(state)&&state!=null) {
			queryStr.append(" AND c.state = :state");
		}
		queryStr.append(" ORDER BY c.registerTime desc");
		TypedQuery<CloudApp> query = em().createQuery(queryStr.toString(), CloudApp.class);
		query.setParameter("pname", pname);
		if (!"".equals(name)&&name!=null) {
			query.setParameter("name", "%" + name + "%");
		}
		if (!"".equals(state)&&state!=null) {
			query.setParameter("state", state);
		}
		return query.getResultList();
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
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

	public CloudAppType getType() {
		return type;
	}

	public void setType(CloudAppType type) {
		this.type = type;
	}

	public CloudAppState getState() {
		return state;
	}

	public void setState(CloudAppState state) {
		this.state = state;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDemo() {
		return demo;
	}

	public void setDemo(String demo) {
		this.demo = demo;
	}

	public Software getWsType() {
		return wsType;
	}

	public void setWsType(Software wsType) {
		this.wsType = wsType;
	}

	public String getWsVersion() {
		return wsVersion;
	}

	public void setWsVersion(String wsVersion) {
		this.wsVersion = wsVersion;
	}

	public Float getAppSize() {
		return appSize;
	}

	public void setAppSize(Float appSize) {
		this.appSize = appSize;
	}

	public Integer getConcurrentUsers() {
		return concurrentUsers;
	}

	public void setConcurrentUsers(Integer concurrentUsers) {
		this.concurrentUsers = concurrentUsers;
	}

	public WSDeploy getWsdeploy() {
		return wsdeploy;
	}

	public void setWsdeploy(WSDeploy wsdeploy) {
		this.wsdeploy = wsdeploy;
	}

	public String getWsName() {
		return wsName;
	}

	public void setWsName(String wsName) {
		this.wsName = wsName;
	}

	public WebServer getWebServer() {
		return webServer;
	}

	public void setWebServer(WebServer webServer) {
		this.webServer = webServer;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAppIp() {
		return appIp;
	}

	public void setAppIp(String appIp) {
		this.appIp = appIp;
	}

	public Integer getAppPort() {
		return appPort;
	}

	public void setAppPort(Integer appPort) {
		this.appPort = appPort;
	}

	public String getDns() {
		return dns;
	}

	public void setDns(String dns) {
		this.dns = dns;
	}

	public String getWsCode() {
		return wsCode;
	}

	public void setWsCode(String wsCode) {
		this.wsCode = wsCode;
	}

	public Software getDbType() {
		return dbType;
	}

	public void setDbType(Software dbType) {
		this.dbType = dbType;
	}

	public String getDbVersion() {
		return dbVersion;
	}

	public void setDbVersion(String dbVersion) {
		this.dbVersion = dbVersion;
	}

	public Float getDbSize() {
		return dbSize;
	}

	public void setDbSize(Float dbSize) {
		this.dbSize = dbSize;
	}

	public Integer getMaxTransactions() {
		return maxTransactions;
	}

	public void setMaxTransactions(Integer maxTransactions) {
		this.maxTransactions = maxTransactions;
	}




	public WSDeploy getDbStore() {
		return dbStore;
	}

	public void setDbStore(WSDeploy dbStore) {
		this.dbStore = dbStore;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public Database getDatabase() {
		return database;
	}

	public void setDatabase(Database database) {
		this.database = database;
	}

	public String getDbIp() {
		return dbIp;
	}

	public void setDbIp(String dbIp) {
		this.dbIp = dbIp;
	}

	public Integer getDbPort() {
		return dbPort;
	}

	public void setDbPort(Integer dbPort) {
		this.dbPort = dbPort;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDbFile() {
		return dbFile;
	}

	public void setDbFile(String dbFile) {
		this.dbFile = dbFile;
	}



	
}
