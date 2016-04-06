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
public class XmlEnterprises {

	private List<XmlEnterprise> enterprises;

	public List<XmlEnterprise> getEnterprises() {
		return enterprises;
	}

	@XmlElement(name = "enterprise")
	public void setEnterprises(List<XmlEnterprise> enterprises) {
		this.enterprises = enterprises;
	}

}
