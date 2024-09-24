package com.hit.base_service_api.exception;

import java.io.Serial;

public class ForbiddenException extends ErrorResponseException {
  @Serial
  private static final long serialVersionUID = 1168092696295913261L;

  public ForbiddenException(String logMessage, Class<?> calledClass) {
    super(logMessage, calledClass);
  }
}
