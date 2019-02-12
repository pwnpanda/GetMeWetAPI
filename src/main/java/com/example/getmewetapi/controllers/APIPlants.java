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

    public static final Logger logger = LoggerFactory.getLogger(APIUser.class);


    @Autowired
    PlantService plantService;

    private Plant getPlantByPath(String id){
        Plant pl = null;
        try {
            System.out.println("Find by int!");
            int id_l = Integer.parseInt(id);
            pl = plantService.findById(id_l);
            System.out.println("Found user int: " + pl);
        } catch (Exception e){
            System.out.println("Find by string!");
            pl = plantService.findByName(id);
            System.out.println("Found user str: " + pl);
        }
        return pl;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Plant>> getAllPlants(){
        logger.info("Get all plants from API!");
        List<Plant> plants = plantService.getAll();
        if (plants.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Plant>>(plants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plant> getPlant(@PathVariable String id){
        logger.info("Get user with id" + id);
        Plant plant = getPlantByPath(id);
        if (plant == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Plant>(plant, HttpStatus.OK);
    }

    // ------------------------------------ PUT ------------------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePlant(@PathVariable String id, @RequestBody Plant plant){
        System.out.println("Change plant with id: " + id);
        Plant pl = getPlantByPath(id);
        if (pl == null) {
            System.out.println("No plant found!");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        plantService.updatePlant(plant);
        return new ResponseEntity<Plant>(plant, HttpStatus.OK);
    }

    // ------------------------------------ POST ------------------------------------------

    @PostMapping("/create")
    public ResponseEntity<?> createPlant(@RequestBody Plant plant){
        System.out.println(plant);
        Plant us = plantService.findById(plant.getId());
        if (us != null) {
            // Already exists
            return new ResponseEntity<String>("Error when creating plant. Please try again.", HttpStatus.NOT_MODIFIED);
        }
        plantService.createPlant(plant);
        return new ResponseEntity<Plant>(plant, HttpStatus.CREATED);
    }

    // ------------------------------------ DEL ------------------------------------------

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delPlant(@PathVariable String id){
        logger.info("Delete user with id" + id);
        Plant plant = getPlantByPath(id);
        if (plant == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        String plantName = plant.getName();
        plantService.deletePlantById(plant.getId());
        return new ResponseEntity<String>("Deleted plant " + plantName, HttpStatus.OK);
    }
}
