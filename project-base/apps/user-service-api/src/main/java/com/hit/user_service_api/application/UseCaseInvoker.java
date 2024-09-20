package com.hit.user_service_api.application;

import com.hit.base_service_api.exception.*;
import com.hit.user_service_api.application.input.Input;
import com.hit.user_service_api.application.output.Output;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Stream;

public class UseCaseInvoker {
  private final Class<?> implementClazz;
  private final Method handleMethod;
  private final Object instance;
  @SuppressWarnings("unused")
  private final boolean hasTransaction;

  public UseCaseInvoker(ApplicationContext applicationContext, Class<?> implementClazz) {
    this.implementClazz = implementClazz;
    this.instance = applicationContext.getBean(implementClazz);
    this.hasTransaction = this.implementClazz.getAnnotation(Transactional.class) != null;
    Stream<Method> methods = Arrays.stream(this.instance.getClass().getMethods());
    this.handleMethod = methods.filter(x -> x.getName().equals("handle")).findFirst().get();
  }

  @SuppressWarnings("unchecked")
  public <TInput extends Input, TOutput extends Output> TOutput invoke(Input input)
      throws Exception {
    try {
      return (TOutput) this.handleMethod.invoke(this.instance, input);

    } catch (InvocationTargetException e) {
      if (e.getTargetException().getClass().equals(BadRequestException.class)) {
        throw (BadRequestException) e.getTargetException();
      }

      if (e.getTargetException().getClass().equals(UnauthorizedException.class)) {
        throw (UnauthorizedException) e.getTargetException();
      }

      if (e.getTargetException().getClass().equals(ForbiddenException.class)) {
        throw (ForbiddenException) e.getTargetException();
      }

      if (e.getTargetException().getClass().equals(NotFoundException.class)) {
        throw (NotFoundException) e.getTargetException();
      }

      if (e.getTargetException().getClass().equals(ConflictException.class)) {
        throw (ConflictException) e.getTargetException();
      }

      if (e.getTargetException().getClass().equals(InternalServerErrorException.class)) {
        throw (InternalServerErrorException) e.getTargetException();
      }

      throw e;
    }
  }
}
