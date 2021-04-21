package com.hsb.mvpmsweb.api.exception;

public class MvpTicketExceptionCode extends MvpWebExceptionCode {

	// PROJECT CODE USER=01 SHARE=02 ASSISTANT=03 MESSAGE=04 TICKET=05 ...
	private static final String PROJECT_CODE = "05";
	private static final String ERROR_PREFIX = "ERR-" + PROJECT_CODE + "-";

	/* API error */
	public static final String ERROR_001_01 = ERROR_PREFIX + "001-01";
	public static final String ERROR_001_02 = ERROR_PREFIX + "001-02";
	public static final String ERROR_001_03 = ERROR_PREFIX + "001-03";
	public static final String ERROR_001_04 = ERROR_PREFIX + "001-04";
	public static final String ERROR_001_05 = ERROR_PREFIX + "001-05";

	public static final String ERROR_002_01 = ERROR_PREFIX + "002-01";
	public static final String ERROR_002_02 = ERROR_PREFIX + "002-02";
	
	public static final String ERROR_003_01 = ERROR_PREFIX + "003-01";
	public static final String ERROR_003_02 = ERROR_PREFIX + "003-02";
	public static final String ERROR_003_03 = ERROR_PREFIX + "003-03";
	public static final String ERROR_003_04 = ERROR_PREFIX + "003-04";
	public static final String ERROR_003_05 = ERROR_PREFIX + "003-05";
	
	public static final String ERROR_004_01 = ERROR_PREFIX + "004-01";
	public static final String ERROR_004_02 = ERROR_PREFIX + "004-02";
	public static final String ERROR_004_03 = ERROR_PREFIX + "004-03";
	public static final String ERROR_004_04 = ERROR_PREFIX + "004-04";
	public static final String ERROR_004_05 = ERROR_PREFIX + "004-05";
	
}
