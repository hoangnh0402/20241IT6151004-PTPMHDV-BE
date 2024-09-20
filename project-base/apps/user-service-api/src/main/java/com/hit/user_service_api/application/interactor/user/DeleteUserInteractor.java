package com.hit.user_service_api.application.interactor.user;

import com.hit.base_service_api.domain.entity.user.User;
import com.hit.base_service_api.domain.object.IsActive;
import com.hit.base_service_api.exception.NotFoundException;
import com.hit.user_service_api.application.dai.user.UserRepository;
import com.hit.user_service_api.application.input.user.DeleteUserInput;
import com.hit.user_service_api.application.input_boundary.user.DeleteUserCase;
import com.hit.user_service_api.application.output.user.DeleteUserOutput;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

@Service("ApplicationDeleteUserInteractor")
public class DeleteUserInteractor implements DeleteUserCase {
  private final UserRepository userRepository;

  public DeleteUserInteractor(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public DeleteUserOutput handle(DeleteUserInput input) throws Exception {
    // Find user
    User userFoundCondition = new User();
    userFoundCondition.setId(input.getId());
    userFoundCondition.setIsActive(IsActive.ACTIVE);

    User user = userRepository.find(userFoundCondition, input.getTracking());

    if (ObjectUtils.isEmpty(user)) {
      throw new NotFoundException("User not found", this.getClass());
    }

    // Delete user
    userRepository.delete(user, input.getTracking());

    // Create output
    DeleteUserOutput output = new DeleteUserOutput();
    output.setItem(user);

    // Return output
    return output;
  }
}
