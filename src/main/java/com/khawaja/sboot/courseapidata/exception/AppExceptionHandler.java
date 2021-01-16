package com.khawaja.sboot.courseapidata.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

  // COMMENT need to do something
  @ExceptionHandler(value = { ApplicationException.class })
  public ResponseEntity<Object> handleAppException(ApplicationException ex, WebRequest request) {
    String errorMessageDescription = ex.getLocalizedMessage();
    if (errorMessageDescription == null)
      errorMessageDescription = ex.toString();

    return new ResponseEntity<>(errorMessageDescription.toString(), new HttpHeaders(),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
