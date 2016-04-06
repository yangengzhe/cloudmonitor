package com.beiyanght.csms.common.helper;  

import java.io.UnsupportedEncodingException;

/**  
 * 字符串辅助类
 *  
 * @author 王吉文
 */
public class StringHelp {
	
	private enum Flag{
		TAIL,// 头部  
		HEAD;// 尾部
	}
	
	/**
	 * 将一个String以头部加空格的方式凑长度
	 */
	public static String headBlank(String orig, int length){
		return buildLength(orig, length, Flag.HEAD, " ");
	}
	
	/**
	 * 将一个String以尾部加空格的方式凑长度
	 */
	public static String tailBlank(String orig, int length){
		return buildLength(orig, length, Flag.TAIL, " ");
	}
	
	/**
	 * 将一个String以头部加0的方式凑长度
	 */
	public static String headZero(String orig, int length){
		return buildLength(orig, length, Flag.HEAD, "0");
	}
	
	/**
	 * 将一个String以尾部加0的方式凑长度
	 */
	public static String tailZero(String orig, int length){
		return buildLength(orig, length, Flag.TAIL, "0");
	}
	
	/**
	 * 给一个String凑长度
	 * @param orig 原字符串
	 * @param length 要求长度
	 * @param headOrTail 头部/尾部
	 * @param addString 要填充的字符串
	 * @return
	 */
	private static String buildLength(String orig, int length, Flag headOrTail, String addString) {
		StringBuilder builder = new StringBuilder(orig);
		switch(headOrTail){
			case HEAD:
				while(builder.length()<length){
					builder.insert(0, addString);
				}
				break;
			case TAIL:
				while(builder.length()<length){
					builder.append(addString);
				}
				break;
			default:
				new Exception("只支持在头部和尾部凑齐字符串长度!").printStackTrace();
		}
		return builder.toString();
	}
	
	/**
	 * 求一个字符串中某个字符串的个数
	 */
	public static int numOfSomeStr(String orig, String dest) {
		int i = 0;
		while(orig.contains(dest)){
			orig = orig.replaceFirst(dest, "");
			i++;
		}
		return i;
	}
	
	/**
	 * 获取字符串中某个字符串的位置
	 * @param	str		某个字符串
	 * @param	mdedia	要寻找的字符串
	 * @param	maxQty	最多可能有多少该字符串
	 * @return	返回的数组，从index 1开始
	 */
	public static Integer[] mediaLoaction(String str, String media, int maxQty) {
		int indexFrom = 0;
		int i = 0;
		Integer[] arr = new Integer[maxQty];
		for(int index=str.indexOf(media); index !=-1; index = str.indexOf(media, indexFrom)){
			i++;
			arr[i] = index;
			indexFrom = index + 1;
		}
		return arr;
	}
	
	/**
	 * 返回一个字符串的非空值
	 * @return	若为null,则返回"";否则返回字符串本身
	 */
	public static String noNull(String original) {
		return (original == null)?"":original;
	}
	
	public static String noNull(Object object) {
		return object == null ? "":object.toString();
	}
	
	public static boolean nullOrEmpty(String str) {
		if(null == str || "".equals(str)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 末尾添加空格
	 * @param orig
	 * @param length
	 */
	public static void tailBlank(StringBuilder orig, int length) {
		while(orig.length() < length) {
			orig.append(" ");
		}
	}
	
	/**
	 * 判空
	 */
	public static boolean empty(String str) {
		if(str == null || "".equals(str.trim())) {
			return true;
		}
		return false;
	}
	
	/**
	 * 获取字符串的16进制字符串表示,用于字符串比较
	 */
    public static String getHexString(String s, String charset) {
        byte[] b = null;
        StringBuffer sb = new StringBuffer();
        try {
            b = s.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if(b!=null){
	        for (int i = 0; i < b.length; i++) {
	            sb.append(Integer.toHexString(b[i] & 0xFF));
	        }
        }
        return sb.toString();
    }
    
    /**
     * 获取字符串的16进制字符串表示,用于字符串比较,gb2312编码
     */
    public static String hexString(String s) {
    	return getHexString(s, "gb2312");
    }
	
}
  