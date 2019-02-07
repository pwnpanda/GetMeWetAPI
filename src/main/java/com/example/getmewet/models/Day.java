package com.example.getmewet.models;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name = "day")
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private final Date date;
    private final Integer day;
    private final Integer month;
    private final Integer year;

    public Day(Date date){
        this.date = date;
        this.day = date.toLocalDate().getDayOfMonth();
        this.month = date.toLocalDate().getMonthValue();
        this.year = date.toLocalDate().getYear();
    }

    public Date getDate() {
        return date;
    }

    public String getWeekDay() {
        return date.toLocalDate().getDayOfWeek().toString();
    }

    public int getDayofMonth() {
        return this.day;
    }

    public int getMonth() {
        return this.month;
    }

    public int getYear() {
        return this.year;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
