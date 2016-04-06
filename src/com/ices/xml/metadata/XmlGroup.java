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
public class XmlGroup {

	private String groupName;
	private String description;
	private XmlMenus menus;

	public String getGroupName() {
		return groupName;
	}

	@XmlAttribute(name = "groupName")
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getDescription() {
		return description;
	}

	@XmlAttribute(name = "description")
	public void setDescription(String description) {
		this.description = description;
	}

	public XmlMenus getMenus() {
		return menus;
	}
	@XmlElement(name = "menus")
	public void setMenus(XmlMenus menus) {
		this.menus = menus;
	}

}
