package com.example.getmewetapi.repositories;

import com.example.getmewetapi.models.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository("dayRepository")
public interface DayRepository extends JpaRepository<Day, Integer> {
    Day findByDate(LocalDate date);
}
