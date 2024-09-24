package com.hit.user_service_api.adapter.web.v1.transfer.request.user;

import com.hit.base_service_api.domain.object.IsActive;
import jakarta.validation.constraints.AssertTrue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserParameter {

  private String fullName;

  private String username;

  private String email;

  private String address;

  private Integer isActive;

  @AssertTrue(message = "Email must be valid")
  public boolean isEmailValid() {
    return email != null && email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
  }

  @AssertTrue(message = "Username must be valid")
  public boolean isUsernameValid() {
    return username != null && username.matches("^[a-zA-Z0-9._%+-]{6,20}$");
  }

  @AssertTrue(message = "isActive must be valid")
  public boolean isActiveValid() {
    IsActive isActive = IsActive.parseOf(this.isActive);

    return ObjectUtils.isNotEmpty(isActive);
  }

}
