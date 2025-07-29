package com.stayfinder.Auth_Service.repositories;

import java.util.Optional;

import com.stayfinder.Auth_Service.models.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}
