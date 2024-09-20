package com.hit.user_service_api.adapter.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hit.base_service_api.exception.ErrorMessage;
import com.hit.base_service_api.exception.ErrorMessageIgnore;
import com.hit.base_service_api.exception.ErrorMessages;
import com.hit.user_service_api.adapter.web.base.RestData;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

abstract class ApplicationFilterBase {
  private final ObjectMapper mapper = new ObjectMapper();

  @Value("${enable.dev.error.messages:false}")
  private Boolean enableDevMessage;

  @Value("${castme.csrf.access.allow.origin}")
  private String allowOrigin;

  public void setErrorResponse(HttpStatus status, ServletResponse response,
      ErrorMessages errorMessages) throws IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("utf-8");

    HttpServletResponse servletResponse = (HttpServletResponse) response;
    servletResponse.setStatus(status.value());
    servletResponse.addHeader("Access-Control-Allow-Origin", this.allowOrigin);
    servletResponse.addHeader("Access-Control-Allow-Headers",
        "content-type, authorization, x-from, X-Authorization, X-Organization-Id");
    servletResponse.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");

    if (!this.enableDevMessage) {
      this.mapper.addMixIn(ErrorMessage.class, ErrorMessageIgnore.class);
    }

    Writer writer =
        new PrintWriter(new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8));

    RestData<?> restData = RestData.error(errorMessages);

    writer.write(this.mapper.writeValueAsString(restData));
    writer.flush();
  }

  public abstract void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException;
}
