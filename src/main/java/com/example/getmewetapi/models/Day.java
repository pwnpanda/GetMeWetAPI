package com.example.getmewetapi.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "day")
public class Day implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "day_id", unique = true, nullable = false)
    private int id;


    private final LocalDate date;
    private final YearMonth mnt;
    private final Integer day;
    private final Integer month;
    private final Integer year;
    @ElementCollection
    private List<Status> statuses = new ArrayList<Status>(0);

    public Day(LocalDate date, List<Status> statuses){
        this.date = date;
        this.mnt = YearMonth.from(date);
        this.day = date.getDayOfMonth();
        this.month = date.getMonthValue();
        this.year = date.getYear();
        this.statuses = statuses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "day_id", targetEntity = Status.class)
    public List<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }

    public LocalDate getDate() {
        return date;
    }

    public YearMonth getMnt() { return mnt; }

    public String getWeekDay() {
        return date.getDayOfWeek().toString();
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

    @Override
    public String toString() {
        return String.format("[ID: {}] Day: {} Month: {} Year: {}", id, date, month, year);
    }
}
