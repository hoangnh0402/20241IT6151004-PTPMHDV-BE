package com.hit.user_service_api.infrastructure.repository.database.user;

import com.hit.base_service_api.domain.entity.Pagination;
import com.hit.base_service_api.domain.entity.Tracking;
import com.hit.base_service_api.domain.entity.user.User;
import com.hit.user_service_api.application.dai.user.UserRepository;
import com.hit.user_service_api.domain.aggregation.Users;
import com.hit.user_service_api.infrastructure.repository.database.session.reader.user.DatabaseUserSessionReaderRepository;
import com.hit.user_service_api.infrastructure.repository.database.session.writer.user.DatabaseUserSessionWriterRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Log4j2
@Repository("DatabaseUserRepository")
public class DatabaseUserRepository implements UserRepository {
  private final DatabaseUserSessionReaderRepository databaseUserSessionReaderRepository;
  private final DatabaseUserSessionWriterRepository databaseUserSessionWriterRepository;

  public DatabaseUserRepository(@Qualifier("DatabaseUserSessionReaderRepository") DatabaseUserSessionReaderRepository databaseUserSessionReaderRepository,
                                @Qualifier("DatabaseUserSessionWriterRepository") DatabaseUserSessionWriterRepository databaseUserSessionWriterRepository) {
    this.databaseUserSessionReaderRepository = databaseUserSessionReaderRepository;
    this.databaseUserSessionWriterRepository = databaseUserSessionWriterRepository;
  }

  @Override
  public User find(User user, Tracking tracking) {
    // Log demo tracking -> tracking can use for log, metrics, tracing
    log.info("Tracking: {}", tracking.getTrackingId().getValue());

    // Find user from database
    return this.databaseUserSessionReaderRepository.find(user);
  }

  @Override
  public Users search(Users users, Pagination pagination, Tracking tracking) {
    long total = databaseUserSessionReaderRepository.searchCount(users);
    if (total == 0) {
      return new Users();
    }

    List<User> items = databaseUserSessionReaderRepository.search(users, pagination);

    return new Users(items, total);
  }

  @Override
  public User save(User user, Tracking tracking) {
    databaseUserSessionWriterRepository.save(user);

    return databaseUserSessionReaderRepository.find(user);
  }

  @Override
  public void update(User user, Tracking tracking) {
    databaseUserSessionWriterRepository.update(user);
  }

  @Override
  public void delete(User user, Tracking tracking) {
    databaseUserSessionWriterRepository.delete(user);
  }

}
