package com.hit.user_service_api.adapter.filter;

import jakarta.servlet.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Log4j2
@Component("AdapterV3ResourcePermissionFilter")
public class ResourcePermissionFilter extends ApplicationFilterBase implements Filter {
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    log.info("ResourcePermissionFilter demo");

    chain.doFilter(request, response);
  }
}
