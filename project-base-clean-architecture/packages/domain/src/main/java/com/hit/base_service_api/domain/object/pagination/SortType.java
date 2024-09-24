package com.hit.base_service_api.domain.object.pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SortType {

  private String value;

  public String getValue() {
    if (Objects.isNull(value)) {
      return "ASC";
    }

    return switch (value) {
      case "DESC", "desc" -> "DESC";
      default -> "ASC";
    };
  }

}
