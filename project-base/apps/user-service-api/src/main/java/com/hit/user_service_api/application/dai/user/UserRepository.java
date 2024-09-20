package com.hit.user_service_api.application.dai.user;

import com.hit.base_service_api.domain.entity.Pagination;
import com.hit.base_service_api.domain.entity.Tracking;
import com.hit.base_service_api.domain.entity.user.User;
import com.hit.user_service_api.domain.aggregation.Users;

public interface UserRepository {

  /**
   * Find user by user
   *
   * @param user     User
   * @param tracking Tracking
   * @return User
   */
  User find(User user, Tracking tracking);

  /**
   * Search users
   *
   * @param users      Users
   * @param pagination Pagination
   * @param tracking   Tracking
   * @return Users
   */
  Users search(Users users, Pagination pagination, Tracking tracking);

  /**
   * Save user
   *
   * @param user     User
   * @param tracking Tracking
   * @return User
   */
  User save(User user, Tracking tracking);

  /**
   * Update user
   *
   * @param user     User
   * @param tracking Tracking
   */
  void update(User user, Tracking tracking);

  /**
   * Delete user
   *
   * @param user     User
   * @param tracking Tracking
   */
  void delete(User user, Tracking tracking);

}
