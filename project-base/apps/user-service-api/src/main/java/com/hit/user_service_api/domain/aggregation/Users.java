package com.hit.user_service_api.domain.aggregation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.hit.base_service_api.domain.entity.user.User;
import com.hit.base_service_api.domain.object.IsActive;
import com.hit.base_service_api.domain.object.Keyword;
import com.hit.base_service_api.domain.object.user.UserId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Users {
  // ----------------------------------
  // Fields for search sql
  // ----------------------------------
  private List<UserId> ids;

  private IsActive isActive;

  private Keyword keyword;

  // ----------------------------------
  // Fields for response
  // ----------------------------------
  @JsonSetter("items")
  @JsonProperty("items")
  private List<User> items = new ArrayList<>();

  @JsonSetter("total")
  @JsonProperty("total")
  private Long total = 0L;

  public Users(List<User> items, Long total) {
    this.items = items;
    this.total = total;
  }

}
