package com.example.getmewet.controllers;

import com.example.getmewet.models.User;
import com.example.getmewet.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class APIUser {

    @Autowired
    UserService userService;


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
    public ResponseEntity<User> getUser(@PathVariable int id){
        logger.info("Get user with id" + id);
        User user = userService.findById(id);
        if (user == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public login(){

    }

    @PostMapping(value = "/register")
    public login(){

    }

}
