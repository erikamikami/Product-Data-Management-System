package com.example.customException;

import org.springframework.security.core.AuthenticationException;

public class UserNotFoundException extends AuthenticationException{

	/**
	 * 指定されたメッセージを使用して UserNotFoundException を構築します。
	 * @param msg the detail message.
	 */
	public UserNotFoundException(String msg) {
		super(msg);
	}

	/**
	 * 	指定されたメッセージと根本原因を持つ UserNotFoundExceptionを構築します。
	 * cause.
	 * @param msg the detail message.
	 * @param cause root cause
	 */
	public UserNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
