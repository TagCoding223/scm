package com.scm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User,String>{

    // custom finder methods, its implemetation jpa define automatically
    Optional<User> findByEmail(String email); // spring data jpa automatically provide implemetation, we dosen't need to write query, spring jpa use a patten of method name which find by a field like findBy* , where * Replace with field name (in camelCase)
    Optional<User> findByEmailAndPassword(String email,String password);

    Optional<User> findByEmailToken(String emailToken);
}
