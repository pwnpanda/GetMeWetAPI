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

    @GetMapping("/all")
    public ResponseEntity<List<Plant>> getAllPlants(){
        logger.info("Get all plants from API!");
        List<Plant> plants = plantService.getAllBy();
        if (plants.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Plant>>(plants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plant> getPlant(@PathVariable int id){
        logger.info("Get user with id" + id);
        Plant plant = plantService.findById(id);
        if (plant == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Plant>(plant, HttpStatus.OK);
    }



}
