package com.ices.csp.common.enumitem;

import org.cspframework.core.dictionary.bean.entry.EnumEntry;

public enum VMOrderState implements EnumEntry{
	// 0
	applying("申请中"),
	// 1
	audited("已审批"),
	// 2
	dispatched("已下达"),
	// 3
	canceled("已取消"),
	// 4
	finished("已完成");
	private String value;
	
	private VMOrderState(String value) {
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
	public static VMOrderState getMatchByName(String name) {
		for (VMOrderState vmorderState : values()){
			if (vmorderState.value.equalsIgnoreCase(name)){
				return vmorderState;
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
	public static VMOrderState getMatchByOrdinal(Integer ordinal) {

		for (VMOrderState flag : values()) {
			if (flag.getKey().equals(ordinal)) {
				return flag;
			}
		}
		return null;
	}
}
