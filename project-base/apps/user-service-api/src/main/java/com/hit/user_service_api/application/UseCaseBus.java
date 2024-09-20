package com.hit.user_service_api.application;

import com.hit.user_service_api.application.input.Input;
import com.hit.user_service_api.application.input.user.*;
import com.hit.user_service_api.application.input_boundary.UseCase;
import com.hit.user_service_api.application.interactor.user.*;
import com.hit.user_service_api.application.output.Output;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component("ApplicationUseCaseBus")
public class UseCaseBus {
  private final HashMap<Class<? extends Input>, Class<? extends UseCase<? extends Input, ? extends Output>>>
      handlerTypes = new HashMap<>();
  private final HashMap<Class<? extends Input>, UseCaseInvoker> invokers = new HashMap<>();
  private final ApplicationContext applicationContext;

  @Autowired
  public UseCaseBus(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;

    // User
    handlerTypes.put(FindUserInput.class, FindUserInteractor.class);
    handlerTypes.put(SearchUsersInput.class, SearchUsersInteractor.class);
    handlerTypes.put(CreateUserInput.class, CreateUserInteractor.class);
    handlerTypes.put(UpdateUserInput.class, UpdateUserInteractor.class);
    handlerTypes.put(DeleteUserInput.class, DeleteUserInteractor.class);

    // Post

  }

  public <TInput extends Input, TOutput extends Output> TOutput handle(TInput input)
      throws Exception {
    UseCaseInvoker useCaseInvoker = this.getInvokers(input);
    return useCaseInvoker.invoke(input);
  }

  private <TInput extends Input> UseCaseInvoker getInvokers(TInput input) {
    if (invokers.containsKey(input.getClass()))
      return invokers.get(input.getClass());

    if (!handlerTypes.containsKey(input.getClass()))
      throw new RuntimeException(
          String.format("[%s]. not registerd use case interactor.", input.getClass()));

    UseCaseInvoker useCaseInvoker =
        new UseCaseInvoker(applicationContext, handlerTypes.get(input.getClass()));
    invokers.put(input.getClass(), useCaseInvoker);
    return useCaseInvoker;
  }
}
