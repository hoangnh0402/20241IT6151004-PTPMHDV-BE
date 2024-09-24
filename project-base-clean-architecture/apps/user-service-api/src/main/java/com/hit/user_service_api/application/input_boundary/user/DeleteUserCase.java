package com.hit.user_service_api.application.input_boundary.user;

import com.hit.user_service_api.application.input.user.DeleteUserInput;
import com.hit.user_service_api.application.input_boundary.UseCase;
import com.hit.user_service_api.application.output.user.DeleteUserOutput;

public interface DeleteUserCase extends UseCase<DeleteUserInput, DeleteUserOutput> {
}
