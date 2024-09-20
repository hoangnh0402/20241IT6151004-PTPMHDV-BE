package com.hit.user_service_api.application.input_boundary;

import com.hit.user_service_api.application.input.Input;
import com.hit.user_service_api.application.output.Output;

public interface UseCase<TInput extends Input, TOutput extends Output> {
  TOutput handle(TInput input) throws Exception;
}
