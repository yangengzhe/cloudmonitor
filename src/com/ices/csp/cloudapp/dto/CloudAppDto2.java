package com.ices.csp.cloudapp.dto;

import org.cspframework.core.dto.annotation.DtoClass;
import org.cspframework.core.dto.annotation.DtoProperty;

import com.ices.csp.cloudapp.domain.CloudApp;

@DtoClass(entities = CloudApp.class)
public class CloudAppDto2 {
	/**
	 * 主键 节点id
	 */
	@DtoProperty(entityClass = CloudApp.class)
	private Integer id;//标识(关键字)

	/**
	 * 云应用名称
	 */

	@DtoProperty(entityClass = CloudApp.class)
	private String name;//云应用名称
	/**
	 * 版本
	 */
	@DtoProperty(entityClass = CloudApp.class)
	private String version;//版本
	/**
	 * / 0:registered(已注册)，1:configured(已配置)，2:deployed(已部署)，3:unloaded(已卸载)

	 */
	@DtoProperty(entityClass=CloudApp.class, entityProperty="state.value", readOnly=true)
	private String state;// 0:registered(已注册)，1:configured(已配置)，2:deployed(已部署)，3:unloaded(已卸载)
	/**
	 * /部署模式，0:本地部署，1:云平台部署
	 */
	@DtoProperty(entityClass=CloudApp.class, entityProperty="type.value", readOnly=true)
	private String type;//部署模式，0:本地部署，1:云平台部署
	/**
	 * 注册布时间
	 */
	@DtoProperty(entityClass = CloudApp.class)
	private String registerTime ;//注册布时间
	/**
	 * 开始时间
	 */
	@DtoProperty(entityClass = CloudApp.class)
	private String beginTime ;//开始时间
	/**
	 * 结束时间
	 */
	@DtoProperty(entityClass = CloudApp.class)
	private String endTime ;//结束时间
	/**
	 * 应用服务器类型
	 */
	@DtoProperty(entityClass=CloudApp.class,entityProperty="wsType.name")
	private String wsType;//应用服务器类型
	
	/**
	 * 应用服务器版本
	 */
	@DtoProperty(entityClass=CloudApp.class,entityProperty="wsType.version")
	private String wsVersion;//应用服务器版本
	/**
	 * 应用大小
	 */
	@DtoProperty(entityClass = CloudApp.class)
	private Float appSize;//应用大小
	/**
	 * 并发用户数
	 */
	@DtoProperty(entityClass = CloudApp.class)
	private Integer concurrentUsers;//并发用户数
	/**
	 * 部署方式，0 共享，1独立
	 */
	@DtoProperty(entityClass=CloudApp.class, entityProperty="wsdeploy.value", readOnly=true)
	private String wsdeploy;//部署方式，0 共享，1独立
	/**
	 * 应用实例名
	 */
	@DtoProperty(entityClass = CloudApp.class)
	private String wsName;//应用实例名
	/**
	 * 数据库类型
	 */
	@DtoProperty(entityClass=CloudApp.class,entityProperty="dbType.name")
	private String dbType;//数据库类型
	/**
	 * 数据库版本
	 */
	@DtoProperty(entityClass=CloudApp.class,entityProperty="dbType.version")
	private String dbVersion;//数据库版本
	/**
	 * 数据库大小上限
	 */
	@DtoProperty(entityClass = CloudApp.class)
	private Float dbSize;//数据库大小上限
	/**
	 * 最大并发事务数
	 */
	@DtoProperty(entityClass = CloudApp.class)
	private Integer maxTransactions;//最大并发事务数
	/**
	 * 存储方式，0 共享，1独立
	 */
	@DtoProperty(entityClass=CloudApp.class, entityProperty="dbStore.value", readOnly=true)
	private String dbStore;//存储方式，0 共享，1独立
	/**
	 * 数据名
	 */
	@DtoProperty(entityClass = CloudApp.class)
	private String dbName;//数据名
	
	//////////////////////////////////////////
	@DtoProperty(entityClass=CloudApp.class,entityProperty="webServer.name")
	private String webServer;//应用服务器
	@DtoProperty(entityClass = CloudApp.class)
	private String url;
	@DtoProperty(entityClass = CloudApp.class)
	private String appIp;//应用IP地址
	@DtoProperty(entityClass = CloudApp.class)
	private Integer appPort;//应用端口号
	@DtoProperty(entityClass = CloudApp.class)
	private String dns;//DNS
	//////////////////////
	@DtoProperty(entityClass=CloudApp.class,entityProperty="database.name")
	private String database;//数据库
	@DtoProperty(entityClass = CloudApp.class)
	private String dbIp;//数据库-IP地址
	@DtoProperty(entityClass = CloudApp.class)
	private Integer dbPort;//数据库-端口号
	@DtoProperty(entityClass = CloudApp.class)
	private String username;//数据库-用户名
	@DtoProperty(entityClass = CloudApp.class)
	private String password;//数据库-密码
	
	/////////////////////////////////////////////
	
	
	public Integer getId() {
		return id;
	}
	public String getWebServer() {
		return webServer;
	}
	public void setWebServer(String webServer) {
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
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
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
	public void setId(Integer id) {
		this.id = id;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getWsType() {
		return wsType;
	}
	public void setWsType(String wsType) {
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
	public String getWsdeploy() {
		return wsdeploy;
	}
	public void setWsdeploy(String wsdeploy) {
		this.wsdeploy = wsdeploy;
	}
	public String getWsName() {
		return wsName;
	}
	public void setWsName(String wsName) {
		this.wsName = wsName;
	}
	public String getDbType() {
		return dbType;
	}
	public void setDbType(String dbType) {
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
	public String getDbStore() {
		return dbStore;
	}
	public void setDbStore(String dbStore) {
		this.dbStore = dbStore;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}








	
}
