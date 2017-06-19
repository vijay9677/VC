/**
 * 
 */
package com.vehiclecontroler.exception;

/**
 * @author Vijay Kumar Reddy K
 *
 */
public class MailException extends Exception {

	/**
	 * 
	 */
	public MailException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public MailException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public MailException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MailException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public MailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
