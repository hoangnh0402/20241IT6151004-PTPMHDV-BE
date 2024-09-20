package com.hit.base_service_api.domain.object.user;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class UserId {

  @JsonValue
  private String value;

  public UserId() {
    this.value = String.valueOf(UUID.randomUUID());
  }

}
