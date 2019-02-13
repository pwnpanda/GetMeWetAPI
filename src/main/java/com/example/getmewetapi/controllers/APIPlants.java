package com.example.getmewetapi.controllers;

import com.example.getmewetapi.models.Plant;
import com.example.getmewetapi.services.PlantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plant")
public class APIPlants {

    public static final Logger logger = LoggerFactory.getLogger(APIPlants.class);


    @Autowired
    PlantService plantService;

    @GetMapping("/all")
    public ResponseEntity<List<Plant>> getAllPlants(){
        logger.info("Get all plants from API!");
        List<Plant> plants = plantService.getAll();
        if (plants.isEmpty()){
            logger.debug("No plants found!");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Plant>>(plants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plant> getPlant(@PathVariable String id){
        logger.info("Get plant with id" + id);
        Plant plant = plantService.getPlantByPath(id);
        if (plant == null){
            logger.debug("No plant found!");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        logger.debug("Found plant " + plant);
        return new ResponseEntity<Plant>(plant, HttpStatus.OK);
    }

    // ------------------------------------ PUT ------------------------------------------
    @PutMapping("/update")
    public ResponseEntity<?> updatePlant(@RequestBody Plant plant){
        logger.debug("Change plant: " + plant);
        Plant pl = plantService.getPlantByPath(plant.getName());
        if (pl == null) {
            logger.debug("No plant found!");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        plantService.updatePlant(plant);
        logger.debug("Updated plant " + plant);
        return new ResponseEntity<Plant>(plant, HttpStatus.OK);
    }

    // ------------------------------------ POST ------------------------------------------
    // TODO handle adding status for today and coming days
    @PostMapping("/create")
    public ResponseEntity<?> createPlant(@RequestBody Plant plant){
        logger.debug("Create plant " + plant);
        Plant us = plantService.findById(plant.getId());
        if (us != null) {
            // Already exists
            logger.debug("Plant already exists! Plant " + plant);
            return new ResponseEntity<String>("Error when creating plant. Please try again.", HttpStatus.NOT_MODIFIED);
        }
        plantService.createPlant(plant);
        logger.debug("Created plant " + plant);
        return new ResponseEntity<Plant>(plant, HttpStatus.CREATED);
    }

    // ------------------------------------ DEL ------------------------------------------

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delPlant(@PathVariable String id){
        logger.info("Delete plant with id" + id);
        Plant plant = plantService.getPlantByPath(id);
        if (plant == null){
            logger.debug("No plant found! Id: " +id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        String plantName = plant.getName();
        plantService.deletePlantById(plant.getId());
        logger.debug("Deleted plant " + plantName);
        return new ResponseEntity<String>("Deleted plant " + plantName, HttpStatus.OK);
    }
}
