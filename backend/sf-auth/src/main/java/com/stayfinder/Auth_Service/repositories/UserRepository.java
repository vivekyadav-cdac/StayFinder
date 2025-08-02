package com.stayfinder.Auth_Service.repositories;

import java.util.List;
import java.util.Optional;

import com.stayfinder.Auth_Service.models.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    @NotNull Optional<User> findById(@NotNull Integer id);
    List<User> findByRoleIn(List<String> roles);
    Integer removeByEmail(String email);
    Integer removeById(Integer id);
    boolean existsByEmail(String email);
    boolean existsById(@NotNull Integer id);
}
