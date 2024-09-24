package com.hit.user_service_api.adapter.web.v1.transfer.response;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component("WebV1TransferResponseHeader")
public class Header implements ResponseHeader {
  /**
   * HTTP Header / GET (+OPTIONS) Method
   *
   * @return HttpHeaders
   */
  @Override
  public HttpHeaders getHeader() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Access-Control-Allow-Origin", "*");
    headers.add("Access-Control-Allow-Methods", "GET,OPTIONS");
    headers.add("Access-Control-Allow-Headers",
        "X-From, Authorization, Content-Type, X-Authorization");
    headers.add("Expires", "Mon, 26 Jul 1997 05:00:00 GMT");
    headers.add("Cache-Control", "no-cache=\"set-cookie\"");
    headers.add("Content-type", "application/json; charset=utf-8");
    headers.add("X-Content-Type-Options", "nosniff");
    headers.add("X-Robots-Tag", "noindex, nofollow");
    return headers;
  }

  /**
   * HTTP Header / POST (+OPTIONS) Method
   *
   * @return HttpHeaders
   */
  @Override
  public HttpHeaders postHeader() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Access-Control-Allow-Origin", "*");
    headers.add("Access-Control-Allow-Methods", "POST,OPTIONS");
    headers.add("Access-Control-Allow-Headers",
        "X-From, Authorization, Content-Type, X-Authorization");
    headers.add("Expires", "Mon, 26 Jul 1997 05:00:00 GMT");
    headers.add("Cache-Control", "no-cache=\"set-cookie\"");
    headers.add("Content-type", "application/json; charset=utf-8");
    headers.add("X-Content-Type-Options", "nosniff");
    headers.add("X-Robots-Tag", "noindex, nofollow");
    return headers;
  }

  /**
   * HTTP Header / PUT (+OPTIONS) Method
   *
   * @return HttpHeaders
   */
  @Override
  public HttpHeaders putHeader() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Access-Control-Allow-Origin", "*");
    headers.add("Access-Control-Allow-Methods", "PUT,OPTIONS");
    headers.add("Access-Control-Allow-Headers",
        "X-From, Authorization, Content-Type, X-Authorization");
    headers.add("Expires", "Mon, 26 Jul 1997 05:00:00 GMT");
    headers.add("Cache-Control", "no-cache=\"set-cookie\"");
    headers.add("Content-type", "application/json; charset=utf-8");
    headers.add("X-Content-Type-Options", "nosniff");
    headers.add("X-Robots-Tag", "noindex, nofollow");
    return headers;
  }

  /**
   * HTTP Header / DELETE (+OPTIONS) Method
   *
   * @return HttpHeaders
   */
  @Override
  public HttpHeaders deleteHeader() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Access-Control-Allow-Origin", "*");
    headers.add("Access-Control-Allow-Methods", "DELEET,OPTIONS");
    headers.add("Access-Control-Allow-Headers",
        "X-From, Authorization, Content-Type, X-Authorization");
    headers.add("Expires", "Mon, 26 Jul 1997 05:00:00 GMT");
    headers.add("Cache-Control", "no-cache=\"set-cookie\"");
    headers.add("Content-type", "application/json; charset=utf-8");
    headers.add("X-Content-Type-Options", "nosniff");
    headers.add("X-Robots-Tag", "noindex, nofollow");
    return headers;
  }

  /**
   * HTTP Header / GET,POST,PUT,DELETE (+OPTIONS) Method
   *
   * @return HttpHeaders
   */
  @Override
  public HttpHeaders fullHeader() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Access-Control-Allow-Origin", "*");
    headers.add("Access-Control-Allow-Methods", "GET,POST,PUT,DELEET,OPTIONS");
    headers.add("Access-Control-Allow-Headers",
        "X-From, Authorization, Content-Type, X-Authorization");
    headers.add("Expires", "Mon, 26 Jul 1997 05:00:00 GMT");
    headers.add("Cache-Control", "no-cache=\"set-cookie\"");
    headers.add("Content-type", "application/json; charset=utf-8");
    headers.add("X-Content-Type-Options", "nosniff");
    headers.add("X-Robots-Tag", "noindex, nofollow");
    return headers;
  }
}
