package com.example.getmewetapi.controllers;

import com.example.getmewetapi.models.MyUser;
import com.example.getmewetapi.services.UserService;
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
    public ResponseEntity<List<MyUser>> listAllUsers(){
        logger.info("Get all users from API!");
        List<MyUser> users = userService.findAll();
        if (users.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<MyUser>>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<MyUser> getUserId(@PathVariable String id){
        logger.info("Get user with id " + id);
        MyUser user;
        try {
            System.out.println("Find by int!");
            int id_l = Integer.parseInt(id);
            user = userService.findById(id_l);
            System.out.println("Found user int: " + user);
        } catch (Exception e){
            System.out.println("Find by string!");
            user = userService.findByUsername(id);
            System.out.println("Found user str: " + user);

        }
        if (user == null){
            System.out.println("No user!");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        System.out.println(user);
        return new ResponseEntity<MyUser>(user, HttpStatus.OK);
    }

    // ------------------------------------ POST ------------------------------------------

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody MyUser user){
        System.out.println(user);
        MyUser us = userService.findByUsername(user.getUsername());
        if (us != null) {
            // Already exists
            return new ResponseEntity<String>("Error when registering user. Please try again. Username may be taken, password invalid, etc.", HttpStatus.NOT_MODIFIED);
        }
        user.setPassword(pwenc.encode(user.getPassword()));
        userService.saveUser(user);
        return new ResponseEntity<MyUser>(user, HttpStatus.CREATED);
    }

}
