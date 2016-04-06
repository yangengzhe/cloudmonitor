package com.beiyanght.csms.common.helper;

import java.util.regex.Matcher;

/**
 * Regex正则帮助类
 * 
 * @author  王吉文
 */
public class RegexHelp {
	
	/**
	 * 匹配次数
	 */
	public static int matchCount(Matcher m) {
		int i = 0;
		while(m.find()) {
			i++;
		}
		return i;
	}
	
}

