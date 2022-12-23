package com.codegym.webthuenha.controller.user;

import com.codegym.webthuenha.model.House;
import com.codegym.webthuenha.model.User;
import com.codegym.webthuenha.service.house.IHouseService;
import com.codegym.webthuenha.service.role.IRoleService;
import com.codegym.webthuenha.service.role.RoleService;
import com.codegym.webthuenha.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IHouseService houseService;
    @Autowired
    private UserService userService;
    @GetMapping("/findAllUsers")
    public ResponseEntity<Iterable<User>> showAllUser() {
        Iterable<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getProfile(@PathVariable Long id) {
        Optional<User> userOptional = this.userService.findById(id);
        return userOptional.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUserProfile(@PathVariable Long id, @RequestBody User user) {
        Optional<User> userOptional = this.userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User newUser = userOptional.get();
        newUser.setFullName(user.getFullName());
        newUser.setId(id);
        newUser.setUserAddress(user.getUserAddress());
        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setEmail(user.getEmail());
        if (user.getAvatar() != null && user.getAvatar() != ""){
            newUser.setAvatar(user.getAvatar());
        }
        newUser.setAvatar(user.getAvatar());
        newUser.setRole(roleService.findById(Long.parseLong("1")).get());
        userService.save(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }


    @PutMapping("/favouriteHouse/{userId}/{houseId}")
    public ResponseEntity<User> favouriteHouse(@PathVariable("userId") Long userId,@PathVariable("houseId") Long houseId){
        if (!userService.findById(userId).isPresent()|| !houseService.findById(houseId).isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = userService.findById(userId).get();
        House house = houseService.findById(houseId).get();
        user.getFavouriteHouse().add(house);
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
