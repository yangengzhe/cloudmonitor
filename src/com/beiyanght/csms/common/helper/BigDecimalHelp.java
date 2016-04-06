package com.beiyanght.csms.common.helper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class BigDecimalHelp {
	
	/**
	 * 四舍五入
	 */
	public static BigDecimal halfUp(BigDecimal decimal, int scale) {
		return decimal.divide(BigDecimal.ONE, scale, RoundingMode.HALF_UP);
	}
	
	/**
	 * 四舍五入取整
	 */
	public static BigInteger halfUpInt(BigDecimal decimal) {
		return halfUp(decimal, 0).toBigInteger();
	}

}
