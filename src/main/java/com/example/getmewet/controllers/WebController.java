package com.example.getmewet.controllers;

import com.example.getmewet.models.User;
import com.example.getmewet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/")
public class WebController {
    private static final String template = "Plant %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    UserService userService;

    // / gives base page
    @RequestMapping(method = GET)
    public String getBase() {
        return "index";
    }

    // /login page
    @RequestMapping(value = "/login", method = GET)
    public String getLogin() {
        return "login";
    }

    @RequestMapping(value="/register", method = GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("reg");
        return modelAndView;
    }

    // /reg.html post registers user
    @RequestMapping(value = "/register", method = POST)
    public ModelAndView getReg(@Valid User user, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        User us = userService.findByUsername(user.getUsername());
        if (us != null) {
            bindingResult.rejectValue("username", "error.user", "An error occurred when registering a user with this username.");
        }
        if (bindingResult.hasErrors()) {
            model.setViewName("reg");
        } else {
            userService.saveUser(user);
            model.addObject("successMessage", "User registered!");
            model.addObject("user", new User());
            model.setViewName("reg");
        }

        return model;
    }

    // /today.html gives today's status
    @RequestMapping(value = "/today", method = GET)
    public String getToday() {
        return "today";
    }

    // /update allows setting today's status (switches for yes/no and save button)
    @RequestMapping(value = "/update", method = GET)
    public String getUpdate() {
        return "update";
    }

    // /listPlants shows all plants (names - links to that plants ID)
    @RequestMapping(value = "/listplants", method = GET)
    public String getAllPlants() {
        return "listplants";
    }

    // /plants/{id} shows data about that plant - Allow for updating fields using API!
    @RequestMapping(value = "/webPlant", method = GET)
    public String getThisPlant() {
        // ???? TODO
        return "plants/";
    }

    // /status show this months status for all plants (day + red dots + green dots)
    @RequestMapping(value = "/status", method = GET)
    public String getStatus() {
        return "status";
    }

    // /addPlant adds another plant to the db (post info and user cred - API!)
    @RequestMapping(value = "/addPlant", method = GET)
    public String addPlant() {
        return "addplant";
    }

    // /removePlant removes the selected plant from the db (post id & user cred - API!)
    @RequestMapping(value = "/removePlant", method = GET)
    public String removePlant() {
        return "removeplant";
    }

    // /logout logs out and returns to /
    @RequestMapping(value = "/logout", method = GET)
    public String logout() {
        return "logout";
    }

}

