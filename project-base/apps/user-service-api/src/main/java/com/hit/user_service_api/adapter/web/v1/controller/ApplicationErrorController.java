package com.hit.user_service_api.adapter.web.v1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hit.base_service_api.exception.ErrorMessage;
import com.hit.base_service_api.exception.ErrorMessageIgnore;
import com.hit.base_service_api.exception.ErrorMessages;
import com.hit.user_service_api.adapter.web.v1.transfer.response.ResponseHeader;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("AdapterV3ApplicationErrorController")
@RequestMapping("/error")
public class ApplicationErrorController implements ErrorController {
  private final ObjectMapper mapper = new ObjectMapper();
  private final ResponseHeader responseHeader;

  @Value("${enable.dev.error.messages:false}")
  private Boolean enableDevMessage;

  @Autowired
  public ApplicationErrorController(ResponseHeader responseHeader) {
    this.responseHeader = responseHeader;
  }

  public String getErrorPath() {
    return "/error";
  }

  private String convertErrorMessage(ErrorMessages errorResponse) {
    if (this.enableDevMessage) {
      return this.mapper.valueToTree(errorResponse).toString();
    }

    this.mapper.addMixIn(ErrorMessage.class, ErrorMessageIgnore.class);

    return this.mapper.valueToTree(errorResponse).toString();
  }

  @RequestMapping(produces = "application/json")
  public ResponseEntity<?> get(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ErrorMessage errorMessage;

    if (response.getStatus() == 400) {
      errorMessage = new ErrorMessage(400, "400", "There is an error in the request.");
    } else if (response.getStatus() == 401) {
      errorMessage = new ErrorMessage(401, "401", "Authentication failed.");
    } else if (response.getStatus() == 403) {
      errorMessage = new ErrorMessage(403, "403", "Invalid access.");
    } else if (response.getStatus() == 404) {
      errorMessage = new ErrorMessage(404, "404", "Access to a non-existent resource.");
    } else if (response.getStatus() == 409) {
      errorMessage = new ErrorMessage(409, "409", "Registration failed.");
    } else {
      errorMessage = new ErrorMessage(response.getStatus(), String.valueOf(response.getStatus()), "An error occurred.");
    }

    ErrorMessages errorMessages = new ErrorMessages();
    errorMessages.add(errorMessage);

    return ResponseEntity.status(response.getStatus()).headers(this.responseHeader.fullHeader())
        .body(convertErrorMessage(errorMessages));
  }
}
