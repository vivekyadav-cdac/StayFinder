package com.stayfinder.sf_usermanagement.service;

import com.stayfinder.sf_usermanagement.dto.RegisterUserRequestDTO;
import com.stayfinder.sf_usermanagement.dto.UserDTO;
import com.stayfinder.sf_usermanagement.exception.UserAlreadyExistsException;
import com.stayfinder.sf_usermanagement.model.User;
import com.stayfinder.sf_usermanagement.repositories.UserRepository;
import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserDTO> getAllUsers() {
        List<User> allUser = userRepository.findByRoleIn(Arrays.asList("OWNER", "TENANT"));
        List<UserDTO> allRegisteredUser = new ArrayList<>();
        for (User u : allUser) {
            UserDTO registeredUser = new UserDTO();
            BeanUtils.copyProperties(u, registeredUser);
            allRegisteredUser.add(registeredUser);
        }
        return allRegisteredUser;
    }

    @Transactional
    public UserDTO deleteUserByEmail(String email) {
        if(userRepository.findByEmail(email).isEmpty()){
            throw new UsernameNotFoundException("User Already Deleted.");
        }
        UserDTO deletedUser = new UserDTO();
        User user = userRepository.findByEmail(email).get();
        BeanUtils.copyProperties(user, deletedUser);
        Integer isUserDeleted = userRepository.removeByEmail(email);
        if(isUserDeleted.equals(0)){
            throw new IllegalStateException("Failed to delete user with email: " +email);
        }
        return deletedUser;
    }

    @Transactional
    public UserDTO deleteUserById(Integer id) {
        if(userRepository.findById(id).isEmpty()){
            throw new UsernameNotFoundException("User Already Deleted.");
        }
        UserDTO deletedUser = new UserDTO();
        User user = userRepository.findById(id).get();
        BeanUtils.copyProperties(user, deletedUser);
        Integer isUserDeleted = userRepository.removeById(id);
        if(isUserDeleted.equals(0)){
            throw new IllegalStateException("Failed to delete user with id: " +id);
        }
        return deletedUser;
    }

    public UserDTO getUserByEmail(String email) {
        if(userRepository.findByEmail(email).isEmpty()){
            throw new UsernameNotFoundException("User not Found");
        }
        User user = userRepository.findByEmail(email).get();
        UserDTO userDto = new UserDTO();
        BeanUtils.copyProperties(user,userDto);
        return userDto;

    }

    public UserDTO getUserById(Integer id) {
        if(userRepository.findById(id).isEmpty()){
            throw new UsernameNotFoundException("User not Found");
        }
        User user = userRepository.findById(id).get();
        UserDTO userDto = new UserDTO();
        BeanUtils.copyProperties(user,userDto);
        return userDto;
    }

    public UserDTO updateUserByEmail(String email, UserDTO userDto) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        return getUpdatedUserDto(userDto, userOptional);

    }
    public UserDTO updateUserById(Integer id, UserDTO userDto) {
        Optional<User> userOptional = userRepository.findById(id);
        return getUpdatedUserDto(userDto, userOptional);

    }
    @NotNull
    private UserDTO getUpdatedUserDto(UserDTO userDto, Optional<User> userOptional) {
        if(userOptional.isEmpty()){
            throw new UsernameNotFoundException("User not Found");
        }
        User user = userOptional.get();
        String oldPassword = user.getPassword();
        String newPassword = userDto.getPassword();
        if(userDto.getLastName()!=null && userDto.getFirstName()!=null && userDto.getPhone()!=null){
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setPhone(userDto.getPhone());
        }
        if(!user.getRole().equals(userDto.getRole())&&userDto.getRole()!=null){
            user.setRole(userDto.getRole());
        }
        if(newPassword == null){
            newPassword = oldPassword;
            user.setPassword(newPassword);
            User savedUser = userRepository.save(user);
            userDto.setPassword(user.getPassword());
            return userDto;
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        User savedUser = userRepository.save(user);
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    public UserDTO registerUser(RegisterUserRequestDTO request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User already exists with email: " + request.getEmail());
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phone(request.getPhone())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        User savedUser = userRepository.save(user);

        UserDTO responseDto = new UserDTO();
        BeanUtils.copyProperties(savedUser, responseDto);
        return responseDto;
    }

    public List<UserDTO> registerUsersInBulk(List<RegisterUserRequestDTO> requests) {
        List<UserDTO> registeredUsers = new ArrayList<>();

        for (RegisterUserRequestDTO request : requests) {
            if (userRepository.findByEmail(request.getEmail()).isPresent()) {
                throw new UserAlreadyExistsException("User already exists with email: " + request.getEmail());
            }

            User user = User.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .phone(request.getPhone())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(request.getRole())
                    .build();

            User savedUser = userRepository.save(user);

            UserDTO responseDto = new UserDTO();
            BeanUtils.copyProperties(savedUser, responseDto);
            registeredUsers.add(responseDto);
        }

        return registeredUsers;
    }

}