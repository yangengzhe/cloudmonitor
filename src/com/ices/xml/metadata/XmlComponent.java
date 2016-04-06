package com.ices.xml.metadata;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author zywei
 * 
 */
@XmlType
public class XmlComponent {

	private String componentCode;
	private String componentName;
	private String state;
	private String bizUrl;
	private String webUrl;
	private String developer;
	private String description;
	private List<XmlService> services;

	public String getComponentCode() {
		return componentCode;
	}

	@XmlAttribute(name = "componentCode")
	public void setComponentCode(String componentCode) {
		this.componentCode = componentCode;
	}

	public String getComponentName() {
		return componentName;
	}

	@XmlAttribute(name = "componentName")
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getState() {
		return state;
	}

	@XmlAttribute(name = "state")
	public void setState(String state) {
		this.state = state;
	}

	public String getBizUrl() {
		return bizUrl;
	}

	@XmlAttribute(name = "bizUrl")
	public void setBizUrl(String bizUrl) {
		this.bizUrl = bizUrl;
	}

	public String getWebUrl() {
		return webUrl;
	}

	@XmlAttribute(name = "webUrl")
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public String getDeveloper() {
		return developer;
	}

	@XmlAttribute(name = "developer")
	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public String getDescription() {
		return description;
	}
	
	@XmlAttribute(name = "description")
	public void setDescription(String description) {
		this.description = description;
	}

	public List<XmlService> getServices() {
		return services;
	}

	@XmlElement(name = "service")
	public void setServices(List<XmlService> services) {
		this.services = services;
	}

}
