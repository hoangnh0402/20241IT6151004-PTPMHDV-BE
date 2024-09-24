package com.hit.user_service_api.adapter.web.v1.transfer.response.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResponse {

  @JsonProperty("id")
  @JsonSetter("id")
  private String id;

  @JsonProperty("fullName")
  @JsonSetter("fullName")
  private String fullName;

  @JsonProperty("username")
  @JsonSetter("username")
  private String username;

  @JsonProperty("email")
  @JsonSetter("email")
  private String email;

  @JsonProperty("address")
  @JsonSetter("address")
  private String address;

  @JsonProperty("isActive")
  @JsonSetter("isActive")
  private Integer isActive;

}
