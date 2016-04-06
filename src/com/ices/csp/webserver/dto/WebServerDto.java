/**
 * 
 */
package com.ices.csp.webserver.dto;


import org.cspframework.core.dto.annotation.DtoClass;
import org.cspframework.core.dto.annotation.DtoProperty;

import com.ices.csp.webserver.domain.WebServer;



/**
 * @author MFC
 *
 */
@DtoClass(entities={WebServer.class})
public class WebServerDto {

	@DtoProperty(entityClass=WebServer.class)
	private Integer id;//标识(关键字)
	
	@DtoProperty(entityClass=WebServer.class)
	private String name;//ws名称
	
	@DtoProperty(entityClass=WebServer.class, entityProperty="type.id")
	private Integer softwareId;//软件Id
	
	@DtoProperty(entityClass=WebServer.class, entityProperty="type.name")
	private String softwareName;//软件名称
	
	@DtoProperty(entityClass=WebServer.class)
	private String state;//状态
		
	@DtoProperty(entityClass=WebServer.class)
	private String ip;//ip地址
	
	@DtoProperty(entityClass=WebServer.class)
	private Integer port;//端口号
	
	@DtoProperty(entityClass=WebServer.class)
	private String webapps;//ws发布地址
	
	@DtoProperty(entityClass=WebServer.class)
	private String storage;//ws存储地址
	
	@DtoProperty(entityClass=WebServer.class)
	private String protocol;//协议，默认HTTP/1.1
	
	@DtoProperty(entityClass=WebServer.class)
	private Integer maxThreads;//最大线程数
	
	@DtoProperty(entityClass=WebServer.class)
	private Integer minSpareThreads;//最小空闲线程数
	
	@DtoProperty(entityClass=WebServer.class)
	private Integer maxSpareThreads;//最大空闲线程数
	
	@DtoProperty(entityClass=WebServer.class)
	private Integer acceptCount;//排队请求数
	
	@DtoProperty(entityClass=WebServer.class)
	private Integer connectionTimeout;//排队请求数
	
	@DtoProperty(entityClass=WebServer.class)
	private String demo;//描述
	
	private Integer count;//已部署云应用数量
	
	private Integer usersize;//可用大小
	
	private Integer userperson;//可用并发用户数
	/////////////////////////////////////////////////////////////////////////////
	
	public Integer getId() {
		return id;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getUsersize() {
		return usersize;
	}

	public void setUsersize(Integer usersize) {
		this.usersize = usersize;
	}

	public Integer getUserperson() {
		return userperson;
	}

	public void setUserperson(Integer userperson) {
		this.userperson = userperson;
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

	public Integer getSoftwareId() {
		return softwareId;
	}

	public void setSoftwareId(Integer softwareId) {
		this.softwareId = softwareId;
	}

	public String getSoftwareName() {
		return softwareName;
	}

	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
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
	
	
	
	
}
