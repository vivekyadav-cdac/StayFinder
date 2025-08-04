package com.stayfinder.sf_usermanagement.dto;

import com.stayfinder.sf_usermanagement.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterUserRequestDTO {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private Role role;
}
