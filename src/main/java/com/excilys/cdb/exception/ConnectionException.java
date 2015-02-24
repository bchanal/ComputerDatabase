package com.excilys.cdb.exception;

public class ConnectionException extends RuntimeException {

	/**
	 * @author berangere
	 */
	private static final long serialVersionUID = 1L;

	public ConnectionException() {
	}

	public ConnectionException(String message) {
		super(message);
	}

	public ConnectionException(Throwable cause) {
		super(cause);
	}

	public ConnectionException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConnectionException(String message, Throwable cause,	boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
