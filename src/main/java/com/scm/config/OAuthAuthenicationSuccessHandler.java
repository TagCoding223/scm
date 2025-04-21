package com.scm.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.scm.entities.Providers;
import com.scm.entities.User;
import com.scm.helpers.AppConstants;
import com.scm.repositories.UserRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenicationSuccessHandler implements AuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(OAuthAuthenicationSuccessHandler.class);

    @Autowired
    private UserRepo userRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        logger.info("OAuthAuthenicationSuccessHandler");

        // now identify the provider
        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        String authorizedClientRegistrationId = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
        logger.info("\nSignup with: " + authorizedClientRegistrationId);

        DefaultOAuth2User oauth2User = (DefaultOAuth2User) authentication.getPrincipal();

        // create a blank user with some default/and comman values
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setRoleList(List.of(AppConstants.ROLE_USER));
        user.setEnabled(true);
        user.setEmailVerified(true);
        user.setPhoneNumber("00000000");

        // now check which attributes provided by provider

        // logger.info(oauth2User.getName());
        // oauth2User.getAttributes().forEach((key,value)->{
        // logger.info("{} => {}",key,value);
        // });
        // logger.info(oauth2User.getAuthorities().toString());

        if (authorizedClientRegistrationId.equalsIgnoreCase("google")) {
            // signup with google and oprerate on google attributes

            // get google provided attributes
            String email = oauth2User.getAttribute("email").toString();
            String name = oauth2User.getAttribute("name").toString();
            String picture = oauth2User.getAttribute("picture").toString();

            // create user and save in database
            user.setEmail(email);
            user.setName(name);
            user.setProfilePic(picture);
            // user.setPassword(UUID.randomUUID().toString() + oauth2User.getName().toString());
            // user.setPassword(name+"@123");
            logger.info(name+"@123");
            user.setPassword(new SecurityConfig().passwordEncoder().encode(name+"@123"));
            user.setProvider(Providers.GOOGLE);
            user.setProviderUserId(oauth2User.getName());
            user.setAbout("This account is created using google.");

        } else if (authorizedClientRegistrationId.equalsIgnoreCase("github")) {
            // signup with github and oprerate on github attributes
            logger.info("\n coming here");
            // get github provided attributes
            String email = oauth2User.getAttribute("email") != null ? oauth2User.getAttribute("email").toString()
                    : oauth2User.getAttribute("login").toString() + "@github.com";
            String picture = oauth2User.getAttribute("avatar_url").toString();
            String name = oauth2User.getAttribute("login").toString();
            String providerUserId = oauth2User.getName();

            user.setEmail(email);
            user.setProfilePic(picture);
            user.setName(name);
            user.setProviderUserId(providerUserId);
            user.setProvider(Providers.GITHUB);
            // user.setPassword(UUID.randomUUID().toString() + oauth2User.getName().toString());
            // user.setPassword(name+"@123");
            logger.info(name+"@123");
            user.setPassword(new SecurityConfig().passwordEncoder().encode(name+"@123"));
            user.setAbout("This account is created using github.");

        } else if (authorizedClientRegistrationId.equalsIgnoreCase("linkedin")) {
            // signup with linkedin and oprerate on linkedin attributes
        } else if (authorizedClientRegistrationId.equalsIgnoreCase("facebook")) {
            // signup with facebook and oprerate on facebook attributes
        } else {
            logger.warn("\nUnknown signin provider: " + authorizedClientRegistrationId);
        }

        // save user in db
        User isUserExistInDB = userRepo.findByEmail(user.getEmail()).orElse(null);
        if (isUserExistInDB == null) {
            userRepo.save(user);
            logger.info(getClass().getName() + ": User save in db, email: " + user.getEmail());
        }

        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/dashboard");
    }

}
