package com.example.getmewetapi.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@Table(name = "Status")
@AssociationOverrides({
        @AssociationOverride(name = "sid.day", joinColumns = @JoinColumn(name = "day_id")),
        @AssociationOverride(name = "sid.plant", joinColumns = @JoinColumn(name = "plant_id"))
})
public class Status implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private StatusAssignmentKey sid = new StatusAssignmentKey();

    @ManyToOne
    private final Day day;
    @ManyToOne
    private final Plant plant;
    private boolean isWet;

    public Status(Plant plant, Day day){
        this.day = day;
        this.plant = plant;
        this.isWet = false;
    }

    @EmbeddedId
    public StatusAssignmentKey getSid() {
        return sid;
    }

    public void setSid(StatusAssignmentKey sid) {
        this.sid = sid;
    }

    @Transient
    public Plant getPlant() {
        return getSid().getPlant();
    }

    public void setPlant(Plant plant){
        getSid().setPlant(plant);
    }

    @Transient
    public Day getDay() {
        return getSid().getDay();
    }

    public void setDay(Day day){
        getSid().setDay(day);
    }

    public boolean isWet() {
        return isWet;
    }

    public void setWet(boolean wet) {
        this.isWet = wet;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Status that = (Status) o;

        if (!Objects.equals(sid, that.getSid()))    return false;

        return true;
    }

    public int hashCode() {
        return (getSid() != null ? getSid().hashCode() : 0);
    }

    @Override
    public String toString() {
        return "Status  is Watered=" + isWet + " for " + getPlant() + " on " + getDay();
    }
}
