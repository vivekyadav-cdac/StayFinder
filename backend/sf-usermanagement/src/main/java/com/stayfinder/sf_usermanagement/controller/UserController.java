package com.stayfinder.sf_usermanagement.controller;

import com.stayfinder.sf_usermanagement.dto.UserResponseDTO;
import com.stayfinder.sf_usermanagement.dto.UserUpdateDTO;
import com.stayfinder.sf_usermanagement.service.UserService;
import com.stayfinder.sf_usermanagement.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtils jwtUtils;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/me")
    public ResponseEntity<UserResponseDTO> updateProfile(@RequestHeader("Authorization") String authHeader,
                                                         @RequestBody UserUpdateDTO dto) {
        String token = authHeader.substring(7);
        Integer id = jwtUtils.getUserId(token);
        return ResponseEntity.ok(userService.updateSelf(id, dto));
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteSelf(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        Integer id = jwtUtils.getUserId(token);
        userService.deleteSelf(id);
        return ResponseEntity.noContent().build();
    }
}
