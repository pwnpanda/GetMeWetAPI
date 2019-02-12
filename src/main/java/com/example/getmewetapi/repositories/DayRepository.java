package com.example.getmewetapi.repositories;

import com.example.getmewetapi.models.Day;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("dayRepository")
public interface DayRepository extends CrudRepository<Day, Integer> {
}
