package com.ices.csp.common.enumitem;

import org.cspframework.core.dictionary.bean.entry.EnumEntry;

public enum IsVirtual implements EnumEntry{
	// 0未虚拟化
	NO("否"),
	// 1已虚拟化
	YES("是");
	private String value;
	
	private IsVirtual(String value) {
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
	public static IsVirtual getMatchByName(String name) {
		for (IsVirtual isVirtual : values()){
			if (isVirtual.value.equalsIgnoreCase(name)){
				return isVirtual;
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
	public static IsVirtual getMatchByOrdinal(Integer ordinal) {

		for (IsVirtual flag : values()) {
			if (flag.getKey().equals(ordinal)) {
				return flag;
			}
		}
		return null;
	}
}
