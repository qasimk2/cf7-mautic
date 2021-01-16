package com.khawaja.sboot.courseapidata.exception;

public class ApplicationException extends RuntimeException {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  public ApplicationException(String message, Throwable exception) {
    super(message, exception);
  }

  public ApplicationException(String message) {
    super(message);
  }
}
