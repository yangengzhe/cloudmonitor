package com.ices.csp.common.enumitem;

import org.cspframework.core.dictionary.bean.entry.EnumEntry;


public enum ServerType implements EnumEntry{
	// 0
	JS("计算"),
	// 1
	CC("存储");
	private String value;
	
	private ServerType(String value) {
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
	public static ServerType getMatchByName(String name) {
		for (ServerType serverType : values()){
			if (serverType.value.equalsIgnoreCase(name)){
				return serverType;
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
	public static ServerType getMatchByOrdinal(Integer ordinal) {

		for (ServerType flag : values()) {
			if (flag.getKey().equals(ordinal)) {
				return flag;
			}
		}
		return null;
	}
}
