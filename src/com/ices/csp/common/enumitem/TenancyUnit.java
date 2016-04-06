package com.ices.csp.common.enumitem;

import org.cspframework.core.dictionary.bean.entry.EnumEntry;

public enum TenancyUnit implements EnumEntry{
	// 0
	HOUR("小时"),
	// 1
	DAY("天"),
	// 2
	MONTH("月"),
	// 3
	YEAR("年")
	;
	private String value;
	
	private TenancyUnit(String value) {
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
	public static TenancyUnit getMatchByName(String name) {
		for (TenancyUnit tenancyUnit : values()){
			if (tenancyUnit.value.equalsIgnoreCase(name)){
				return tenancyUnit;
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
	public static TenancyUnit getMatchByOrdinal(Integer ordinal) {

		for (TenancyUnit flag : values()) {
			if (flag.getKey().equals(ordinal)) {
				return flag;
			}
		}
		return null;
	}
}
