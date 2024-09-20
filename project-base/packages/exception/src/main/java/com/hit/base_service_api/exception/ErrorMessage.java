package com.hit.base_service_api.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.ObjectError;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorMessage {

  @JsonProperty("status")
  @JsonSetter("status")
  private Integer status = 500;

  @JsonProperty("userMessage")
  @JsonSetter("userMessage")
  private String userMessage = "";

  @JsonProperty("devMessage")
  @JsonSetter("devMessage")
  private String devMessage = "";

  public ErrorMessage(ObjectError error) {
    this.status = 400;
    this.userMessage = error.getDefaultMessage();
    this.devMessage = error.getDefaultMessage();
  }

}
