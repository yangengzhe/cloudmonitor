package com.ices.csp.common.enumitem;

import org.cspframework.core.dictionary.bean.entry.EnumEntry;

public enum NetType implements EnumEntry{
	// 0
	Intranet("内网"),
	// 1
	Lian("联通"),
	// 2
	Dian("电信");
	private String value;
	
	private NetType(String value) {
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
	public static NetType getMatchByName(String name) {
		for (NetType netType : values()){
			if (netType.value.equalsIgnoreCase(name)){
				return netType;
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
	public static NetType getMatchByOrdinal(Integer ordinal) {

		for (NetType flag : values()) {
			if (flag.getKey().equals(ordinal)) {
				return flag;
			}
		}
		return null;
	}


}
