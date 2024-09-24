package com.hit.user_service_api.application.input_boundary.user;

import com.hit.user_service_api.application.input.user.SearchUsersInput;
import com.hit.user_service_api.application.input_boundary.UseCase;
import com.hit.user_service_api.application.output.user.SearchUsersOutput;

public interface SearchUsersCase extends UseCase<SearchUsersInput, SearchUsersOutput> {
}
