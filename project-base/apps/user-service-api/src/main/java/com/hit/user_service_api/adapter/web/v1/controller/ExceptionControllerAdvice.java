package com.hit.user_service_api.adapter.web.v1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hit.base_service_api.domain.entity.Tracking;
import com.hit.base_service_api.exception.*;
import com.hit.user_service_api.adapter.web.base.VsResponseUtil;
import com.hit.user_service_api.adapter.web.v1.transfer.response.ResponseHeader;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;

@RestControllerAdvice
public class ExceptionControllerAdvice {
  private final ObjectMapper mapper = new ObjectMapper();
  private final ResponseHeader responseHeader;

  @Value("${enable.dev.error.messages:false}")
  private Boolean enableDevMessage;

  @Value("${server.request.attribute.name.tracking}")
  private String trackingRequestAttributeName;

  public ExceptionControllerAdvice(ResponseHeader responseHeader) {
    this.responseHeader = responseHeader;
  }

  private HttpHeaders findResponseHeader(HttpServletRequest request) {
    String method = request.getMethod();

    if ("GET".equals(method)) {
      return this.responseHeader.getHeader();
    }

    if ("POST".equals(method)) {
      return this.responseHeader.postHeader();
    }

    if ("PUT".equals(method)) {
      return this.responseHeader.putHeader();
    }

    if ("DELETE".equals(method)) {
      return this.responseHeader.deleteHeader();
    }

    return this.responseHeader.fullHeader();
  }

  private List<ErrorMessage> convertErrorMessage(ErrorMessages errorResponse) {
    if (this.enableDevMessage) {
      return errorResponse.getErrors();
    }

    this.mapper.addMixIn(ErrorMessage.class, ErrorMessageIgnore.class);

    return errorResponse.getErrors();
  }

  private void logWarn(HttpServletRequest request, Throwable exception)
      throws ClassNotFoundException {
    String method = request.getMethod();
    String uri = request.getRequestURI();

    Tracking tracking = (Tracking) request.getAttribute(this.trackingRequestAttributeName);
    String trackingIdAsString =
        (!Objects.isNull(tracking) && !Objects.isNull(tracking.getTrackingId()) && !Objects.isNull(
            tracking.getTrackingId().getValue())) ? tracking.getTrackingId().getValue() : "";

    if (exception instanceof ErrorResponseException e) {
      Logger logger = LoggerFactory.getLogger(e.getCalledClass());
      logger.warn("{}\t{}\t{}", trackingIdAsString, method, uri);
      return;
    }

    String className = Thread.currentThread().getStackTrace()[2].getClassName();
    Class<?> forName = Class.forName(className);
    Logger logger = LoggerFactory.getLogger(forName);
    logger.warn("{}\t{}\t{}", trackingIdAsString, method, uri);
  }

  private void logError(HttpServletRequest request, Throwable exception)
      throws ClassNotFoundException {
    String method = request.getMethod();
    String uri = request.getRequestURI();

    Tracking tracking = (Tracking) request.getAttribute(this.trackingRequestAttributeName);
    String trackingIdAsString =
        (!Objects.isNull(tracking) && !Objects.isNull(tracking.getTrackingId()) && !Objects.isNull(
            tracking.getTrackingId().getValue())) ? tracking.getTrackingId().getValue() : "";

    if (exception instanceof ErrorResponseException e) {
      Logger logger = LoggerFactory.getLogger(e.getCalledClass());
      logger.error("{}\t{}\t{}\t{}", trackingIdAsString, method, uri, exception.getMessage(),
          exception);
      return;
    }

    String className = Thread.currentThread().getStackTrace()[2].getClassName();
    Class<?> forName = Class.forName(className);
    Logger logger = LoggerFactory.getLogger(forName);
    logger.error("{}\t{}\t{}\t{}", trackingIdAsString, method, uri, exception.getMessage(),
        exception);
  }

  @ExceptionHandler(value = UnauthorizedException.class)
  public ResponseEntity<?> unauthorizedHandle(UnauthorizedException exception,
                                              HttpServletRequest request) throws ClassNotFoundException {
    logWarn(request, exception);

    int statusCode = HttpStatus.UNAUTHORIZED.value();

    ErrorMessage errorMessage =
        new ErrorMessage(statusCode, "401", exception.getMessage());

    ErrorMessages errorMessages = new ErrorMessages();
    errorMessages.add(errorMessage);

    return VsResponseUtil.error(this.findResponseHeader(request), HttpStatus.UNAUTHORIZED,
        convertErrorMessage(errorMessages));
  }


  @ExceptionHandler(value = BadRequestException.class)
  public ResponseEntity<?> badRequestHandle(BadRequestException exception,
                                            HttpServletRequest request) throws ClassNotFoundException {

    logWarn(request, exception);

    HttpStatus statusCode = HttpStatus.BAD_REQUEST;

    ErrorMessage errorMessage = new ErrorMessage(statusCode.value(), "400", exception.getMessage());

    ErrorMessages errorMessages = new ErrorMessages();
    errorMessages.add(errorMessage);

    return VsResponseUtil.error(this.findResponseHeader(request), statusCode, convertErrorMessage(errorMessages));
  }

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public ResponseEntity<?> badRequestHandle(MethodArgumentNotValidException exception,
                                            HttpServletRequest request) throws ClassNotFoundException {
    logWarn(request, exception);

    HttpStatus statusCode = HttpStatus.BAD_REQUEST;

    ErrorMessages errorMessages = new ErrorMessages();

    List<ObjectError> errors = exception.getBindingResult().getAllErrors();
    for (ObjectError error : errors) {
      ErrorMessage errorMessage = new ErrorMessage(statusCode.value(), "400", error.getDefaultMessage());
      errorMessages.add(errorMessage);
    }

    return VsResponseUtil.error(this.findResponseHeader(request), statusCode, convertErrorMessage(errorMessages));
  }

  @ExceptionHandler(value = ForbiddenException.class)
  public ResponseEntity<?> forbiddenHandle(ForbiddenException exception,
                                           HttpServletRequest request) throws ClassNotFoundException {
    logWarn(request, exception);

    HttpStatus statusCode = HttpStatus.FORBIDDEN;

    ErrorMessage errorMessage = new ErrorMessage(statusCode.value(), "403", exception.getMessage());

    ErrorMessages errorMessages = new ErrorMessages();
    errorMessages.add(errorMessage);

    return VsResponseUtil.error(this.findResponseHeader(request), statusCode, convertErrorMessage(errorMessages));
  }

  @ExceptionHandler(value = NoResourceFoundException.class)
  public ResponseEntity<?> notFoundResourceHandle(NoResourceFoundException exception,
                                                  HttpServletRequest request) throws ClassNotFoundException {
    logWarn(request, exception);

    HttpStatus statusCode = HttpStatus.NOT_FOUND;

    ErrorMessage errorMessage = new ErrorMessage(statusCode.value(), "404", exception.getMessage());

    ErrorMessages errorMessages = new ErrorMessages();
    errorMessages.add(errorMessage);

    return VsResponseUtil.error(this.findResponseHeader(request), statusCode, convertErrorMessage(errorMessages));
  }

  @ExceptionHandler(value = NotFoundException.class)
  public ResponseEntity<?> notFoundHandle(NotFoundException exception,
                                          HttpServletRequest request) throws ClassNotFoundException {
    logWarn(request, exception);

    HttpStatus statusCode = HttpStatus.NOT_FOUND;

    ErrorMessage errorMessage = new ErrorMessage(statusCode.value(), "404", exception.getMessage());

    ErrorMessages errorMessages = new ErrorMessages();
    errorMessages.add(errorMessage);

    return VsResponseUtil.error(this.findResponseHeader(request), statusCode, convertErrorMessage(errorMessages));
  }

  @ExceptionHandler(value = ConflictException.class)
  public ResponseEntity<?> conflictErrorHandle(ConflictException exception,
                                               HttpServletRequest request) throws ClassNotFoundException {
    logError(request, exception);

    HttpStatus statusCode = HttpStatus.CONFLICT;

    ErrorMessage errorMessage = new ErrorMessage(statusCode.value(), "409", exception.getMessage());

    ErrorMessages errorMessages = new ErrorMessages();
    errorMessages.add(errorMessage);

    return VsResponseUtil.error(this.findResponseHeader(request), statusCode, convertErrorMessage(errorMessages));
  }

  @ExceptionHandler(value = InternalServerErrorException.class)
  public ResponseEntity<?> internalServerErrorHandle(InternalServerErrorException exception,
                                                     HttpServletRequest request) throws ClassNotFoundException {
    logError(request, exception);

    HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;

    ErrorMessage errorMessage = new ErrorMessage(statusCode.value(), "500", exception.getMessage());

    ErrorMessages errorMessages = new ErrorMessages();
    errorMessages.add(errorMessage);

    return VsResponseUtil.error(this.findResponseHeader(request), statusCode, convertErrorMessage(errorMessages));
  }

  @ExceptionHandler(value = Throwable.class)
  public ResponseEntity<?> throwableHandle(Throwable exception, HttpServletRequest request)
      throws ClassNotFoundException {
    logError(request, exception);

    HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;

    ErrorMessage errorMessage;
    if (exception instanceof InvocationTargetException) {
      String devMessage = ((InvocationTargetException) exception).getTargetException().getMessage();
      if (StringUtils.isEmpty(devMessage)) {
        devMessage = exception.getCause().getCause().getMessage();
      }
      errorMessage = new ErrorMessage(statusCode.value(), "1000", devMessage);
    } else {
      errorMessage = new ErrorMessage(statusCode.value(), "1000", exception.getMessage());
    }

    ErrorMessages errorMessages = new ErrorMessages();
    errorMessages.add(errorMessage);

    return VsResponseUtil.error(this.findResponseHeader(request), statusCode, convertErrorMessage(errorMessages));
  }
}
