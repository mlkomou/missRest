package com.miss.api.auth.repository;

import com.miss.api.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    @Query("from User u where u.id=:id")
    User findUserById(@Param(value = "id") Long id);

    @Query("from User u where u.id<>:id and u.username=:username")
    List<User> checkExistingUser(@Param(value = "id") Long id, @Param(value = "username") String username);

    boolean existsByUsername(String username);
}
