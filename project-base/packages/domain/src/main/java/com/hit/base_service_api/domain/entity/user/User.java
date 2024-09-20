package com.hit.base_service_api.domain.entity.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.hit.base_service_api.domain.object.IsActive;
import com.hit.base_service_api.domain.object.user.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @JsonProperty("id")
  @JsonSetter("id")
  private UserId id;

  @JsonProperty("fullName")
  @JsonSetter("fullName")
  private FullName fullName;

  @JsonProperty("username")
  @JsonSetter("username")
  private Username username;

  @JsonProperty("email")
  @JsonSetter("email")
  private Email email;

  @JsonProperty("address")
  @JsonSetter("address")
  private Address address;

  @JsonProperty("isActive")
  @JsonSetter("isActive")
  private IsActive isActive;

}
