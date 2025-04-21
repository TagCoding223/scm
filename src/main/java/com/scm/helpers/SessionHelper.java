package com.scm.helpers;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;

@Component
public class SessionHelper {

    public static void addMessage(String m){
        try {
            System.out.println("adding message from session.");
            HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
            Message message = Message.builder().content(m).type(MessageType.red).build();
            session.setAttribute("message", message);
            System.out.println("Message added.");
            System.out.println(session.getAttribute("message"));
        } catch (Exception e) {
            System.out.println("Error in SessionHelper: "+e);
            e.printStackTrace();
        }
    }

    public static void removeMessage(){
        try {
            System.out.println("Removing message from session.");
            HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
            session.removeAttribute("message");
        } catch (Exception e) {
            System.out.println("Error in SessionHelper: "+e);
            e.printStackTrace();
        }
    }

}
