package com.scm.config;

import java.io.IOException;

// import org.springframework.dao.DataIntegrityViolationException;
// import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.scm.helpers.Message;
import com.scm.helpers.MessageType;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AuthFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        HttpSession session = request.getSession();

        if (exception instanceof DisabledException) {
            // user account is disabled

            session.setAttribute("message",
                    Message.builder().content("User is disabled!, varification link is sent on your email id.")
                            .type(MessageType.red).build());

            response.sendRedirect("/login");
        } 
        // else if (exception.getCause() instanceof DuplicateUserException) {
        //     // when user already exist (custom exception)
        //     session.setAttribute("message",
        //             Message.builder().content("User already exist!").type(MessageType.blue).build());
        //     response.sendRedirect("/login");
        // } else if (exception.getCause() instanceof DataIntegrityViolationException) {
        //     session.setAttribute("message",
        //             Message.builder().content("Duplicate entry detected!").type(MessageType.red).build());
        //     response.sendRedirect("/login");
        // } else if (exception instanceof BadCredentialsException) {
        //     // when username and password worng
        //     session.setAttribute("message",
        //             Message.builder().content("Incorrect username or password.").type(MessageType.red).build());
        //     response.sendRedirect("/login");
        // } 
        else {
            response.sendRedirect("/login?error=true");
        }

    }

}
