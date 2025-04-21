package com.scm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import com.scm.services.impl.SecurityCustomUserDetailService;

@Configuration
public class SecurityConfig {


    // user create and login using java code with in memory service

    // below method use for testing not for production
    // @Bean
    // public UserDetailsService userDetailsService(){
    //     UserDetails user = User
    //     .withUsername("vishal")
    //     .password("vishal")
    //     .build(); // when we try to login with those user they doesn't use any password encoder

    //     UserDetails user2 = User
    //     .withDefaultPasswordEncoder() // this deprecated method use for only testing purpose
    //     .username("vc")
    //     .password("vc")
    //     .build();

    //     InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user,user2);
    //     return inMemoryUserDetailsManager; // we not use inMemoryUserDetailsManager becuase it store user info in memory not in db
    // }

    @Autowired
    private SecurityCustomUserDetailService userDetailService;

    @Autowired
    private OAuthAuthenicationSuccessHandler aouthSuccessHandler;

    @Autowired
    private AuthFailureHandler authFailureHandler;

    // configuration of authentication provider for spring security
    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        
        // user detail service object
        daoAuthenticationProvider.setUserDetailsService(userDetailService);

        // password encoder
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

    // configuration to define which pages are open and which are closed
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        // url access configuration
        httpSecurity.authorizeHttpRequests(authorize->{
            // authorize.requestMatchers("/home","/register","/services").permitAll();
            // authorize.requestMatchers("/*").permitAll();
            authorize.requestMatchers("/user/**").authenticated();
            // authorize.requestMatchers("/api/**").authenticated(); // making api call private
            authorize.anyRequest().permitAll();


        });

        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        // defualt login form
        // httpSecurity.formLogin(Customizer.withDefaults()); // defualt login page
        httpSecurity.formLogin(formLogin->{
            formLogin.loginPage("/login")
            .loginProcessingUrl("/authenticate");
            formLogin.successForwardUrl("/user/profile");
            // formLogin.failureForwardUrl("/login?error=true");
            formLogin.usernameParameter("email");
            formLogin.passwordParameter("password");

            // use to handle user login failure message due to account disable
            formLogin.failureHandler(authFailureHandler);

        });

        // oauth 2 configuration
        // httpSecurity.oauth2Login(Customizer.withDefaults());
        httpSecurity.oauth2Login(login->{
            login.loginPage("/login");
            login.successHandler(aouthSuccessHandler);
        });

        httpSecurity.logout(logoutForm->{
            logoutForm.logoutUrl("/logout");
            logoutForm.logoutSuccessUrl("/login?logout=true");
        });

        return httpSecurity.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
