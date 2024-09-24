package com.hit.user_service_api.adapter.web.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestData<T> {

  private Object status;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private T messages;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private T data;

  public RestData(T data) {
    this.status = RestStatus.SUCCESS;
    this.data = data;
  }

  public RestData(RestStatus status, T messages, T data) {
    this.status = status;
    this.messages = messages;
    this.data = data;
  }

  public RestData(Integer status, T messages) {
    this.status = status;
    this.messages = messages;
  }

  public static RestData<?> error(Object messages) {
    return new RestData<>(RestStatus.ERROR, messages, null);
  }

}
