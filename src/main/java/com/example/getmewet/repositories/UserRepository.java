package com.example.getmewet.repositories;

import com.example.getmewet.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
    User findById(int id);
    User findByUsername(String username);
}