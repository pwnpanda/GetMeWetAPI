package com.example.getmewetapi.models;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
class StatusAssignmentKey implements Serializable {
    private static final long serialVersionUID = 1L;

    private Plant plant;
    private Day day;

    @ManyToOne
    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant){
        this.plant = plant;
    }

    @ManyToOne
    public Day getDay() {
        return day;
    }

    public void setDay(Day day){
        this.day = day;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StatusAssignmentKey that = (StatusAssignmentKey) o;

        if (!Objects.equals(plant, that.plant)) return false;
        if (!Objects.equals(day, that.day)) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (plant != null ? plant.hashCode() : 0);
        result = 31 * result + (day != null ? day.hashCode() : 0);
        return result;
    }
}
