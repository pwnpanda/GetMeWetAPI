package com.example.getmewetapi;

import com.example.getmewetapi.models.MyUser;
import com.example.getmewetapi.services.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EntityScan("com.example.getmewetapi.*")
public class GetMeWetAPIApplication {

    @Autowired
    UserService userService;
    /*@Autowired
    DayService calendar;
    @Autowired
    PlantService plantService;
    @Autowired
    StatusService statusService;*/

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(GetMeWetAPIApplication.class, args);
    }

    @Bean
    InitializingBean sendDatabase() {
        return () -> {

            // ------------------- Users ---------------------
            MyUser user = new MyUser();
            user.setUsername("Robin");
            user.setPassword("admin");
            userService.saveUser(user);
            user = new MyUser();
            user.setUsername("Erika");
            user.setPassword("notAdmin");
            userService.saveUser(user);

            /*// ------------------- Dates ----------------------
            LocalDate start = LocalDate.now();
            LocalDate end = start.plusYears(5);
            while (start.isBefore(end)){
                Day d = new Day(start);
                calendar.createDay(d);
                start = start.plusDays(1);
            }
            // ------------------ Plants ----------------------
            Plant plant = new Plant("Test1", "***");
            plantService.createPlant(plant);
            plant = new Plant("test2","---");
            plantService.createPlant(plant);
            //------------------- Status ----------------------
            */
        };
    }
}

