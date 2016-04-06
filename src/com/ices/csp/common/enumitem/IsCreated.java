package com.ices.csp.common.enumitem;

import org.cspframework.core.dictionary.bean.entry.EnumEntry;

public enum IsCreated implements EnumEntry{
	// 0
	NO("不创建"),
	// 1
	YES("创建");
	private String value;
	
	private IsCreated(String value) {
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
	public static IsCreated getMatchByName(String name) {
		for (IsCreated iscreated : values()){
			if (iscreated.value.equalsIgnoreCase(name)){
				return iscreated;
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
	public static IsCreated getMatchByOrdinal(Integer ordinal) {

		for (IsCreated flag : values()) {
			if (flag.getKey().equals(ordinal)) {
				return flag;
			}
		}
		return null;
	}
}
