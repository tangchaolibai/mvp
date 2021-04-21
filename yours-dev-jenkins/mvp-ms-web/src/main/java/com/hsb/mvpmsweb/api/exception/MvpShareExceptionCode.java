package com.hsb.mvpmsweb.api.exception;

public class MvpShareExceptionCode extends MvpWebExceptionCode {

	private MvpShareExceptionCode() {}

	// PROJECT CODE USER=01 SHARE=02 ASSISTANT=03 MESSAGE=04 TICKET=05 ...
	private static final String PROJECT_CODE = "02";
	private static final String ERROR_PREFIX = "ERR-" + PROJECT_CODE + "-";

	/* API error */
	// ShareService
	public static final String ERROR_001_01 = ERROR_PREFIX + "001-01";

	public static final String ERROR_002_01 = ERROR_PREFIX + "002-01";

	public static final String ERROR_003_01 = ERROR_PREFIX + "003-01";

	public static final String ERROR_004_01 = ERROR_PREFIX + "004-01";

	public static final String ERROR_005_01 = ERROR_PREFIX + "005-01";

	public static final String ERROR_006_01 = ERROR_PREFIX + "006-01";

	public static final String ERROR_007_01 = ERROR_PREFIX + "007-01";

	public static final String ERROR_008_01 = ERROR_PREFIX + "008-01";

	public static final String ERROR_009_01 = ERROR_PREFIX + "009-01";

	public static final String ERROR_010_01 = ERROR_PREFIX + "010-01";

	public static final String ERROR_011_01 = ERROR_PREFIX + "011-01";
	
	public static final String ERROR_012_01 = ERROR_PREFIX + "012-01";
	
	public static final String ERROR_013_01 = ERROR_PREFIX + "013-01";
	
	public static final String ERROR_014_01 = ERROR_PREFIX + "014-01";
	
	public static final String ERROR_015_01 = ERROR_PREFIX + "015-01";

}
