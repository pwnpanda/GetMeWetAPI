package com.example.getmewetapi.repositories;

import com.example.getmewetapi.models.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<MyUser, Integer> {
    MyUser findById(int id);
    MyUser findByUsername(String username);
}