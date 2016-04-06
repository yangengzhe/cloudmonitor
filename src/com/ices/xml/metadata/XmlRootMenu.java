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
public class XmlRootMenu {

	private String idSeq;
	private String menuName;
	private String description;
	private String level;
	private String leaf;
	private String displayOrder;
	private String componentCode;
	private String serviceName;
	private List<XmlMenu> menus;

	public String getIdSeq() {
		return idSeq;
	}

	@XmlAttribute(name = "idSeq")
	public void setIdSeq(String idSeq) {
		this.idSeq = idSeq;
	}

	public String getMenuName() {
		return menuName;
	}

	@XmlAttribute(name = "menuName")
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getDescription() {
		return description;
	}

	@XmlAttribute(name = "description")
	public void setDescription(String description) {
		this.description = description;
	}

	public String getLevel() {
		return level;
	}

	@XmlAttribute(name = "level")
	public void setLevel(String level) {
		this.level = level;
	}

	public String getLeaf() {
		return leaf;
	}

	@XmlAttribute(name = "leaf")
	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}

	public String getDisplayOrder() {
		return displayOrder;
	}

	@XmlAttribute(name = "displayOrder")
	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getComponentCode() {
		return componentCode;
	}

	@XmlAttribute(name = "componentCode")
	public void setComponentCode(String componentCode) {
		this.componentCode = componentCode;
	}

	public String getServiceName() {
		return serviceName;
	}

	@XmlAttribute(name = "serviceName")
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public List<XmlMenu> getMenus() {
		return menus;
	}

	@XmlElement(name = "menu")
	public void setMenus(List<XmlMenu> menus) {
		this.menus = menus;
	}

}
