package com.stayfinder.sf_usermanagement.service;

import com.stayfinder.sf_usermanagement.dto.UserResponseDTO;
import com.stayfinder.sf_usermanagement.dto.UserUpdateDTO;

import java.util.List;

public interface UserService {
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getUserById(Integer id);
    void deleteUser(Integer id);
    UserResponseDTO updateSelf(Integer id, UserUpdateDTO dto);
    void deleteSelf(Integer id);
}