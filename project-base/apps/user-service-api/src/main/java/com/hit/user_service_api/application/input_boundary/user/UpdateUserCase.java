package com.hit.user_service_api.application.input_boundary.user;

import com.hit.user_service_api.application.input.user.UpdateUserInput;
import com.hit.user_service_api.application.input_boundary.UseCase;
import com.hit.user_service_api.application.output.user.UpdateUserOutput;

public interface UpdateUserCase extends UseCase<UpdateUserInput, UpdateUserOutput> {
}
