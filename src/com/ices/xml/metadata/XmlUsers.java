package com.ices.xml.metadata;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author zywei
 * 
 */
@XmlType
public class XmlUsers {

	private List<XmlUser> users;

	public List<XmlUser> getUsers() {
		return users;
	}

	@XmlElement(name = "user")
	public void setUsers(List<XmlUser> users) {
		this.users = users;
	}
}
