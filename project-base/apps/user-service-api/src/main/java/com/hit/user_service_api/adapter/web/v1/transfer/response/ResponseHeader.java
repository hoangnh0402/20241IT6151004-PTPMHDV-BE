package com.hit.user_service_api.adapter.web.v1.transfer.response;

import org.springframework.http.HttpHeaders;

public interface ResponseHeader {

  HttpHeaders getHeader();

  HttpHeaders postHeader();

  HttpHeaders putHeader();

  HttpHeaders deleteHeader();

  HttpHeaders fullHeader();

}
