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
public class XmlMenus {

	private List<XmlRootMenu> rootmenus;

	public List<XmlRootMenu> getRootmenus() {
		return rootmenus;
	}

	@XmlElement(name = "rootmenu")
	public void setRootmenus(List<XmlRootMenu> rootmenus) {
		this.rootmenus = rootmenus;
	}
}
