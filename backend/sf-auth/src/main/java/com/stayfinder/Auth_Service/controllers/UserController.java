package com.stayfinder.Auth_Service.controllers;


import com.stayfinder.Auth_Service.dto.UserDto;
import com.stayfinder.Auth_Service.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUser() {

        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PreAuthorize("hasAnyRole('ADMIN','TENANT','OWNER')")
    @DeleteMapping("/email/{email}")
    public ResponseEntity<UserDto> deleteUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.deleteUserByEmail(email));
    }

    @PreAuthorize("hasAnyRole('ADMIN','TENANT','OWNER')")
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @PreAuthorize("hasAnyRole('TENANT','OWNER')")
    @PutMapping("/email/{email}")
    public ResponseEntity<UserDto> updateUserByEmail(@PathVariable String email, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUserByEmail(email, userDto));
    }

    @PreAuthorize("hasAnyRole('ADMIN','TENANT','OWNER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.deleteUserById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN','TENANT','OWNER')")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PreAuthorize("hasAnyRole('TENANT','OWNER')")
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUserById(@PathVariable Integer id, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUserById(id, userDto));
    }
}
