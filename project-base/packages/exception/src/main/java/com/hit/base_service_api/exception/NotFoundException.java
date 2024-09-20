package com.hit.base_service_api.exception;

import java.io.Serial;

public class NotFoundException extends ErrorResponseException {
  @Serial
  private static final long serialVersionUID = 1168092696295913261L;

  public NotFoundException(String logMessage, Class<?> calledClass) {
    super(logMessage, calledClass);
  }
}
