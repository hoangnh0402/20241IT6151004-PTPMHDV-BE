package com.hit.user_service_api.adapter.web.base;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class VsResponseUtil {

  public static ResponseEntity<RestData<?>> ok(HttpHeaders headers) {
    return ok(headers, HttpStatus.OK, null);
  }

  public static ResponseEntity<RestData<?>> ok(HttpHeaders headers, Object data) {
    return ok(headers, HttpStatus.OK, data);
  }

  public static ResponseEntity<RestData<?>> ok(HttpHeaders headers, HttpStatus status, Object data) {
    RestData<?> response = new RestData<>(data);
    return new ResponseEntity<>(response, headers, status);
  }

  public static ResponseEntity<RestData<?>> error(HttpHeaders headers, HttpStatus status, Object message) {
    RestData<?> response = RestData.error(message);
    return new ResponseEntity<>(response, headers, status);
  }

  public static ResponseEntity<RestData<?>> error(HttpStatus status, Object message) {
    RestData<?> response = RestData.error(message);
    return new ResponseEntity<>(response, status);
  }

}
