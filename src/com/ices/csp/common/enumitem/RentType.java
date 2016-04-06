package com.ices.csp.common.enumitem;

import org.cspframework.core.dictionary.bean.entry.EnumEntry;

public enum RentType implements EnumEntry{
	// 0
	NEED("按需"),
	// 1
	RESE("预留（包年包月）");
	private String value;
	
	private RentType(String value) {
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
	public static RentType getMatchByName(String name) {
		for (RentType rentType : values()){
			if (rentType.value.equalsIgnoreCase(name)){
				return rentType;
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
	public static RentType getMatchByOrdinal(Integer ordinal) {

		for (RentType flag : values()) {
			if (flag.getKey().equals(ordinal)) {
				return flag;
			}
		}
		return null;
	}


}
