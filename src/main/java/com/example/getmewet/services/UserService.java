package com.example.getmewet.services;

import com.example.getmewet.models.Role;
import com.example.getmewet.models.User;
import com.example.getmewet.repositories.RoleRepository;
import com.example.getmewet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service("UserService")
public class UserService {

    private UserRepository userRepo;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepo,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findById(int id) {
        return userRepo.findById(id);
    }

    public User findByUsername(String username){
        User thisUser = userRepo.findByUsername(username);
        return thisUser;
    }

    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepo.save(user);
    }

    public List<User> findAll(){
        return userRepo.findAll();
    }

}
