package com.example.getmewetapi.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "plant", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Plant implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plant_id", unique = true, nullable = false)
    private int id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;
    private String picture;
    private String note;
    @ElementCollection
    private List<Status> statuses = new ArrayList<Status>(0);

    public Plant(String name, String picture, List<Status> statuses){
        this.name = name;
        this.picture = picture;
        this.statuses = statuses;
        note = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){   this.name = name;   }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "plant_id",targetEntity = Status.class ,cascade=CascadeType.ALL)
    public List<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }

    public Plant getPlant(){
        return this;
    }

    @Override
    public String toString() {
        return "[ID: "+ id + "]Plant name: " + this.name + ", Note: " + this.note;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
