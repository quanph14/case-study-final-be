package com.codegym.webthuenha.service.user;

import com.codegym.webthuenha.model.User;
import com.codegym.webthuenha.model.UserPrinciple;
import com.codegym.webthuenha.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    private IUserRepository userRepository;


    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()){
            return UserPrinciple.build(userOptional.get());
        }
        return null;
    }
    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username).get();
        return user;
    }

    @Override
    public Optional<User> checkDoubleUser(String username) {
        return userRepository.checkDoubleUser(username);
    }

}