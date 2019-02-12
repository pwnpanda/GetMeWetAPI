/*package com.example.getmewet.models;

import org.hibernate.annotations.NaturalId;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ForeignKey
    private final Plant plant;
    @ForeignKey
    private final Day day;
    private boolean isWet;

    public Status(Plant plant, Day day){
        this.day = day;
        this.plant = plant;
        this.isWet = false;
    }

    public Plant getPlant() {
        return plant;
    }

    public Day getDay() {
        return day;
    }

    public boolean isWet() {
        return isWet;
    }

    public void setWet(boolean wet) {
        this.isWet = wet;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
*/