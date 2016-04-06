package com.beiyanght.csms.common.helper.formula;

/**
 * 功能名	共通-名称公式生成器
 * 类描述	定字公式单元
 * @author  郭董宁
 */
public class FixedUnit implements FormulaUnit{
	/*-------------------类常量定义--------------------*/
	
	/*-------------------类成员定义--------------------*/
	private FormulaUnit lastUnit;
	
	private String[] alphabet;
	
	private int pointer;
	
	public FormulaUnit getLastUnit() {
		return lastUnit;
	}

	public void setLastUnit(FormulaUnit lastUnit) {
		this.lastUnit = lastUnit;
	}

	public String[] getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(String[] alphabet) {
		this.alphabet = alphabet;
	}

	public int getPointer() {
		return pointer;
	}

	public void setPointer(int pointer) {
		this.pointer = pointer;
	}
	
	/**
	 * 构造函数
	 * @param lastUnit 上一位单元
	 * @param alphabet 基础字母表
	 */
	public FixedUnit(FormulaUnit lastUnit,String[] alphabet){
		this.lastUnit = lastUnit;
		this.alphabet = alphabet;
	}
	/*-------------------业务逻辑--------------------*/
	/**
	 * 公式单元值增加操作
	 */
	public void add(){
		if(lastUnit!=null){
			lastUnit.add();
		}
	}
	/**
	 * 公式单元值取得操作
	 */
	public String get(){
		String result = alphabet[pointer];
		return result;
	}
}
