/**
 * 
 */
package com.ices.csp.database.dto;

import org.cspframework.core.dto.annotation.DtoClass;
import org.cspframework.core.dto.annotation.DtoProperty;

import com.ices.csp.database.domain.Database;




/**
 * @author MFC
 *
 */
@DtoClass(entities={Database.class})
public class DatabaseDto {
	@DtoProperty(entityClass=Database.class)
	private Integer id;//标识(关键字)
	
	@DtoProperty(entityClass=Database.class)
	private String name;//名称
	
	@DtoProperty(entityClass=Database.class, entityProperty="type.id")
	private Integer softwareId;//软件Id
	
	@DtoProperty(entityClass=Database.class, entityProperty="type.name")
	private String softwareName;//软件名称
		
	@DtoProperty(entityClass=Database.class)
	private String ip;//ip地址
	
	@DtoProperty(entityClass=Database.class)
	private Integer port;//端口号
	
	@DtoProperty(entityClass=Database.class)
	private String storage;//存储地址
		
	@DtoProperty(entityClass=Database.class)
	private String demo;//描述
	private Integer count;//已部署云应用数量
	
	private Integer usersize;//可用大小
	
	private Integer userperson;//可用并发用户数
	
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
	
}
