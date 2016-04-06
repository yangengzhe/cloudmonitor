package com.beiyanght.csms.common.helper.formula;

/**
 * 功能名	共通-名称公式生成器
 * 类描述	变字公式单元
 * @author  郭董宁
 */
public class AlphaUnit implements FormulaUnit{
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
	 * @param formulaFormat 单元公式
	 * @param baseAlphabet 基础字母表
	 */
	public AlphaUnit(FormulaUnit lastUnit,String formulaFormat, String baseAlphabet){
		this.lastUnit = lastUnit;
		
		// 取得上限下限
		formulaFormat = formulaFormat.substring(2, 5);
		String[] limitArray = formulaFormat.split(",");
		int lowerLimit = baseAlphabet.indexOf(limitArray[0].toUpperCase());
		int upperLimit = baseAlphabet.indexOf(limitArray[1].toUpperCase());
		// 截取字母表
		alphabet = new String[upperLimit-lowerLimit+1];
		for(int i = lowerLimit;i<=upperLimit;i++){
			this.alphabet[i-lowerLimit] = (String.valueOf(baseAlphabet.charAt(i)));
		}
	}
	/*-------------------业务逻辑--------------------*/
	/**
	 * 公式单元值增加操作
	 */
	public void add(){
		if(pointer<alphabet.length-1){
			pointer++;
		} else {
			pointer = 0;
			if(lastUnit!=null){
				lastUnit.add();
			}
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
