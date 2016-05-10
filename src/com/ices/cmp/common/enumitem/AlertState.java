package com.ices.cmp.common.enumitem;

import org.cspframework.core.dictionary.bean.entry.EnumEntry;

public enum AlertState implements EnumEntry{
    
    WD("未读"),
    YD("已读");
    private String value;
    private AlertState(String value) {
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
    
}
