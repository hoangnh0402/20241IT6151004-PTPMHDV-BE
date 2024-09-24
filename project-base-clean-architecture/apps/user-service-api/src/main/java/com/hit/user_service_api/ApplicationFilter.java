package com.hit.user_service_api;

import com.hit.user_service_api.adapter.filter.AuthorizationFilter;
import com.hit.user_service_api.adapter.filter.ResourcePermissionFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationFilter {
  private final int ORDER_APPLICATION_AUTHORIZATION = 1;
  private final int ORDER_APPLICATION_RESOURCE_PERMISSION = 2;

  private final AuthorizationFilter authorizationFilter;
  private final ResourcePermissionFilter resourcePermissionFilter;

  public ApplicationFilter(AuthorizationFilter authorizationFilter,
                           ResourcePermissionFilter resourcePermissionFilter) {
    this.authorizationFilter = authorizationFilter;
    this.resourcePermissionFilter = resourcePermissionFilter;
  }

  @Bean
  public FilterRegistrationBean<AuthorizationFilter> applicationAuthorizationFilterConfig() {
    FilterRegistrationBean<AuthorizationFilter> bean =
        new FilterRegistrationBean<>(authorizationFilter);
    bean.setOrder(ORDER_APPLICATION_AUTHORIZATION);
    return bean;
  }

  @Bean
  public FilterRegistrationBean<ResourcePermissionFilter> applicationResourceFilterConfig() {
    FilterRegistrationBean<ResourcePermissionFilter> bean =
        new FilterRegistrationBean<>(resourcePermissionFilter);
    bean.setOrder(ORDER_APPLICATION_RESOURCE_PERMISSION);
    return bean;
  }

}
