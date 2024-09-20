package com.hit.user_service_api.application.input.user;

import com.hit.base_service_api.domain.entity.Tracking;
import com.hit.base_service_api.domain.object.user.Address;
import com.hit.base_service_api.domain.object.user.Email;
import com.hit.base_service_api.domain.object.user.FullName;
import com.hit.base_service_api.domain.object.user.Username;
import com.hit.user_service_api.application.input.Input;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserInput implements Input {

  private FullName fullName;

  private Username username;

  private Email email;

  private Address address;

  private Tracking tracking;

}
