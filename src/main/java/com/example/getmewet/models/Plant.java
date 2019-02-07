package com.example.getmewet.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "plant")
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private  final String name;
    private String picture;
    private String note;

    public Plant(String name, String picture){
        this.name = name;
        this.picture = picture;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getNote() {
        return note;
    }

    public Plant getPlant(){
        return this;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Plant name: " + this.name + ", Note: " + this.note;
    }
}
