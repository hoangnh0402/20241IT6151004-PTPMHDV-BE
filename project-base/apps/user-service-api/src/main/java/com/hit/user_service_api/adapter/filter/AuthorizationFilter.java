package com.hit.user_service_api.adapter.filter;

import com.hit.base_service_api.domain.entity.Tracking;
import com.hit.base_service_api.domain.object.tracking.TrackingId;
import com.hit.base_service_api.exception.ErrorMessage;
import com.hit.base_service_api.exception.ErrorMessages;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component("AdapterV3AuthorizationFilter")
public class AuthorizationFilter extends ApplicationFilterBase implements Filter {
  @Value("${environment}")
  private String environment;

  @Value("${server.header.name.tracking-id}")
  private String trackingIdHeaderName;

  @Value("${server.request.attribute.name.tracking}")
  private String trackingRequestAttributeName;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest httpRequest = (HttpServletRequest) request;

    String path = httpRequest.getServletPath();

    String trackingId = !Objects.isNull(httpRequest.getHeader(this.trackingIdHeaderName)) ?
        httpRequest.getHeader(this.trackingIdHeaderName) :
        new TrackingId().getValue();

    Tracking tracking = new Tracking();
    tracking.setTrackingId(new TrackingId(trackingId));
    request.setAttribute(this.trackingRequestAttributeName, tracking);

    ErrorMessages errorMessages = new ErrorMessages();
    Logger logger = LoggerFactory.getLogger(this.getClass());

    if (path.toLowerCase().contains("health") || path.toLowerCase().startsWith("/actuator/")) {
      chain.doFilter(request, response);
      return;
    }

    // TODO: Draft implementation for Authorization
    // Check if not contain Authorization header => return 401
    // Need extract token from Authorization header => expired time, validate token...
    String authorizationToken = httpRequest.getHeader("Authorization");

    if (Objects.isNull(authorizationToken) || authorizationToken.isBlank()) {
      ErrorMessage errorMessage =
          new ErrorMessage(HttpStatus.UNAUTHORIZED.value(), "401", "UnAuthorization!");
      errorMessages.add(errorMessage);
      setErrorResponse(HttpStatus.UNAUTHORIZED, response, errorMessages);
      logger.warn("{} \"{}\" {} {}", httpRequest.getMethod(), path, errorMessage.getStatus(),
          errorMessage.getUserMessage());
      return;
    }

    chain.doFilter(request, response);
  }
}
