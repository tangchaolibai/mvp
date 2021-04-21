package com.hsb.mvpmsticket.exception;

import java.util.Map;

public class MvpMsTicketException extends Exception {

	private static final long serialVersionUID = 1L;

	private String code;
	private String message;
	private Throwable stack;
	private Object[] args;

	protected MvpMsTicketException(String code, String message) {
		super(String.valueOf(code) + " " + message);
		this.code = code;
		this.message = message;
	}

	protected MvpMsTicketException(String code, String message, Throwable stack) {
		super(String.valueOf(code) + " " + message, stack);
		this.stack = stack;
		this.code = code;
		this.message = message;
	}

	protected MvpMsTicketException(Map<String, String> exception) {
		this(exception.get("code"), exception.get("message"));
	}

	protected MvpMsTicketException(Map<String, String> exception, Throwable errStack) {
		this(exception.get("code"), exception.get("message"), errStack);
	}

	public MvpMsTicketException(String exCode) {
		this(exCode, "");
	}
	
	public MvpMsTicketException(String exCode, Object[] args) {
		this(exCode, "");
		this.args = args;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Throwable getStack() {
		return stack;
	}

	public void setStack(Throwable stack) {
		this.stack = stack;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.code) + " " + this.message;
	}
	
}
