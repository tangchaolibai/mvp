package com.hsb.mvpmsweb.api.exception;

public class MvpWebExceptionCode {
	
	public MvpWebExceptionCode() {}

	// PROJECT CODE COMMON-EXCEPTION=00
	private static final String PROJECT_CODE = "00";
	private static final String ERROR_PREFIX = "ERR-" + PROJECT_CODE + "-";
	
	// 999 reserved for system error 
	public static final String ERROR_999_99 = ERROR_PREFIX + "999-99";
	public static final String ERROR_999_98 = ERROR_PREFIX + "999-98";
	public static final String ERROR_999_97 = ERROR_PREFIX + "999-97";
	public static final String ERROR_999_96 = ERROR_PREFIX + "999-96";
	public static final String ERROR_999_95 = ERROR_PREFIX + "999-95";
	public static final String ERROR_999_94 = ERROR_PREFIX + "999-94";
	public static final String ERROR_999_93 = ERROR_PREFIX + "999-93";
	public static final String ERROR_999_92 = ERROR_PREFIX + "999-92";
	
	// jwt error
	public static final String ERROR_998_01 = ERROR_PREFIX + "998-01";
	public static final String ERROR_998_02 = ERROR_PREFIX + "998-02";
	public static final String ERROR_998_03 = ERROR_PREFIX + "998-03";
	public static final String ERROR_998_04 = ERROR_PREFIX + "998-04";
	public static final String ERROR_998_05 = ERROR_PREFIX + "998-05";
	public static final String ERROR_998_11 = ERROR_PREFIX + "998-11";
	
}
