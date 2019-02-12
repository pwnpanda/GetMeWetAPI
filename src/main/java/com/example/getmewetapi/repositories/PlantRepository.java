package com.example.getmewetapi.repositories;

import com.example.getmewetapi.models.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("plantRepository")
public interface PlantRepository extends JpaRepository<Plant, Integer> {
    Plant findById(int id);
    Plant findByName(String name);
}
