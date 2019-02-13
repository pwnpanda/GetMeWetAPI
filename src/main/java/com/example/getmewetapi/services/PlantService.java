package com.example.getmewetapi.services;

import com.example.getmewetapi.models.Plant;
import com.example.getmewetapi.repositories.PlantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service("PlantService")
public class PlantService {

    private PlantRepository plantRepo;

    public static final Logger logger = LoggerFactory.getLogger(PlantService.class);

    @Autowired
    public PlantService(PlantRepository plantRepo){
        this.plantRepo = plantRepo;
    }

    public List<Plant> getAll() {
        return plantRepo.findAll();
    }

    public Plant findById(int id){
        return plantRepo.findById(id);
    }

    public Plant findByName(String name){
        return plantRepo.findByName(name);
    }

    public Plant getPlantByPath(String id){
        Plant pl = null;
        try {
            logger.debug("Find by int!");
            int id_l = Integer.parseInt(id);
            pl = findById(id_l);
            logger.debug("Found Plant int: " + pl);
        } catch (Exception e){
            logger.debug("Find by string!");
            pl = findByName(id);
            logger.debug("Found plant str: " + pl);
        }
        return pl;
    }

    public void createPlant(Plant plant){
        int index = getAll().indexOf(findById(plant.getId()));
        if (index == -1) {
            plantRepo.save(plant);
            logger.debug("Added plant " + plant);
            return;
        }
        logger.debug("Plant already exists! No action taken! " + plant);
    }

    public void updatePlant(Plant plant){
        logger.debug("Updating plant plant " + findById(plant.getId()));
        int index = getAll().indexOf(findById(plant.getId()));
        if (index != -1){
            plantRepo.save(plant);
            logger.debug("Updated plant to " + plant);
            return;
        }
        logger.debug("Plant not found! " + plant);
    }

    public void deletePlantById(int id){
        for (Iterator<Plant> iterator = getAll().iterator(); iterator.hasNext();) {
            Plant plant = iterator.next();
            if (plant.getId() == id) {
                logger.debug("Deleted plant " + plant);
                plantRepo.delete(plant);
                return;
            }
        }
        logger.debug("Plant not found! ID: " + id);
    }
}
