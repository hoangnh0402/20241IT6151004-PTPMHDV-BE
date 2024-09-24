package com.hit.user_service_api.application.input.user;

import com.hit.base_service_api.domain.entity.Tracking;
import com.hit.base_service_api.domain.object.IsActive;
import com.hit.base_service_api.domain.object.user.*;
import com.hit.user_service_api.application.input.Input;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserInput implements Input {

  private UserId id;

  private FullName fullName;

  private Username username;

  private Email email;

  private Address address;

  private IsActive isActive;

  private Tracking tracking;

}
