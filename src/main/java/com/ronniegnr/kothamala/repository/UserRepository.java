package com.ronniegnr.kothamala.repository;

import com.ronniegnr.kothamala.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByEmail(String email);

}
