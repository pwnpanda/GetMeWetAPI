package com.example.getmewet.repositories;

import com.example.getmewet.models.Day;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("dayRepository")
public interface DayRepository extends CrudRepository<Day, Integer> {
}
