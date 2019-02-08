package com.example.getmewet.controllers;

import com.example.getmewet.models.User;
import com.example.getmewet.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class APIUser {

    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder pwenc;


    public static final Logger logger = LoggerFactory.getLogger(APIUser.class);

    @GetMapping(value = "/user/all")
    public ResponseEntity<List<User>> listAllUsers(){
        logger.info("Get all users from API!");
        List<User> users = userService.findAll();
        if (users.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> getUserId(@PathVariable int id){
        logger.info("Get user with id" + id);
        User user = userService.findById(id);
        if (user == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{username}")
    public ResponseEntity<User> getUserUsername(@PathVariable String username){
        logger.info("Get user with id" + username);
        User user = userService.findByUsername(username);
        if (user == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.FOUND);
    }

    /*
    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody User user){
        User localUser = userService.findByUsername(user.getUsername());
        ResponseEntity error = new ResponseEntity("Invalid credentials!", HttpStatus.UNAUTHORIZED);
        System.out.println("localUser PW: " +  localUser.getPassword() + " LoginUser PW: " +  user.getPassword());
        if (localUser == null){
            return error;
        } else if( localUser.getPassword() == user.getPassword()) {
            return new ResponseEntity("User " + user.getUsername() + " logged in!", HttpStatus.OK);
        } else{
            return error;
        }
    }*/

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody User user){
        System.out.println(user);
        User us = userService.findByUsername(user.getUsername());
        if (us != null) {
            // Already exists
            return new ResponseEntity<String>("Error when registering user. Please try again. Username may be taken, password invalid, etc.", HttpStatus.NOT_MODIFIED);
        }
        userService.saveUser(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

}
