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
public class XmlGroups {

	private List<XmlGroup> groups;

	public List<XmlGroup> getGroups() {
		return groups;
	}

	@XmlElement(name = "group")
	public void setGroups(List<XmlGroup> groups) {
		this.groups = groups;
	}
}
