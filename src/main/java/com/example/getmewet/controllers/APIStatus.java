/*package com.example.getmewet.controllers;

import com.example.getmewet.models.Day;
import com.example.getmewet.repositories.DayRepository;
import com.example.getmewet.repositories.PlantRepository;
import com.example.getmewet.repositories.StatusService;
import com.example.getmewet.util.CustomErrorType;
import com.example.getmewet.models.Plant;

import com.sun.org.apache.xerces.internal.impl.dv.xs.MonthDV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/status")
public class APIPlantStatus {
    public static final Logger logger = LoggerFactory.getLogger(APIController.class);
    public int mntNr = LocalDateTime.now().getMonthValue();
    public int dayNr = LocalDateTime.now().getDayOfMonth();

    @Autowired
    PlantRepository plantService;
    @Autowired
    DayRepository calendar;
    @Autowired
    StatusService getStatus;

    // Get list of plants
    @RequestMapping("/plants", method = RequestMethod.GET)
    public ResponseEntity<List<Plant>> allPlants() {
        List<Plant> plants = plantService.getPlants();
        if (plants.isEmpty()) {
            logger.warn("No plants found!");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        logger.info("Got all plants");
        return new ResponseEntity<List<Plant>>(plants, HttpStatus.OK);
    }

    // Get data about one plant
    @RequestMapping("/plant/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPlant(@PathVariable("id") int id) {
        logger.info("Get plant with id {}", id);
        Plant plant = plantService.findById(id);
        if (plant == null) {
            logger.error("Plant with id {} does not exist!", id);
            return new ResponseEntity(new CustomErrorType("Plant with ID " + id + " does not exist!"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Plant>(plant, HttpStatus.OK);
    }


    // Get status for given month
    @RequestMapping("/month/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getDataMonth(@PathVariable("id") int id) {
        logger.info("Get data for month {}", id);
        List<Day> month = getStatus.getMonth(id);
        if (month.isEmpty()) {
            logger.error("No data for month {}!", id);
            return new ResponseEntity(new CustomErrorType("No data for month " + Month.of(id).toString() + " found!"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Day>>(month, HttpStatus.OK);
    }

    // Get status for this month
    @RequestMapping("/month", method = RequestMethod.GET)
    public ResponseEntity<List<Day>> getCurMonth() {
        logger.info("Get data for current month");
        List<Day> month = getStatus.getMonth(mntNr);
        if (month.isEmpty()) {
            logger.error("No data found for this month!");
            return new ResponseEntity(new CustomErrorType("No data found for this month!"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Day>>(month, HttpStatus.OK);
    }

    // Get status for all plants for day
    @RequestMapping("month/{mnt}/day/{day}", method = RequestMethod.GET)
    public ResponseEntity<?> getStatusDay(@RequestParam("mnt") int mnt, @RequestParam("day") int day) {
        logger.info("Get data for all plants on {} {}", Month.of(mnt).toString(), day);
        HashMap<Plant, Boolean> plants = getStatus.getDay(mnt, day);
        if (plants.isEmpty()){
            logger.error("No data found for any plants on {} {}", Month.of(mnt).toString(), day);
            return new ResponseEntity(new CustomErrorType("No data found for any plants on " +Month.of(mnt).toString() + " " + day + " !"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<HashMap<Plant, Boolean>>(plants, HttpStatus.OK);
    }

    // Get status for all plants for today
    @RequestMapping("/day", method = RequestMethod.GET)
    public ResponseEntity<?> getStatusToday() {
        logger.info("Get data for all plants for today");
        HashMap<Plant, Boolean> plants = getStatus.getDay(mntNr, dayNr);
        if (plants.isEmpty()){
            logger.error("No data found for any plants for today");
            return new ResponseEntity(new CustomErrorType("No data found for any plants for today !"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<HashMap<Plant, Boolean>>(plants, HttpStatus.OK);
    }
    // Get status of plant for month
    @RequestMapping("/status/month/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getMonthStatus(@RequestParam("id" int id)) {
        logger.info("Get monthly status for plant {}", id);
        List<Boolean> status = getStatus.getMonthlyPlantStatus(id);
        if (status.isEmpty()){
            logger.error("No data found for plant with id: " + id + " for this month!");
            return new ResponseEntity(new CustomErrorType("No data found for plant with id: " + id + " for this month!"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Boolean>>(status, HttpStatus.OK);
    }

    // Get status of plant for today
    @RequestMapping("/status/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCurrentStatus(@RequestParam("id" int id)) {
        logger.info("Get today's status for plant {}", id);
        Boolean status = getStatus.getPlantStatus(id);
        if (status){
            logger.error("No data found for plant with id: " + id + " for today!");
            return new ResponseEntity(new CustomErrorType("No data found for plant with id: " + id + " for today!"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Boolean>(status, HttpStatus.OK);
    }
    // Change status of plant today
    // TODO FIX!
    @RequestMapping("/status/{id}/{status}", method = RequestMethod.PUT)
    public ResponseEntity<?> changeStatus(@RequestParam("id") long id, @RequestParam("status") boolean status){
        logger.info("Set status of plant {} to {}", id, status);
        Boolean success = getStatus.setStatus(id, status);
        if (!success){
            logger.error("Could not change status of plant {} to {}", id, status);
            return new ResponseEntity(new CustomErrorType("Could not change status to " + status + " for plant " + id + "!"), HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<Boolean>(success, HttpStatus.OK);
    }
}
*/