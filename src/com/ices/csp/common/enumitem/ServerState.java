package com.ices.csp.common.enumitem;

import org.cspframework.core.dictionary.bean.entry.EnumEntry;

public enum ServerState implements EnumEntry{
	// 0
	TJ("停机"),
	// 1
	YX("运行");
	private String value;
	
	private ServerState(String value) {
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
	public static ServerState getMatchByName(String name) {
		for (ServerState serverState : values()){
			if (serverState.value.equalsIgnoreCase(name)){
				return serverState;
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
	public static ServerState getMatchByOrdinal(Integer ordinal) {

		for (ServerState flag : values()) {
			if (flag.getKey().equals(ordinal)) {
				return flag;
			}
		}
		return null;
	}
}
