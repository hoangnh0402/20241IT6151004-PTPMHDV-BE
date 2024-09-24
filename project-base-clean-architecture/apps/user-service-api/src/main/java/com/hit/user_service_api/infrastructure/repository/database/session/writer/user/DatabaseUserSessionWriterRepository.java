package com.hit.user_service_api.infrastructure.repository.database.session.writer.user;

import com.hit.base_service_api.domain.entity.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("DatabaseUserSessionWriterRepository")
@Mapper
public interface DatabaseUserSessionWriterRepository {

  void update(@Param("user") User user);

  void save(@Param("user") User user);

  void delete(@Param("user") User user);

}
