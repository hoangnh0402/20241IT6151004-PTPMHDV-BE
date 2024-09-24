package com.hit.user_service_api.infrastructure.repository.database.session.reader.user;

import com.hit.base_service_api.domain.entity.Pagination;
import com.hit.base_service_api.domain.entity.user.User;
import com.hit.user_service_api.domain.aggregation.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("DatabaseUserSessionReaderRepository")
@Mapper
public interface DatabaseUserSessionReaderRepository {

  User find(@Param("user") User user);

  Long searchCount(@Param("users") Users users);

  List<User> search(@Param("users") Users users, @Param("pagination") Pagination pagination);

}
