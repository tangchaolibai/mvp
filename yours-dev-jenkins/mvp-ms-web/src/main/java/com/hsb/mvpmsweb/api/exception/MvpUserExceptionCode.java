package com.hsb.mvpmsweb.api.exception;

public class MvpUserExceptionCode extends MvpWebExceptionCode {

	// PROJECT CODE USER=01 SHARE=02 ASSISTANT=03 MESSAGE=04 TICKET=05 ...
	private static final String PROJECT_CODE = "01";
	private static final String ERROR_PREFIX = "ERR-" + PROJECT_CODE + "-";
	
	/* API error */
	// User login
	public static final String ERROR_001_01 = ERROR_PREFIX + "001-01";
	public static final String ERROR_001_02 = ERROR_PREFIX + "001-02";
	public static final String ERROR_001_03 = ERROR_PREFIX + "001-03";
	public static final String ERROR_001_04 = ERROR_PREFIX + "001-04";

	// User register validation
	public static final String ERROR_001_05 = ERROR_PREFIX + "001-05";
	public static final String ERROR_001_06 = ERROR_PREFIX + "001-06";
	
	// User register
	public static final String ERROR_002_01 = ERROR_PREFIX + "002-01";
	
	// User edit profile
	public static final String ERROR_003_01 = ERROR_PREFIX + "003-01";
	
	// User log off
	public static final String ERROR_005_01 = ERROR_PREFIX + "005-01";
	
	// User log out
	public static final String ERROR_006_01 = ERROR_PREFIX + "006-01";
	
	// User forget password
	public static final String ERROR_007_01 = ERROR_PREFIX + "007-01";
	
}
