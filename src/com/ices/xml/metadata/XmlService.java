package com.ices.xml.metadata;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author zywei
 * 
 */
@XmlType
public class XmlService {
	private String serviceCode;
	private String serviceName;
	private String serviceUrl;
	private String description;
	private String parameters;
	

	public String getServiceCode() {
		return serviceCode;
	}

	@XmlAttribute(name = "serviceCode")
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceName() {
		return serviceName;
	}

	@XmlAttribute(name = "serviceName")
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceUrl() {
		return serviceUrl;
	}

	@XmlAttribute(name = "serviceUrl")
	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public String getDescription() {
		return description;
	}

	@XmlAttribute(name = "description")
	public void setDescription(String description) {
		this.description = description;
	}

	public String getParameters() {
		return parameters;
	}

	@XmlAttribute(name = "parameters")
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

}
