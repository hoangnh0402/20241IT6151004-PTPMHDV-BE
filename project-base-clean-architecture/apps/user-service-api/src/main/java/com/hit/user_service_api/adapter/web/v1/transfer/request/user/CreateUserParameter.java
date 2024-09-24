package com.hit.user_service_api.adapter.web.v1.transfer.request.user;

import jakarta.validation.constraints.AssertTrue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserParameter {

  private String fullName;

  private String username;

  private String email;

  private String address;

  @AssertTrue(message = "Email must be valid")
  public boolean isEmailValid() {
    return email != null && email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
  }

  @AssertTrue(message = "Username must be valid")
  public boolean isUsernameValid() {
    return username != null && username.matches("^[a-zA-Z0-9._%+-]{6,20}$");
  }

}
