package com.stayfinder.sf_usermanagement.service;

import com.stayfinder.sf_usermanagement.dto.UserResponseDTO;
import com.stayfinder.sf_usermanagement.dto.UserUpdateDTO;
import com.stayfinder.sf_usermanagement.model.Role;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserServiceImpl implements UserService {

    private final Map<Integer, UserResponseDTO> dummyDb = new ConcurrentHashMap<>();

    public UserServiceImpl() {
        dummyDb.put(1, new UserResponseDTO(1, "Admin", "User", "admin@gmail.com", Role.ADMIN));
        dummyDb.put(2, new UserResponseDTO(2, "John", "Doe", "tenant@gmail.com", Role.TENANT));
        dummyDb.put(3, new UserResponseDTO(3, "Alice", "Smith", "owner@gmail.com", Role.OWNER));
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return new ArrayList<>(dummyDb.values());
    }

    @Override
    public UserResponseDTO getUserById(Integer id) {
        return dummyDb.get(id);
    }

    @Override
    public void deleteUser(Integer id) {
        dummyDb.remove(id);
    }

    @Override
    public UserResponseDTO updateSelf(Integer id, UserUpdateDTO dto) {
        UserResponseDTO user = dummyDb.get(id);
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        return user;
    }

    @Override
    public void deleteSelf(Integer id) {
        dummyDb.remove(id);
    }
}
