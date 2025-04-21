package com.scm.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.entities.User;
import com.scm.helpers.Helper;
import com.scm.services.UserService;

@ControllerAdvice
public class RootController { // this class method accessable for each route

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    // use to save user info in all page
    @ModelAttribute // this attribute call below method/router calling all methods of this controller and pass model to each
    public void addLoggedInUserInformation(Model model, Authentication authentication){

        if (authentication == null) {
            return;
        }

        // getting loggin user details
        String username = Helper.getEmailOfLoggedInUser(authentication);
        logger.info("\nUser logged in: "+username);
        
        // now get user from db
        User user = userService.getUserByEmail(username);
        
        model.addAttribute("loggedInUser", user);
    }
}
