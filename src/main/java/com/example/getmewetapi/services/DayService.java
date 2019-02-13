package com.example.getmewetapi.services;

import com.example.getmewetapi.models.Day;
import com.example.getmewetapi.repositories.DayRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service("DayService")
public class DayService {

    public static final Logger logger = LoggerFactory.getLogger(DayService.class);

    private DayRepository dayRepository;

    @Autowired
    public DayService(DayRepository dayRepository){
        this.dayRepository = dayRepository;
    }

    public List<Day> getAll(){
        return dayRepository.findAll();
    }

    public List<Day> getDaysInMonth(int year, int month){
        List<Day> thisMonth = new ArrayList<Day>();
        for (Day day: getAll()){
            if (day.getYear() == year && day.getMonth() == month) {
                logger.debug("Found day: " + day + " - for: " + month + "/" + year);
                thisMonth.add(day);
            }
        }
        if (thisMonth.isEmpty()){
            logger.debug("No days found!");
            return null;
        } else{
            logger.debug("Days found!");
            return thisMonth;
        }
    }

    public Day getDayByDate(LocalDate date){
        return dayRepository.findByDate(date);
    }

    public Day getToday(){
        LocalDate today = LocalDate.now();
        for (Day day: getAll()){
            if (day.getDate() == today) {
                logger.debug("Found today's date in db: " + day);
                return day;
            }
        }
        logger.debug("Cannot find today's date in db - Date: " + today);
        return null;
    }

    public int getMonth(){
        LocalDate today = LocalDate.now();
        int curmonth = today.getMonthValue();
        logger.debug("This month is " + curmonth);
        return curmonth;
    }

    public int getYear(){
        LocalDate today = LocalDate.now();
        int curyear = today.getYear();
        logger.debug("This year is " + curyear);
        return curyear;
    }

    public void createDay(Day day){
        int index = getAll().indexOf(getDayByDate(day.getDate()));
        if (index == -1) {
            dayRepository.save(day);
            logger.debug("Added day " + day);
            return;
        }
        logger.debug("Day already exists! No action taken! " + day);
    }

    public void updateDay(Day day){
        logger.debug("Updating day day " + getDayByDate(day.getDate()));
        int index = getAll().indexOf(getDayByDate(day.getDate()));
        if (index != -1){
            dayRepository.save(day);
            logger.debug("Updated day to " + day);
            return;
        }
        logger.debug("Day not found! " + day);
    }

    public void deleteDayById(int id){
        for (Iterator<Day> iterator = getAll().iterator(); iterator.hasNext();) {
            Day day = iterator.next();
            if (day.getId() == id) {
                logger.debug("Deleted day " + day);
                dayRepository.delete(day);
                return;
            }
        }
        logger.debug("Day not found! ID: " + id);
    }

}
