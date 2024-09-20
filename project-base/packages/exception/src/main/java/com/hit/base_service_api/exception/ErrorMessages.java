package com.hit.base_service_api.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessages {

  @JsonProperty("errors")
  @JsonSetter("errors")
  private List<ErrorMessage> errors;

  public ErrorMessages() {
    this.setErrors(new ArrayList<>());
  }

  public void add(ErrorMessage error) {
    this.getErrors().add(error);
  }
}
