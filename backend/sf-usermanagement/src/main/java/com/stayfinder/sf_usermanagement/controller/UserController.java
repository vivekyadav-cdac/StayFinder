package com.stayfinder.sf_usermanagement.controller;

import com.stayfinder.sf_usermanagement.dto.RegisterUserRequestDTO;
import com.stayfinder.sf_usermanagement.dto.UserDTO;
import com.stayfinder.sf_usermanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody RegisterUserRequestDTO request) {
        UserDTO createdUser = userService.registerUser(request);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    @GetMapping("/login/{email}")
    public ResponseEntity<UserDTO> userLogin(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'TENANT', 'OWNER')")
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @PostMapping("/register/bulk")
    public ResponseEntity<List<UserDTO>> registerMultipleUsers(@RequestBody List<RegisterUserRequestDTO> requests) {
        List<UserDTO> registeredUsers = userService.registerUsersInBulk(requests);
        return new ResponseEntity<>(registeredUsers, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUser() {

        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PreAuthorize("hasAnyRole('ADMIN','TENANT','OWNER')")
    @DeleteMapping("/email/{email}")
    public ResponseEntity<UserDTO> deleteUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.deleteUserByEmail(email));
    }




    @PreAuthorize("hasAnyRole('TENANT','OWNER')")
    @PutMapping("/email/{email}")
    public ResponseEntity<UserDTO> updateUserByEmail(@PathVariable String email, @RequestBody UserDTO userDto) {
        return ResponseEntity.ok(userService.updateUserByEmail(email, userDto));
    }

    @PreAuthorize("hasAnyRole('ADMIN','TENANT','OWNER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.deleteUserById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN','TENANT','OWNER')")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PreAuthorize("hasAnyRole('TENANT','OWNER')")
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUserById(@PathVariable Integer id, @RequestBody UserDTO userDto) {
        return ResponseEntity.ok(userService.updateUserById(id, userDto));
    }
}
