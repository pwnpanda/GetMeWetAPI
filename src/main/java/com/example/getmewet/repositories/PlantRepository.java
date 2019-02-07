package com.example.getmewet.repositories;

import com.example.getmewet.models.Plant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("plantRepository")
public interface PlantRepository extends CrudRepository<Plant, Integer> {
    List<Plant> getAllBy();
    Plant findById(int id);
    Plant findByName(String name);
    void deletePlantById(int id);
    boolean existsPlantById(int id);
}
