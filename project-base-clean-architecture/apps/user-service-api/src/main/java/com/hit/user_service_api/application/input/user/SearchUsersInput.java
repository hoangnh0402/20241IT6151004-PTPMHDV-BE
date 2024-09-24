package com.hit.user_service_api.application.input.user;

import com.hit.base_service_api.domain.entity.Tracking;
import com.hit.base_service_api.domain.object.Keyword;
import com.hit.base_service_api.domain.object.pagination.PageNum;
import com.hit.base_service_api.domain.object.pagination.PageSize;
import com.hit.base_service_api.domain.object.pagination.SortBy;
import com.hit.base_service_api.domain.object.pagination.SortType;
import com.hit.base_service_api.domain.object.user.UserId;
import com.hit.user_service_api.application.input.Input;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchUsersInput implements Input {

  private List<UserId> ids;

  private Keyword keyword;

  private PageNum pageNum;

  private PageSize pageSize;

  private SortBy sortBy;

  private SortType sortType;

  private Tracking tracking;

}
