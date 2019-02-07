package com.example.getmewet.services;

import com.example.getmewet.models.Day;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service("DayService")
public class DayService {

    private static final AtomicInteger counter = new AtomicInteger();

    private static List<Day> days;

    public List<Day> getDaysInMonth(int year, int month){
        List<Day> thisMonth = new ArrayList<Day>();
        for (Day day: days){
            if (day.getYear() == year && day.getMonth() == month) {
                thisMonth.add(day);
            }
        }
        if (thisMonth.isEmpty()){
            return null;
        } else{
            return thisMonth;
        }
    }

    public Day getToday(){
        Date today = new Date(System.currentTimeMillis());
        for (Day day: days){
            if (day.getDate() == today) {
                return day;
            }
        }
        return null;
    }

    public int getMonth(){
        Date today = new Date(System.currentTimeMillis());
        return today.toLocalDate().getMonthValue();
    }

    public int getYear(){
        Date today = new Date(System.currentTimeMillis());
        return today.toLocalDate().getYear();
    }
}
