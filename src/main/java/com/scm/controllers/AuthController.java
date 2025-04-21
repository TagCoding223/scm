package com.scm.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.entities.User;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    // verify email
    @RequestMapping("/verify-email")
    public String verifyEmail(@RequestParam("token") String token, HttpSession session) {
        logger.info("Email verification handler called.");
        User userByEmailToken = userService.getUserByEmailToken(token);
        logger.info("userByEmailToken: {}",userByEmailToken);
        if (userByEmailToken != null) {
            
            // user exist in db
            if (userByEmailToken.getEmailToken().equals(token)) {
                // no sense for this if direct use below code
                userByEmailToken.setEmailVerified(true);
                userByEmailToken.setEnabled(true);

                // update in db
                userService.updateUser(userByEmailToken); // if we call save method then we get null because we deny duplicate entry

                // set success message
                session.setAttribute("message",
                        Message.builder().content("Email is verified, Now your account is enabled.")
                                .type(MessageType.green).build());

                return "success_page";
            }
        }

        // set failure message
        session.setAttribute("message",
                Message.builder().content("Something in Wrong, your email not verified yet!").type(MessageType.red)
                        .build());

        return "error_page";
    }
}
