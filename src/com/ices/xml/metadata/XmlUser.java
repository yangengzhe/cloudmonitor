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
public class XmlUser {

	private String userName;
	private String password;
	private String name;
	private String sex;
	private String theme;
	private String state;
	private String userType;
	private List<XmlUserGroup> userGroups;

	public String getUserName() {
		return userName;
	}

	@XmlAttribute(name = "userName")
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	@XmlAttribute(name = "password")
	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	@XmlAttribute(name = "name")
	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	@XmlAttribute(name = "sex")
	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTheme() {
		return theme;
	}

	@XmlAttribute(name = "theme")
	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getState() {
		return state;
	}

	@XmlAttribute(name = "state")
	public void setState(String state) {
		this.state = state;
	}

	public String getUserType() {
		return userType;
	}

	@XmlAttribute(name = "userType")
	public void setUserType(String userType) {
		this.userType = userType;
	}

	public List<XmlUserGroup> getUserGroups() {
		return userGroups;
	}

	@XmlElement(name = "user_group")
	public void setUserGroups(List<XmlUserGroup> userGroups) {
		this.userGroups = userGroups;
	}

}
