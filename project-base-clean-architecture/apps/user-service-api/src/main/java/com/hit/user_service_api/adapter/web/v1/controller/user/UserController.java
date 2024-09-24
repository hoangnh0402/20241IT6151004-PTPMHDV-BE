package com.hit.user_service_api.adapter.web.v1.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hit.base_service_api.domain.entity.Tracking;
import com.hit.base_service_api.domain.object.IsActive;
import com.hit.base_service_api.domain.object.pagination.PageNum;
import com.hit.base_service_api.domain.object.pagination.PageSize;
import com.hit.base_service_api.domain.object.pagination.SortBy;
import com.hit.base_service_api.domain.object.pagination.SortType;
import com.hit.base_service_api.domain.object.user.*;
import com.hit.user_service_api.adapter.web.base.RestApiV1;
import com.hit.user_service_api.adapter.web.base.VsResponseUtil;
import com.hit.user_service_api.adapter.web.v1.transfer.request.user.CreateUserParameter;
import com.hit.user_service_api.adapter.web.v1.transfer.request.user.SearchUsersParameter;
import com.hit.user_service_api.adapter.web.v1.transfer.request.user.UpdateUserParameter;
import com.hit.user_service_api.adapter.web.v1.transfer.response.ResponseHeader;
import com.hit.user_service_api.adapter.web.v1.transfer.response.user.*;
import com.hit.user_service_api.application.UseCaseBus;
import com.hit.user_service_api.application.constant.UrlConstant;
import com.hit.user_service_api.application.input.user.*;
import com.hit.user_service_api.application.output.user.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestApiV1
public class UserController {
  private final ObjectMapper mapper;
  private final UseCaseBus useCaseBus;
  private final ResponseHeader responseHeader;

  @Value("${server.request.attribute.name.tracking}")
  private String trackingRequestAttributeName;

  public UserController(ObjectMapper mapper,
                        @Qualifier("ApplicationUseCaseBus") UseCaseBus useCaseBus,
                        @Qualifier("WebV1TransferResponseHeader") ResponseHeader responseHeader) {
    this.mapper = mapper;
    this.useCaseBus = useCaseBus;
    this.responseHeader = responseHeader;
  }

  @GetMapping(UrlConstant.User.FIND)
  public ResponseEntity<?> find(@PathVariable UserId id, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
    // Get tracking from request
    Tracking tracking = (Tracking) request.getAttribute(this.trackingRequestAttributeName);

    // Build input
    FindUserInput input = new FindUserInput(id, tracking);

    // Handle input and get output
    FindUserOutput output = this.useCaseBus.handle(input);

    // Map output to response
    FindUserResponse responseBody =
        mapper.readValue(mapper.writeValueAsString(output.getItem()), FindUserResponse.class);

    // Return response
    return VsResponseUtil.ok(this.responseHeader.getHeader(), responseBody);
  }

  @GetMapping(UrlConstant.User.SEARCH)
  public ResponseEntity<?> search(@Valid SearchUsersParameter parameter, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // Get tracking from request
    Tracking tracking = (Tracking) request.getAttribute(this.trackingRequestAttributeName);

    // Build input
    SearchUsersInput input = new SearchUsersInput();
    input.setIds(parameter.getIds());
    input.setKeyword(parameter.getKeyword());
    input.setPageNum(new PageNum(parameter.getPageNum()));
    input.setPageSize(new PageSize(parameter.getPageSize()));
    input.setSortBy(new SortBy(parameter.getSortBy()));
    input.setSortType(new SortType(parameter.getSortType()));
    input.setTracking(tracking);

    // Handle input and get output
    SearchUsersOutput output = this.useCaseBus.handle(input);

    // Map output to response
    SearchUsersResponse responseBody =
        mapper.readValue(mapper.writeValueAsString(output), SearchUsersResponse.class);

    // Return response
    return VsResponseUtil.ok(this.responseHeader.getHeader(), responseBody);
  }

  @PostMapping(UrlConstant.User.CREATE)
  public ResponseEntity<?> create(@Valid @RequestBody CreateUserParameter parameter, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
    // Get tracking from request
    Tracking tracking = (Tracking) request.getAttribute(this.trackingRequestAttributeName);

    // Build input
    CreateUserInput input = new CreateUserInput();
    input.setUsername(new Username(parameter.getUsername()));
    input.setFullName(new FullName(parameter.getFullName()));
    input.setEmail(new Email(parameter.getEmail()));
    input.setAddress(new Address(parameter.getAddress()));
    input.setTracking(tracking);

    // Handle input and get output
    CreateUserOutput output = this.useCaseBus.handle(input);

    // Map output to response
    CreateUserResponse responseBody =
        mapper.readValue(mapper.writeValueAsString(output.getItem()), CreateUserResponse.class);

    // Return response
    return VsResponseUtil.ok(this.responseHeader.getHeader(), responseBody);
  }

  @PatchMapping(UrlConstant.User.UPDATE)
  public ResponseEntity<?> update(@PathVariable UserId id, @Valid @RequestBody UpdateUserParameter parameter,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
    // Get tracking from request
    Tracking tracking = (Tracking) request.getAttribute(this.trackingRequestAttributeName);

    // Build input
    UpdateUserInput input = new UpdateUserInput();
    input.setId(id);
    input.setUsername(new Username(parameter.getUsername()));
    input.setFullName(new FullName(parameter.getFullName()));
    input.setEmail(new Email(parameter.getEmail()));
    input.setAddress(new Address(parameter.getAddress()));
    input.setIsActive(IsActive.parseOf(parameter.getIsActive()));
    input.setTracking(tracking);

    // Handle input and get output
    UpdateUserOutput output = this.useCaseBus.handle(input);

    // Map output to response
    UpdateUserResponse responseBody =
        mapper.readValue(mapper.writeValueAsString(output.getItem()), UpdateUserResponse.class);

    // Return response
    return VsResponseUtil.ok(this.responseHeader.getHeader(), responseBody);
  }

  @DeleteMapping(UrlConstant.User.DELETE)
  public ResponseEntity<?> delete(@PathVariable UserId id, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
    // Get tracking from request
    Tracking tracking = (Tracking) request.getAttribute(this.trackingRequestAttributeName);

    // Build input
    DeleteUserInput input = new DeleteUserInput(id, tracking);

    // Handle input and get output
    DeleteUserOutput output = this.useCaseBus.handle(input);

    // Map output to response
    DeleteUserResponse responseBody =
        mapper.readValue(mapper.writeValueAsString(output.getItem()), DeleteUserResponse.class);

    // Return response
    return VsResponseUtil.ok(this.responseHeader.getHeader(), responseBody);
  }


}
