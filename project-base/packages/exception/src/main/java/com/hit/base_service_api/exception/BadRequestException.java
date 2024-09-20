package com.hit.base_service_api.exception;

import java.io.Serial;

public class BadRequestException extends ErrorResponseException {
  @Serial
  private static final long serialVersionUID = 1168092696295913261L;

  public BadRequestException(String logMessage, Class<?> calledClass) {
    super(logMessage, calledClass);
  }

  public BadRequestException(String logMessage, ErrorMessages errorMessages, Class<?> calledClass) {
    super(logMessage, errorMessages, calledClass);
  }
}
