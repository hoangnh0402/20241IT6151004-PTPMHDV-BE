package com.hit.user_service_api.application.input_boundary.user;

import com.hit.user_service_api.application.input.user.FindUserInput;
import com.hit.user_service_api.application.input_boundary.UseCase;
import com.hit.user_service_api.application.output.user.FindUserOutput;

public interface FindUserCase extends UseCase<FindUserInput, FindUserOutput> {
}
