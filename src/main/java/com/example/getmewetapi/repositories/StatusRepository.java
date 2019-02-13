package com.example.getmewetapi.repositories;

import com.example.getmewetapi.models.Status;
import com.example.getmewetapi.models.StatusAssignmentKey;
import org.springframework.data.jpa.repository.JpaRepository;



public interface StatusRepository extends JpaRepository<Status, Integer> {
    Status findBySid(StatusAssignmentKey sid);
}
