package com.example.getmewetapi.controllers;


import com.example.getmewetapi.models.Status;
import com.example.getmewetapi.services.StatusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/status")
public class APIStatus {
    public static final Logger logger = LoggerFactory.getLogger(APIStatus.class);

    @Autowired
    StatusService getStatus;

    // Get status of all plants
    @GetMapping("/all")
    public ResponseEntity<?> allPlants() {
        List<Status> statuses = getStatus.getAll();
        if (statuses.isEmpty()) {
            logger.warn("No plants found!");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        logger.debug("Got all statuses");
        return new ResponseEntity<List<Status>>(statuses, HttpStatus.OK);
    }

    // ------------------------- Get status by plant ----------------------------------------------------

    // Get status for one plant for all days
    @GetMapping("/plant/{id}")
    public ResponseEntity<?> getPlantAll(@PathVariable("id") String plant_id) {
        logger.debug("Get status for plant with id {}", plant_id);
        List<Status> status = getStatus.getStatusByPlantId(plant_id);
        if (status == null) {
            logger.warn("Cannot find status for plant with id {} !", (status.get(0)).getPlant().getId());
            return new ResponseEntity("Plant with ID " + plant_id + " does not exist!", HttpStatus.NOT_FOUND);
        }
        logger.debug("Found status for plant " + (status.get(0)).getPlant().getName());
        return new ResponseEntity<List<Status>>(status, HttpStatus.OK);
    }

    // Get status of plant for today
    @GetMapping("/today/{id}")
    public ResponseEntity<?> getCurrentStatus(@PathVariable("id") String plant_id) {
        logger.info("Get today's status for plant {}", plant_id);
        Status status = getStatus.getPlantStatus(plant_id);
        if (status == null){
            logger.warn("No data found for plant with id: " + plant_id + " for today!");
            return new ResponseEntity("No data found for plant with id: " + plant_id + " for today!", HttpStatus.NOT_FOUND);
        }
        logger.debug("Found data for plant " + status.getPlant().getName() + " on day " + status.getDay());
        return new ResponseEntity<Status>(status, HttpStatus.OK);
    }

    // Get status of plant for current month
    @GetMapping("/month/{id}")
    public ResponseEntity<?> getPlantMonth(@PathVariable("id") String plant_id) {
        logger.info("Get monthly status for plant {}", plant_id);
        List<Status> status = getStatus.getMonthlyPlantStatus(plant_id);
        if (status.isEmpty()){
            logger.warn("No data found for plant with id: " + plant_id + " for this month!");
            return new ResponseEntity("No data found for plant with id: " + plant_id + " for this month!", HttpStatus.NOT_FOUND);
        }
        logger.debug("Found data for plant with id: " + plant_id + " for this month!" );
        return new ResponseEntity<List<Status>>(status, HttpStatus.OK);
    }

    // ------------------------------- Status for all plants by day ------------------------------------

    // Get status for all plants for given day
    // Require date format: "2016-08-16"
    @GetMapping("/day/{date}")
    public ResponseEntity<?> getStatusDay(@PathVariable("date") String date) {
        logger.info("Get data for all plants on " + date);
        List<Status> statuses = getStatus.getDay(date);
        if (statuses.isEmpty()){
            logger.error("No data found for any plants on " + date);
            return new ResponseEntity("No data found for any plants on "+date+"!", HttpStatus.NOT_FOUND);
        }
        logger.debug("Found data for date "+ date);
        return new ResponseEntity<List<Status>>(statuses, HttpStatus.OK);
    }

    // ------------------------------------------- Status for all plants by Month -------------------------------------------------

    // Get status for all plants for given month
    // Require date format: "2016-08"
    @GetMapping("/month/{mnt}")
    public ResponseEntity<?> getDataMonth(@PathVariable("mnt") String mnt) {
        logger.debug("Get data for month ", mnt);
        List<Status> month = getStatus.getMonth(mnt);
        if (month.isEmpty()) {
            logger.warn("No data for month {}/{}!", mnt);
            return new ResponseEntity("No data for month " + mnt + " found!", HttpStatus.NOT_FOUND);
        }
        logger.debug("Found data for month ", mnt);
        return new ResponseEntity<List<Status>>(month, HttpStatus.OK);
    }

    // ------------------------------------ PUT ------------------------------------------

    // Change WateringStatus (isWet) of plant today
    @PutMapping("/update")
    public ResponseEntity<?> changeStatus(@RequestBody Status status){
        logger.info("Set status of plant {} to {} for day {}", status.getPlant(), status.isWet(), status.getDay());
        Status test = getStatus.getStatusBySid(status.getSid());
        if (test == null){
            logger.error("Could not change status of plant {} to {}. Status not found!", status.getPlant(), status.isWet());
            return new ResponseEntity("Could not change status to " + status.isWet() + " for plant " + status.getPlant() + " on day " + status.getDay() + " !", HttpStatus.NOT_MODIFIED);
        }
        getStatus.updateStatus(status);
        logger.debug("Changed status of plant " + status.getPlant() + " to isWet=" + status.isWet() + " for day " + status.getDay());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
