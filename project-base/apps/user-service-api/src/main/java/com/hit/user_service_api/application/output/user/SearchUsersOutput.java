package com.hit.user_service_api.application.output.user;

import com.hit.base_service_api.domain.entity.user.User;
import com.hit.user_service_api.application.output.Output;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchUsersOutput implements Output {

  private Long total;

  private List<User> items;

}
