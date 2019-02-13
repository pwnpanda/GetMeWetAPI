package com.example.getmewetapi.controllers;

import com.example.getmewetapi.models.Day;
import com.example.getmewetapi.services.DayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/day")
public class APIDay {

    public static final Logger logger = LoggerFactory.getLogger(APIDay.class);

    @Autowired
    DayService dayService;


    @GetMapping("/all")
    public ResponseEntity<List<Day>> getAllDays() {
        logger.info("Get all days from API!");
        List<Day> days = dayService.getAll();
        if (days.isEmpty()) {
            logger.debug("No days found!");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Day>>(days, HttpStatus.OK);
    }

    @GetMapping("/{date}")
    public ResponseEntity<Day> getDay(@PathVariable LocalDate date) {
        logger.info("Get Day with date" + date);
        Day day = dayService.getDayByDate(date);
        if (day == null) {
            logger.debug("No day found!");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        logger.debug("Found day " + day);
        return new ResponseEntity<Day>(day, HttpStatus.OK);
    }

    // ------------------------------------ PUT ------------------------------------------
    @PutMapping("/update")
    public ResponseEntity<?> updateDay(@RequestBody Day day) {
        logger.debug("Change day with date: " + day.getDate());
        Day pl = dayService.getDayByDate(day.getDate());
        if (pl == null) {
            logger.debug("No day found!");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        dayService.updateDay(day);
        logger.debug("Updated day " + day);
        return new ResponseEntity<Day>(day, HttpStatus.OK);
    }

    // ------------------------------------ POST ------------------------------------------
    // TODO handle and create status for today and all coming days
    @PostMapping("/create")
    public ResponseEntity<?> createDay(@RequestBody Day day) {
        logger.debug("Day " + day);
        Day us = dayService.getDayByDate(day.getDate());
        if (us != null) {
            // Already exists
            logger.debug("Day already exists! Day " + day);
            return new ResponseEntity<String>("Error when creating day. Please try again.", HttpStatus.NOT_MODIFIED);
        }
        dayService.createDay(day);
        logger.debug("Created day " + day);
        return new ResponseEntity<Day>(day, HttpStatus.CREATED);
    }

    // ------------------------------------ DEL ------------------------------------------

    @DeleteMapping("/{date}")
    public ResponseEntity<String> delDay(@PathVariable LocalDate date) {
        logger.info("Delete day with date" + date);
        Day day = dayService.getDayByDate(date);
        if (day == null) {
            logger.debug("No day found! Date: " + date);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        String datestr = day.getDate().toString();
        dayService.deleteDayById(day.getId());
        logger.debug("Deleted day " + datestr);
        return new ResponseEntity<String>("Deleted day " + datestr, HttpStatus.OK);
    }


}
