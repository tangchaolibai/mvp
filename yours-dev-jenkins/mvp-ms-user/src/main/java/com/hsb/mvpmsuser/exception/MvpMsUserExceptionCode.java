package com.hsb.mvpmsuser.exception;

public class MvpMsUserExceptionCode {

	// PROJECT CODE USER=01 SHARE=02 ASSISTANT=03 MESSAGE=04 TICKET=05 ...
	private static final String PROJECT_CODE = "01";
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
	public static final String ERROR_999_91 = ERROR_PREFIX + "999-91";
	
	/* API error */
	//jwt error
	public static final String ERROR_998_01 = ERROR_PREFIX + "998-01";
	public static final String ERROR_998_02 = ERROR_PREFIX + "998-02";
	public static final String ERROR_998_03 = ERROR_PREFIX + "998-03";
	public static final String ERROR_998_04 = ERROR_PREFIX + "998-04";
	public static final String ERROR_998_05 = ERROR_PREFIX + "998-05";
	
	public static final String ERROR_998_11 = ERROR_PREFIX + "998-11";
	
	// User login
	public static final String ERROR_001_01 = ERROR_PREFIX + "001-01";
	public static final String ERROR_001_02 = ERROR_PREFIX + "001-02";
	public static final String ERROR_001_03 = ERROR_PREFIX + "001-03";
	public static final String ERROR_001_04 = ERROR_PREFIX + "001-04";

	// User register
	public static final String ERROR_002_01 = ERROR_PREFIX + "002-01";
	
	// User log off
	public static final String ERROR_005_01 = ERROR_PREFIX + "005-01";
	
	// User log out
	public static final String ERROR_006_01 = ERROR_PREFIX + "006-01";

}
