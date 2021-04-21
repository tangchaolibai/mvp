package com.hsb.mvpmsweb.api.exception;

public class MvpMessageExceptionCode extends MvpWebExceptionCode {

	private MvpMessageExceptionCode() {}

	// PROJECT CODE USER=01 SHARE=02 ASSISTANT=03 MESSAGE=04 TICKET=05 ...
	private static final String PROJECT_CODE = "04";
	private static final String ERROR_PREFIX = "ERR-" + PROJECT_CODE + "-";

	/* API error */
	public static final String ERROR_001_01 = ERROR_PREFIX + "001-01";

}
