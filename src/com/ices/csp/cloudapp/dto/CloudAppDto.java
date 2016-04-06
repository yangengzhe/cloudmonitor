package com.ices.csp.cloudapp.dto;

import org.cspframework.core.dto.annotation.DtoClass;
import org.cspframework.core.dto.annotation.DtoProperty;

import com.ices.csp.cloudapp.domain.CloudApp;

@DtoClass(entities = CloudApp.class)
public class CloudAppDto {
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
	@DtoProperty(entityClass=CloudApp.class,entityProperty="wsType.id")
	private Integer wsType;//应用服务器类型
	
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
	@DtoProperty(entityClass=CloudApp.class,entityProperty="dbType.id")
	private Integer dbType;//数据库类型
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



	public Integer getWsType() {
		return wsType;
	}

	public void setWsType(Integer wsType) {
		this.wsType = wsType;
	}

	public Integer getDbType() {
		return dbType;
	}

	public void setDbType(Integer dbType) {
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
	
	
}
