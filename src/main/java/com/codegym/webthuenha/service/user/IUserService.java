package com.codegym.webthuenha.service.user;

import com.codegym.webthuenha.model.User;
import com.codegym.webthuenha.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User findByUsername(String username);

    Optional<User> checkDoubleUser(String username);

}