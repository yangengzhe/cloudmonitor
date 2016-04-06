package com.ices.csp.common.enumitem.cloudapp;

import org.cspframework.core.dictionary.bean.entry.EnumEntry;


public enum CloudAppState implements EnumEntry{
	//状态，registered(已注册)，configured(已配置)，deployed(已部署)，unloaded(已卸载)
	// 0
	registered("已注册"),
	// 1
	configured("已配置"),
	// 2
	deployed("已部署"),
	//3
	unloaded("已卸载");
	private String value;
	
	private CloudAppState(String value) {
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
	public static CloudAppState getMatchByName(String name) {
		for (CloudAppState cloudAppState : values()){
			if (cloudAppState.value.equalsIgnoreCase(name)){
				return cloudAppState;
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
	public static CloudAppState getMatchByOrdinal(Integer ordinal) {

		for (CloudAppState flag : values()) {
			if (flag.getKey().equals(ordinal)) {
				return flag;
			}
		}
		return null;
	}
}
