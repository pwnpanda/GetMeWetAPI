package com.example.getmewetapi.services;

import com.example.getmewetapi.models.Plant;
import com.example.getmewetapi.repositories.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service("PlantService")
public class PlantService {

    private static final AtomicInteger counter = new AtomicInteger();

    private PlantRepository plantRepo;

    private static List<Plant> plants;

    /*
    static {
        plants = populate_plants();
    }*/


    @Autowired
    public PlantService(PlantRepository plantRepo){
        this.plantRepo = plantRepo;
    }

    public List<Plant> getAll() {
        return plantRepo.findAll();
    }

    public Plant findById(int id){
        return plantRepo.findById(id);
    }

    public Plant findByName(String name){
        return plantRepo.findByName(name);
    }

    public void createPlant(Plant plant){
        plant.setId(counter.incrementAndGet());
        plants.add(plant);
        System.out.println("Added plant " + plant);
    }

    public void updatePlant(Plant plant){
        System.out.println("Updating plant plant " + findById(plant.getId()));
        int index = plants.indexOf(findById(plant.getId()));
        plants.set(index, plant);
        System.out.println("Updated plant to " + plant);
    }

    public void deletePlantById(int id){
        for (Iterator<Plant> iterator = plants.iterator(); iterator.hasNext();) {
            Plant plant = iterator.next();
            if (plant.getId() == id) {
                iterator.remove();
            }
        }
    }

    /*

    private static List<Plant> populate_Plants(){
        List<Plant> plants = new ArrayList<Plant>();
        plants.add(new Plant("TestPlant1", "NoPic"));
        return plants;
    }*/
}
