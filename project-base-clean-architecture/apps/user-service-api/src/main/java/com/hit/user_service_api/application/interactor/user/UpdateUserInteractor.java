package com.hit.user_service_api.application.interactor.user;

import com.hit.base_service_api.domain.entity.user.User;
import com.hit.base_service_api.domain.object.IsActive;
import com.hit.base_service_api.exception.NotFoundException;
import com.hit.user_service_api.application.dai.user.UserRepository;
import com.hit.user_service_api.application.input.user.UpdateUserInput;
import com.hit.user_service_api.application.input_boundary.user.UpdateUserCase;
import com.hit.user_service_api.application.output.user.UpdateUserOutput;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

@Service("ApplicationUpdateUserInteractor")
public class UpdateUserInteractor implements UpdateUserCase {
  private final UserRepository userRepository;

  public UpdateUserInteractor(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UpdateUserOutput handle(UpdateUserInput input) throws Exception {
    // Find user
    User userFoundCondition = new User();
    userFoundCondition.setId(input.getId());
    userFoundCondition.setIsActive(IsActive.ACTIVE);
    User user = userRepository.find(userFoundCondition, input.getTracking());

    if (ObjectUtils.isEmpty(user)) {
      throw new NotFoundException("User not found", this.getClass());
    }

    // Create user update
    user.setUsername(input.getUsername());
    user.setFullName(input.getFullName());
    user.setEmail(input.getEmail());
    user.setAddress(input.getAddress());
    user.setIsActive(input.getIsActive());

    // Update user
    userRepository.update(user, input.getTracking());

    // Create output
    UpdateUserOutput output = new UpdateUserOutput();
    output.setItem(user);

    // Return output
    return output;
  }
}
