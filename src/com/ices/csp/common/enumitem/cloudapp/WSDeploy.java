package com.ices.csp.common.enumitem.cloudapp;

import org.cspframework.core.dictionary.bean.entry.EnumEntry;

public enum WSDeploy implements EnumEntry{
	//部署方式，0 共享，1独立
	// 0
	GX("共享"),
	// 1
	DL("独立");
	private String value;
	
	private WSDeploy(String value) {
		this.value = value;
	}

	@Override
	public Object getKey() {
		return this.ordinal();
	}

	@Override
	public String getValue() {
		return value;
	}

	/**
	 * 按名字检索
	 * 
	 * @param name
	 * @return 顺序号
	 */
	public static WSDeploy getMatchByName(String name) {
		for (WSDeploy wsdeploy : values()){
			if (wsdeploy.value.equalsIgnoreCase(name)){
				return wsdeploy;
			}
		}
		return null;
	}

	/**
	 * 按ordinal检索
	 * 
	 * @param ordinal
	 * @return
	 */
	public static WSDeploy getMatchByOrdinal(Integer ordinal) {

		for (WSDeploy flag : values()) {
			if (flag.getKey().equals(ordinal)) {
				return flag;
			}
		}
		return null;
	}
}
