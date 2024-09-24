package com.hit.user_service_api.application.input_boundary.user;

import com.hit.user_service_api.application.input.user.CreateUserInput;
import com.hit.user_service_api.application.input_boundary.UseCase;
import com.hit.user_service_api.application.output.user.CreateUserOutput;

public interface CreateUserCase extends UseCase<CreateUserInput, CreateUserOutput> {
}
