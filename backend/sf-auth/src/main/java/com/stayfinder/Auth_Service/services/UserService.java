package com.stayfinder.Auth_Service.services;

import com.stayfinder.Auth_Service.dto.UserDto;
import com.stayfinder.Auth_Service.models.User;
import com.stayfinder.Auth_Service.repositories.UserRepository;
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

    public List<UserDto> getAllUsers() {
        List<User> allUser = userRepository.findByRoleIn(Arrays.asList("OWNER", "TENANT"));
        List<UserDto> allRegisteredUser = new ArrayList<>();
        for (User u : allUser) {
            UserDto registeredUser = new UserDto();
            BeanUtils.copyProperties(u, registeredUser);
            allRegisteredUser.add(registeredUser);
        }
        return allRegisteredUser;
    }

    @Transactional
    public UserDto deleteUserByEmail(String email) {
        if(userRepository.findByEmail(email).isEmpty()){
            throw new UsernameNotFoundException("User Already Deleted.");
        }
        UserDto deletedUser = new UserDto();
        User user = userRepository.findByEmail(email).get();
        BeanUtils.copyProperties(user, deletedUser);
        Integer isUserDeleted = userRepository.removeByEmail(email);
        if(isUserDeleted.equals(0)){
            throw new IllegalStateException("Failed to delete user with email: " +email);
        }
        return deletedUser;
    }

    @Transactional
    public UserDto deleteUserById(Integer id) {
        if(userRepository.findById(id).isEmpty()){
            throw new UsernameNotFoundException("User Already Deleted.");
        }
        UserDto deletedUser = new UserDto();
        User user = userRepository.findById(id).get();
        BeanUtils.copyProperties(user, deletedUser);
        Integer isUserDeleted = userRepository.removeById(id);
        if(isUserDeleted.equals(0)){
            throw new IllegalStateException("Failed to delete user with id: " +id);
        }
        return deletedUser;
    }

    public UserDto getUserByEmail(String email) {
        if(userRepository.findByEmail(email).isEmpty()){
            throw new UsernameNotFoundException("User not Found");
        }
        User user = userRepository.findByEmail(email).get();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user,userDto);
        return userDto;
        
    }

    public UserDto getUserById(Integer id) {
        if(userRepository.findById(id).isEmpty()){
            throw new UsernameNotFoundException("User not Found");
        }
        User user = userRepository.findById(id).get();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user,userDto);
        return userDto;
    }

    public UserDto updateUserByEmail(String email, UserDto userDto) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        return getUpdatedUserDto(userDto, userOptional);

    }
    public UserDto updateUserById(Integer id, UserDto userDto) {
        Optional<User> userOptional = userRepository.findById(id);
        return getUpdatedUserDto(userDto, userOptional);

    }
    @NotNull
    private UserDto getUpdatedUserDto(UserDto userDto, Optional<User> userOptional) {
        if(userOptional.isEmpty()){
            throw new UsernameNotFoundException("User not Found");
        }
        User user = userOptional.get();
        BeanUtils.copyProperties(userDto,user);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User savedUser = userRepository.save(user);
        if(!userDto.getEmail().equals(savedUser.getEmail())){
            throw  new IllegalStateException("Unable to Update User Details");
        }
        userDto.setPassword(user.getPassword());
        return userDto;
    }


}
