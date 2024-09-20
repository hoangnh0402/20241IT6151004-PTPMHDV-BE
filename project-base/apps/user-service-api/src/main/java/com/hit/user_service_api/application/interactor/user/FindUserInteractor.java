package com.hit.user_service_api.application.interactor.user;

import com.hit.base_service_api.domain.entity.user.User;
import com.hit.base_service_api.domain.object.IsActive;
import com.hit.base_service_api.exception.NotFoundException;
import com.hit.user_service_api.application.dai.user.UserRepository;
import com.hit.user_service_api.application.input.user.FindUserInput;
import com.hit.user_service_api.application.input_boundary.user.FindUserCase;
import com.hit.user_service_api.application.output.user.FindUserOutput;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("ApplicationFindUserInteractor")
public class FindUserInteractor implements FindUserCase {
  private final UserRepository userRepository;

  public FindUserInteractor(@Qualifier("DatabaseUserRepository") UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public FindUserOutput handle(FindUserInput input) throws Exception {
    // Build condition for find
    User userCondition = new User();
    userCondition.setId(input.getUserId());
    userCondition.setIsActive(IsActive.ACTIVE);

    // Find user
    User user = this.userRepository.find(userCondition, input.getTracking());

    // Check user exist
    if (ObjectUtils.isEmpty(user)) {
      throw new NotFoundException("User not found with id = " + input.getUserId().getValue(), this.getClass());
    }

    // Build output
    FindUserOutput output = new FindUserOutput();
    output.setItem(user);

    // Return output
    return output;
  }
}
