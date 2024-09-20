package com.hit.base_service_api.exception;

import java.io.Serial;

public class ConflictException extends ErrorResponseException {
  @Serial
  private static final long serialVersionUID = 1168092696295913121L;

  public ConflictException(String logMessage, Class<?> calledClass) {
    super(logMessage, calledClass);
  }

  public ConflictException(String logMessage, ErrorMessages errorMessages, Class<?> calledClass) {
    super(logMessage, errorMessages, calledClass);
  }
}
