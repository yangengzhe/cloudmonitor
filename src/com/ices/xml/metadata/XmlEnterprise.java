package com.ices.xml.metadata;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author zywei
 * 
 */
@XmlType
public class XmlEnterprise {

	private String enterpriseCode;
	private String enterpriseName;
	private String abbreviation;
	private String state;
	private String description;
	private String serviceType;
	private XmlGroups groups;
	private XmlUsers users;

	public String getEnterpriseCode() {
		return enterpriseCode;
	}

	@XmlAttribute(name = "enterpriseCode")
	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	@XmlAttribute(name = "enterpriseName")
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	@XmlAttribute(name = "abbreviation")
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getState() {
		return state;
	}

	@XmlAttribute(name = "state")
	public void setState(String state) {
		this.state = state;
	}

	public String getDescription() {
		return description;
	}

	@XmlAttribute(name = "description")
	public void setDescription(String description) {
		this.description = description;
	}

	public String getServiceType() {
		return serviceType;
	}

	@XmlAttribute(name = "serviceType")
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public XmlGroups getGroups() {
		return groups;
	}

	@XmlElement(name = "groups")
	public void setGroups(XmlGroups groups) {
		this.groups = groups;
	}

	public XmlUsers getUsers() {
		return users;
	}

	@XmlElement(name = "users")
	public void setUsers(XmlUsers users) {
		this.users = users;
	}

}
