package com.hit.base_service_api.exception;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
public class ErrorResponseException extends Exception {
  @Serial
  private static final long serialVersionUID = 7382006724746243062L;
  private ErrorMessages errorMessages;
  private Class<?> calledClass;

  public ErrorResponseException(String logMessage, Class<?> calledClass) {
    super(logMessage);
    this.calledClass = calledClass;
  }

  public ErrorResponseException(String logMessage, ErrorMessages errorMessages,
      Class<?> calledClass) {
    super(logMessage);
    this.errorMessages = errorMessages;
    this.calledClass = calledClass;
  }

  public ErrorResponseException(String logMessage, ErrorMessages errorMessages, Throwable e,
      Class<?> calledClass) {
    super(logMessage, e);
    this.errorMessages = errorMessages;
    this.calledClass = calledClass;
  }

}
