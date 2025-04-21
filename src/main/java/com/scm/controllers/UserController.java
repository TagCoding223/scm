package com.scm.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    // @Autowired
    // private UserService userService;

    // private Logger logger = LoggerFactory.getLogger(UserController.class);
    // this controller hold all user related view and hide 
    

    // use to save user info in all page
    // @ModelAttribute // this attribute call below method/router calling all methods of this controller and pass model to each
    // public void addLoggedInUserInformation(Model model, Authentication authentication){
    //     // getting loggin user details
    //     String username = Helper.getEmailOfLoggedInUser(authentication);
    //     logger.info("\nUser logged in: "+username);
        
    //     // now get user from db
    //     User user = userService.getUserByEmail(username);
        
    //     model.addAttribute("loggedInUser", user);
    // }


    // user dashboard page
    @RequestMapping(value = "/dashboard") // by defualt method is get
    public String userDashboard(Authentication authentication,Model model) {
        System.out.println("User Dashboard");

        // // getting loggin user details
        // String username = Helper.getEmailOfLoggedInUser(authentication);
        // logger.info("\nUser logged in: "+username);
        
        // // now get user from db
        // User user = userService.getUserByEmail(username);
        
        // model.addAttribute("loggedInUser", user);


        return "user/dashboard";
    }
    

    @RequestMapping(value = "/profile") // by defualt method is get
    public String userProfile(Authentication authentication,Model model) {
        System.out.println("User Profile");

        // // getting loggin user details
        // String username = Helper.getEmailOfLoggedInUser(authentication);
        // logger.info("\nUser logged in: "+username);

        // // now get user from db
        // User user = userService.getUserByEmail(username);
        
        // model.addAttribute("loggedInUser", user);
        
        return "user/profile";
    }

    // user add contacts page

    // user view contacts

    // user edit contact

    // user delete contact

    // user search contact
}
