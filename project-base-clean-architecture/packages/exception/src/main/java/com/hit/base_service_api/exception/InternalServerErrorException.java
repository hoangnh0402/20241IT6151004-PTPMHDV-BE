package com.hit.base_service_api.exception;

import java.io.Serial;

public class InternalServerErrorException extends ErrorResponseException {
  @Serial
  private static final long serialVersionUID = 561181926807682349L;

  public InternalServerErrorException(String logMessage, ErrorMessages errorMessages, Throwable e,
      Class<?> calledClass) {
    super(logMessage, errorMessages, e, calledClass);
  }
}
