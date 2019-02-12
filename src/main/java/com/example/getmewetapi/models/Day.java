package com.example.getmewetapi.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "day")
public class Day implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "day_id", unique = true, nullable = false)
    private int id;


    private final Date date;
    private final Integer day;
    private final Integer month;
    private final Integer year;
    @ElementCollection
    private Set<Status> statuses = new HashSet<Status>(0);

    public Day(Date date, Set<Status> statuses){
        this.date = date;
        this.day = date.toLocalDate().getDayOfMonth();
        this.month = date.toLocalDate().getMonthValue();
        this.year = date.toLocalDate().getYear();
        this.statuses = statuses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "day_id", targetEntity = Status.class)
    public Set<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(Set<Status> statuses) {
        this.statuses = statuses;
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

    @Override
    public String toString() {
        return String.format("[ID: {}] Day: {} Month: {} Year: {}", id, date, month, year);
    }
}
