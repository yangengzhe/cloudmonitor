package com.beiyanght.csms.common.helper.formula;

import java.util.ArrayList;

/**
 * 功能名	共通-名称公式生成器
 * 类描述	公式对象
 * @author  郭董宁
 */
public class Formula {
	/*-------------------类常量定义--------------------*/
	
	/*-------------------类成员定义--------------------*/
	private ArrayList<FormulaUnit> formulaUnitList;
	
	public Formula(String formulaFormat){
		formulaUnitList = new ArrayList<FormulaUnit>();
		String[] formulaArray = formulaFormat.split("@");
		for(int i = 0;i<formulaArray.length;i++){
			addFormula(formulaArray[i]);
		}
	}
	/*-------------------业务逻辑--------------------*/
	/**
	 * 增加公式单元
	 * @param formulaFormat 单元公式
	 */
	private void addFormula(String formulaFormat){
		FormulaUnit newFormulaUnit = null;
		FormulaUnit lastUnit = null;
		// 取得上一节点
		if(formulaUnitList.size()>0){
			lastUnit = formulaUnitList.get(formulaUnitList.size()-1);
		}
		if(formulaFormat.startsWith("/")){
			String[] alphabet = {formulaFormat.replaceFirst("/", "")};
			newFormulaUnit = new FixedUnit(lastUnit, alphabet);
		} else if(formulaFormat.startsWith("X")){
			newFormulaUnit = new AlphaUnit(lastUnit, formulaFormat, "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		} else if(formulaFormat.startsWith("x")){
			newFormulaUnit = new AlphaUnit(lastUnit, formulaFormat, "abcdefghijklmnopqrstuvwxyz");
		} else if(formulaFormat.startsWith("9")){
			newFormulaUnit = new AlphaUnit(lastUnit, formulaFormat, "0123456789");
		}
		
		formulaUnitList.add(newFormulaUnit);
	}
	
	/**
	 * 公式增加操作
	 */
	public void addNumber(){
		formulaUnitList.get(formulaUnitList.size()-1).add();
	}
	
	/**
	 * 公式值取得操作
	 * @return 公式当前值
	 */
	public String getNumber(){
		StringBuffer resultBuffer = new StringBuffer();
		for(FormulaUnit formulaUnit : formulaUnitList){
			resultBuffer.append(formulaUnit.get());
		}
		return resultBuffer.toString();
	}
}
