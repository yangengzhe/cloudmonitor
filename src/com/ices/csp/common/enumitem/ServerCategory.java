package com.ices.csp.common.enumitem;

import org.cspframework.core.dictionary.bean.entry.EnumEntry;

public enum ServerCategory implements EnumEntry{
	//0:机架；1:塔式；2:刀片
	// 0
	JJ("机架"),
	// 1
	TS("塔式"),
	// 2
	DP("刀片");
	private String value;
	
	private ServerCategory(String value) {
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
	public static ServerCategory getMatchByName(String name) {
		for (ServerCategory serverCategory : values()){
			if (serverCategory.value.equalsIgnoreCase(name)){
				return serverCategory;
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
	public static ServerCategory getMatchByOrdinal(Integer ordinal) {

		for (ServerCategory flag : values()) {
			if (flag.getKey().equals(ordinal)) {
				return flag;
			}
		}
		return null;
	}
}
