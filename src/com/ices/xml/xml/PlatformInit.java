package com.ices.xml.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ices.xml.metadata.XmlComponents;
import com.ices.xml.metadata.XmlEnterprises;
import com.ices.xml.metadata.XmlMenus;

/**
 * 
 * @author ywzhang
 * 
 */
@XmlRootElement(name="SaaS")
public class PlatformInit {

	private XmlComponents components;
	private XmlMenus menus;
	private XmlEnterprises enterprises;

	public XmlEnterprises getEnterprises() {
		return enterprises;
	}

	@XmlElement(name = "enterprises")
	public void setEnterprises(XmlEnterprises enterprises) {
		this.enterprises = enterprises;
	}

	public XmlMenus getMenus() {
		return menus;
	}

	@XmlElement(name = "menus")
	public void setMenus(XmlMenus menus) {
		this.menus = menus;
	}

	public XmlComponents getComponents() {
		return components;
	}

	@XmlElement(name = "components")
	public void setComponents(XmlComponents components) {
		this.components = components;
	}
}
