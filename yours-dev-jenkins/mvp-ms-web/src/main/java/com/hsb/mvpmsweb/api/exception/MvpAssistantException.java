package com.hsb.mvpmsweb.api.exception;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MvpAssistantException extends Exception {

	private static final long serialVersionUID = 1L;

	private String code;
	private String message;
	private Throwable stack;
	private Object[] args;

	protected MvpAssistantException(String code, String message) {
		super(String.valueOf(code) + " " + message);
		this.code = code;
		this.message = message;
	}

	protected MvpAssistantException(String code, String message, Throwable stack) {
		super(String.valueOf(code) + " " + message, stack);
		this.stack = stack;
		this.code = code;
		this.message = message;
	}

	protected MvpAssistantException(Map<String, String> exception) {
		this(exception.get("code"), exception.get("message"));
	}

	protected MvpAssistantException(Map<String, String> exception, Throwable errStack) {
		this(exception.get("code"), exception.get("message"), errStack);
	}

	public MvpAssistantException(String exCode) {
		this(exCode, "");
	}
	
	public MvpAssistantException(String exCode, Object[] args) {
		this(exCode, "");
		this.args = args;
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.code) + " " + this.message;
	}
	
}
