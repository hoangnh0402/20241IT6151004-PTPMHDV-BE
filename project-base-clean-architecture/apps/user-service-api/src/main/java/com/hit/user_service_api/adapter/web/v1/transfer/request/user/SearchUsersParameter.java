package com.hit.user_service_api.adapter.web.v1.transfer.request.user;

import com.hit.base_service_api.domain.object.Keyword;
import com.hit.base_service_api.domain.object.user.UserId;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchUsersParameter {

  private List<String> ids;

  private String keyword;

  private Long pageNum = 0L;

  private Long pageSize = 100L;

  private String sortBy;

  @Pattern(regexp = "^(ASC|DESC|asc|desc)$")
  private String sortType = "ASC";

  public List<UserId> getIds() {
    if (ObjectUtils.isEmpty(ids)) {
      return new ArrayList<>();
    }

    return ids.stream()
        .filter(StringUtils::isNotEmpty)
        .map(UserId::new)
        .toList();
  }

  public Keyword getKeyword() {
    return new Keyword(keyword);
  }

}
