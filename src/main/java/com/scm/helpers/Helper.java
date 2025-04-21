package com.scm.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.authentication.OAuth2LoginAuthenticationProvider;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {
    
    public static String getEmailOfLoggedInUser(Authentication authentication){
        
        Logger logger = LoggerFactory.getLogger(Helper.class);
        String username = "";
        
        if (authentication instanceof OAuth2LoginAuthenticationProvider) {
            OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            String clientId = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
            OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();

            if (clientId.equalsIgnoreCase("google")) {
                // how to get and display user detils in /user/dashboard, if login with google
                logger.info("\nGetting email from google.");
                username = oAuth2User.getAttribute("email").toString();

            }else if(clientId.equalsIgnoreCase("github")){
                // how to get and display user detils in /user/dashboard, if login with github
                logger.info("\nGetting email from github.");
                username = oAuth2User.getAttribute("email") != null ? oAuth2User.getAttribute("email").toString() : oAuth2User.getAttribute("login").toString()+"github.com";
            }else{
                logger.info("\nUnkown client.");
            }
            
        }else{
            // means user login with email and password
            // how to get and display user detils in /user/dashboard, if login with email and password
            logger.info("\nUse email and password for login and getting email in dashboard (Getteing data from database).");
            username = authentication.getName().toString();
        }

        return username;
    }

    public static String getLinkForEmailVerification(String emailToken){
        String link = AppConstants.DOMAIN_NAME+"auth/verify-email?token="+emailToken;
        return link;
    }
}
