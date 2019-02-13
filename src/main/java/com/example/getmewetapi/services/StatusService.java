package com.example.getmewetapi.services;

import com.example.getmewetapi.models.Day;
import com.example.getmewetapi.models.Plant;
import com.example.getmewetapi.models.Status;
import com.example.getmewetapi.models.StatusAssignmentKey;
import com.example.getmewetapi.repositories.StatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service("StatusService")
public class StatusService {

    public static final Logger logger = LoggerFactory.getLogger(StatusService.class);

    private static final ZoneId zone = ZoneId.of("JST");
    //If norway change to Europe/Oslo

    @Autowired
    PlantService plantService;
    @Autowired
    DayService calendar;

    private StatusRepository statusRepo;

    @Autowired
    public StatusService(StatusRepository statusRepo){
        this.statusRepo = statusRepo;
    }

    public List<Status> getAll() {
        return statusRepo.findAll();
    }

    public Status getStatusBySid(StatusAssignmentKey sid){
        return statusRepo.findBySid(sid);
    }

    public List<Status> getStatusByPlantId(String id){
        logger.debug("Finding statuses for plant with ID " + id);
        Plant plant = plantService.getPlantByPath(id);
        if (plant == null){
            logger.warn("No plant found for id " + id);
            return null;
        }
        logger.debug("Found statuses for plant with ID " + id);
        return plant.getStatuses();
    }

    public Status getPlantStatus(String id){
        LocalDate today = LocalDate.now(zone);
        List<Status> statuses = getStatusByPlantId(id);
        for (Status status: statuses){
            if (status.getDay().getDate().equals(today)){
                logger.debug("Found status for date " + today);
                return status;
            }
        }
        logger.warn("No status found for plant with id " + id + " for date " + today);
        return null;
    }

    public List<Status> getMonthlyPlantStatus(String id){
        List<Status> end = new ArrayList<Status>(0);
        YearMonth month = YearMonth.now(zone);
        List<Status> statuses = getStatusByPlantId(id);
        for (Status status: statuses){
            if (status.getDay().getMnt().equals(month)){
                logger.debug("Found status for day " + status.getDay().getDate());
                end.add(status);
            }
        }
        if (end.isEmpty()){
            logger.warn("No status found for plant with id " + id + " for month " + month);
            return null;
        }
        logger.debug("Found " + end.size() + " elements for month " + month);
        return end;
    }

    public List<Status> getDay(String date){
        LocalDate givenDate = LocalDate.parse(date);
        for (Day day: calendar.getAll()){
            if (day.getDate().equals(givenDate)){
                logger.debug("Found status for day " + givenDate);
                return day.getStatuses();
            }
        }
        logger.warn("No data found for day " + givenDate);
        return null;
    }

    public List<Status> getMonth(String date){
        List<Status> end = new ArrayList<Status>(0);
        YearMonth month = YearMonth.parse(date);
        for (Status status: getAll()){
            if (status.getDay().getMnt().equals(month)){
                logger.debug("Found status for day " + status.getDay().getDate());
                end.add(status);
            }
        }
        if (end.isEmpty()){
            logger.warn("No statuses found for month " + month);
            return null;
        }
        logger.debug("Found " + end.size() + " elements for month " + month);
        return end;
    }

    public void updateStatus(Status status){
        logger.debug("Updating status " + status.getSid());
        int index = getAll().indexOf(statusRepo.findBySid(status.getSid()));
        if (index != -1){
            statusRepo.save(status);
            logger.debug("Updated status to " + status);
            return;
        }
        logger.debug("Status not found! " + status);
    }

}
