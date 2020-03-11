package com.nexio.exception;

public class BusinessException extends RuntimeException {
	   private static final long serialVersionUID = 1L;
	   
	   public BusinessException(String exception) {
		    super(exception);
		}
}