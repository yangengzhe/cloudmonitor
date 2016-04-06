package com.ices.csp.common.enumitem;

import org.cspframework.core.dictionary.bean.entry.EnumEntry;

public enum AuditOpinion implements EnumEntry{
	// 0
	NO("未通过"),
	// 1
	YES("通过"),
	// 2
	NOTALL("部分通过");
	private String value;
	
	private AuditOpinion(String value) {
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
	public static AuditOpinion getMatchByName(String name) {
		for (AuditOpinion auditOpinion : values()){
			if (auditOpinion.value.equalsIgnoreCase(name)){
				return auditOpinion;
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
	public static AuditOpinion getMatchByOrdinal(Integer ordinal) {

		for (AuditOpinion flag : values()) {
			if (flag.getKey().equals(ordinal)) {
				return flag;
			}
		}
		return null;
	}
}
