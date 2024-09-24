package com.hit.base_service_api.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.hit.base_service_api.domain.object.pagination.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Pagination {

  @JsonSetter("pageNum")
  @JsonProperty("pageNum")
  private PageNum pageNum;

  @JsonSetter("pageSize")
  @JsonProperty("pageSize")
  private PageSize pageSize;

  @JsonSetter("sortType")
  @JsonProperty("sortType")
  private SortType sortType;

  @JsonSetter("sortBy")
  @JsonProperty("sortBy")
  private SortBy sortBy;

  @JsonSetter("limit")
  @JsonProperty("limit")
  private Limit limit;

  @JsonSetter("offset")
  @JsonProperty("offset")
  private Offset offset;

  public Long getLimit() {
    return pageSize.getValue();
  }

  public Long getOffset() {
    return (pageNum.getValue() - 1) * pageSize.getValue();
  }

  public boolean hasSort() {
    return ObjectUtils.isNotEmpty(sortBy)
        && StringUtils.isNotEmpty(sortBy.getValue())
        && ObjectUtils.isNotEmpty(sortType)
        && StringUtils.isNotEmpty(sortType.getValue());
  }

  public boolean hasRange() {
    return ObjectUtils.isNotEmpty(pageNum)
        && ObjectUtils.isNotEmpty(pageNum.getValue())
        && ObjectUtils.isNotEmpty(pageSize)
        && ObjectUtils.isNotEmpty(pageSize.getValue());
  }

}
