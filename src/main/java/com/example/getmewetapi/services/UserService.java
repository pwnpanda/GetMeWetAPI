package com.example.getmewetapi.services;

import com.example.getmewetapi.models.Role;
import com.example.getmewetapi.models.MyUser;
import com.example.getmewetapi.repositories.RoleRepository;
import com.example.getmewetapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static java.util.Collections.emptyList;

@Service("UserService")
public class UserService implements UserDetailsService {

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

    public MyUser findById(int id) {
        return userRepo.findById(id);
    }

    public MyUser findByUsername(String username){
        MyUser thisUser = userRepo.findByUsername(username);
        return thisUser;
    }

    public void saveUser(MyUser user) {
        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepo.save(user);
    }

    public List<MyUser> findAll(){
        return userRepo.findAll();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser applicationUser = userRepo.findByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), applicationUser.getAuthorities());
    }
}
