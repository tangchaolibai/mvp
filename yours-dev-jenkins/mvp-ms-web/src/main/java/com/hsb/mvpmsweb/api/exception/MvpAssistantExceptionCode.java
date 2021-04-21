package com.hsb.mvpmsweb.api.exception;

public class MvpAssistantExceptionCode extends MvpWebExceptionCode {

	private MvpAssistantExceptionCode() {}

	// PROJECT CODE USER=01 SHARE=02 ASSISTANT=03 MESSAGE=04 TICKET=05 ...
	private static final String PROJECT_CODE = "03";
	private static final String ERROR_PREFIX = "ERR-" + PROJECT_CODE + "-";

	/* API error */
	public static final String ERROR_001_01 = ERROR_PREFIX + "001-01";
	public static final String ERROR_001_02 = ERROR_PREFIX + "001-02";
	
}
