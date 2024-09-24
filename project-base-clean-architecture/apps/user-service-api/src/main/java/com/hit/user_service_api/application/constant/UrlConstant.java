package com.hit.user_service_api.application.constant;

public class UrlConstant {
  private UrlConstant() {
  }

  public static final class User {
    private User() {
    }

    private static final String PREFIX = "/users";
    public static final String FIND = PREFIX + "/{id}";
    public static final String SEARCH = PREFIX;
    public static final String CREATE = PREFIX;
    public static final String UPDATE = PREFIX + "/{id}";
    public static final String DELETE = PREFIX + "/{id}";
  }
}
