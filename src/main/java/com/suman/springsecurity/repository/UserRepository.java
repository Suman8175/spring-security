package com.suman.springsecurity.repository;

import com.suman.springsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByUserEmail(String userEmail);

    Optional<User> findByUserEmail(String userEmail);
}
