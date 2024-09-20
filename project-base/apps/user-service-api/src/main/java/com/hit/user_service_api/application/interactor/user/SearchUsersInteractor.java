package com.hit.user_service_api.application.interactor.user;

import com.hit.base_service_api.domain.entity.Pagination;
import com.hit.base_service_api.domain.object.IsActive;
import com.hit.user_service_api.application.dai.user.UserRepository;
import com.hit.user_service_api.application.input.user.SearchUsersInput;
import com.hit.user_service_api.application.input_boundary.user.SearchUsersCase;
import com.hit.user_service_api.application.output.user.SearchUsersOutput;
import com.hit.user_service_api.domain.aggregation.Users;
import org.springframework.stereotype.Service;

@Service("ApplicationSearchUsersInteractor")
public class SearchUsersInteractor implements SearchUsersCase {
  private final UserRepository userRepository;

  public SearchUsersInteractor(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public SearchUsersOutput handle(SearchUsersInput input) throws Exception {
    // Build search criteria
    Users users = new Users();
    users.setIds(input.getIds());
    users.setKeyword(input.getKeyword());
    users.setIsActive(IsActive.ACTIVE);

    // Build pagination
    Pagination pagination = new Pagination();
    pagination.setPageNum(input.getPageNum());
    pagination.setPageSize(input.getPageSize());
    pagination.setSortBy(input.getSortBy());
    pagination.setSortType(input.getSortType());

    // Search users
    Users result = userRepository.search(users, pagination, input.getTracking());

    // Build output
    SearchUsersOutput output = new SearchUsersOutput();
    output.setItems(result.getItems());
    output.setTotal(result.getTotal());

    // Return output
    return output;
  }
}
