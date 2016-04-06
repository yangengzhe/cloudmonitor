package com.ices.csp.common.enumitem.cloudapp;

import org.cspframework.core.dictionary.bean.entry.EnumEntry;

public enum CloudAppType implements EnumEntry{
	//部署模式，0:本地部署，1:云平台部署
	// 0
	BD("本地部署"),
	// 1
	PT("云平台部署");
	private String value;
	
	private CloudAppType(String value) {
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
	public static CloudAppType getMatchByName(String name) {
		for (CloudAppType cloudAppType : values()){
			if (cloudAppType.value.equalsIgnoreCase(name)){
				return cloudAppType;
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
	public static CloudAppType getMatchByOrdinal(Integer ordinal) {

		for (CloudAppType flag : values()) {
			if (flag.getKey().equals(ordinal)) {
				return flag;
			}
		}
		return null;
	}
}
