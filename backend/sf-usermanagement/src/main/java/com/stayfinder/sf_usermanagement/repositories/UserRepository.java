package com.stayfinder.sf_usermanagement.repositories;

import com.stayfinder.sf_usermanagement.model.User;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    @NotNull Optional<User> findById(@NotNull Integer id);
    List<User> findByRoleIn(List<String> roles);
    Integer removeByEmail(String email);
    Integer removeById(Integer id);
    boolean existsByEmail(String email);
    boolean existsById(@NotNull Integer id);
}