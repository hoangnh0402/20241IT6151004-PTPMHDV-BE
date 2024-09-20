package com.hit.user_service_api.application.interactor.user;

import com.hit.base_service_api.domain.entity.user.User;
import com.hit.base_service_api.domain.object.IsActive;
import com.hit.base_service_api.domain.object.user.UserId;
import com.hit.user_service_api.application.dai.user.UserRepository;
import com.hit.user_service_api.application.input.user.CreateUserInput;
import com.hit.user_service_api.application.input_boundary.user.CreateUserCase;
import com.hit.user_service_api.application.output.user.CreateUserOutput;
import org.springframework.stereotype.Service;

@Service("ApplicationCreateUserInteractor")
public class CreateUserInteractor implements CreateUserCase {
  private final UserRepository userRepository;

  public CreateUserInteractor(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public CreateUserOutput handle(CreateUserInput input) throws Exception {
    // Create user
    User user = new User();
    user.setId(new UserId());
    user.setUsername(input.getUsername());
    user.setFullName(input.getFullName());
    user.setEmail(input.getEmail());
    user.setAddress(input.getAddress());
    user.setIsActive(IsActive.ACTIVE);

    // Save user
    User result = userRepository.save(user, input.getTracking());

    // Create output
    CreateUserOutput output = new CreateUserOutput();
    output.setItem(result);

    // Return output
    return output;
  }
}
