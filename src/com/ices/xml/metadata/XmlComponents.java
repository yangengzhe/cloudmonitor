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
public class XmlComponents {

	private List<XmlComponent> components;

	public List<XmlComponent> getComponents() {
		return components;
	}

	@XmlElement(name = "component")
	public void setComponents(List<XmlComponent> components) {
		this.components = components;
	}
}
