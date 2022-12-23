package com.codegym.webthuenha.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRegisterDTO {

    @Size(min = 6)
    private String userName;

    @Size(min = 6)
    private String password;
    private String confirmPassword;
    @Pattern(regexp = "((09|03|07|08|05)+([0-9]{8})\\b)")
    private String phone;
    @Email
    private String email;

}
