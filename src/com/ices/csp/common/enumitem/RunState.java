package com.ices.csp.common.enumitem;

import org.cspframework.core.dictionary.bean.entry.EnumEntry;

public enum RunState implements EnumEntry{
	// 0
	Shutdown("关机"),
	// 1
	ON("开机"),
	// 2
	Standby("待机");
	private String value;
	
	private RunState(String value) {
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
	public static RunState getMatchByName(String name) {
		for (RunState runState : values()){
			if (runState.value.equalsIgnoreCase(name)){
				return runState;
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
	public static RunState getMatchByOrdinal(Integer ordinal) {

		for (RunState flag : values()) {
			if (flag.getKey().equals(ordinal)) {
				return flag;
			}
		}
		return null;
	}
}
