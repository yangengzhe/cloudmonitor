package com.ices.csp.common.enumitem;

import org.cspframework.core.dictionary.bean.entry.EnumEntry;

public enum VMItemState implements EnumEntry{
	// 0
	applying("申请中"),
	// 1
	created("已创建"),
	// 2
	canceled("已取消"),
	// 3
	expired("已到期");
	private String value;
	
	private VMItemState(String value) {
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
	public static VMItemState getMatchByName(String name) {
		for (VMItemState vmitemState : values()){
			if (vmitemState.value.equalsIgnoreCase(name)){
				return vmitemState;
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
	public static VMItemState getMatchByOrdinal(Integer ordinal) {

		for (VMItemState flag : values()) {
			if (flag.getKey().equals(ordinal)) {
				return flag;
			}
		}
		return null;
	}
}
