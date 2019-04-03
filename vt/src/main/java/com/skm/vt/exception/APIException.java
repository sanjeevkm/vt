package com.skm.vt.exception;

public class APIException extends Exception {
	private static final long serialVersionUID = 13216733298798717L;
	private ErrorInfo errorInfo;

	public APIException(ErrorInfo errorInfo) {
		super();
		this.errorInfo = errorInfo;
	}

	public APIException(String message, ErrorInfo errorInfo) {
		super(message);
		this.errorInfo = errorInfo;
	}

	public APIException(String message, Throwable error, ErrorInfo errorInfo) {
		super(message, error);
		this.errorInfo = errorInfo;
	}

	public APIException(Throwable e, ErrorInfo errorInfo) {
		super(e);
		this.errorInfo = errorInfo;
	}

	public ErrorInfo getErrorInfo() {
		return errorInfo;
	}
}