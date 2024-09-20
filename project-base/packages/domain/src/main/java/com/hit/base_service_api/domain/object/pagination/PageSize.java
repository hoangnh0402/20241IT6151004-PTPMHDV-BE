package com.hit.base_service_api.domain.object.pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageSize {

  private Long value = 0L;

  public Long getValue() {
    if (value <= 0) {
      value = 50L;
    }
    return value;
  }

}
