package com.skm.vt.exception;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.HttpStatus;

@SuppressWarnings("unused")
public class ErrorInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private HttpStatus status;
	private final int code;
	private final String message;
	private final String developerMessage;
	private final String moreInfoUrl;
	private final Throwable throwable;
	private final List<String> errors;

	private ErrorInfo() {
		this.status = null;
		this.code = -1;
		this.message = null;
		this.developerMessage = null;
		this.moreInfoUrl = null;
		this.throwable = null;
		this.errors = null;
	}

	private ErrorInfo(HttpStatus status, int code, String message, String developerMessage, String moreInfoUrl,
			Throwable throwable, List<String> errors) {
		if (status == null) {
			throw new NullPointerException("HttpStatus argument cannot be null.");
		}
		this.status = status;
		this.code = code;
		this.message = message;
		this.developerMessage = developerMessage;
		this.moreInfoUrl = moreInfoUrl;
		this.throwable = throwable;
		this.errors = errors;
	}

	// Builder pattern to create the object
	public static class Builder {
		private HttpStatus status;
		private int code;
		private String message;
		private String developerMessage;
		private String moreInfoUrl;
		private Throwable throwable;
		private List<String> errors;

		public Builder() {
		}

		public Builder setStatus(int statusCode) {
			this.status = HttpStatus.valueOf(statusCode);
			return this;
		}

		public Builder setStatus(HttpStatus status) {
			this.status = status;
			return this;
		}

		public Builder setCode(int code) {
			this.code = code;
			return this;
		}

		public Builder setMessage(String message) {
			this.message = message;
			return this;
		}

		public Builder setDeveloperMessage(String developerMessage) {
			this.developerMessage = developerMessage;
			return this;
		}

		public Builder setMoreInfoUrl(String moreInfoUrl) {
			this.moreInfoUrl = moreInfoUrl;
			return this;
		}

		public Builder setThrowable(Throwable throwable) {
			this.throwable = throwable;
			return this;
		}

		public Builder setErrors(List<String> errors) {
			this.errors = errors;
			return this;
		}

		public ErrorInfo build() {
			if (this.status == null) {
				this.status = HttpStatus.INTERNAL_SERVER_ERROR;
			}

			return new ErrorInfo(this.status, this.code, this.message, this.developerMessage, this.moreInfoUrl,
					this.throwable, this.errors);
		}
	}
}
