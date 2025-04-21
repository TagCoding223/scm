package com.scm.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.scm.entities.Contact;
import com.scm.entities.User;

public interface ContactService {
    Contact save(Contact contact);
    Contact update(Contact contact);
    List<Contact> getAll();
    Contact getById(String id);
    void delete(String id);

    Page<Contact> searchByName(User user,String name, int size, int page, String sortBy, String order);
    Page<Contact> searchByEmail(User user,String email, int size, int page, String sortBy, String order);
    Page<Contact> searchByPhoneNumber(User user,String phoneNumber, int size, int page, String sortBy, String order);

    List<Contact> getByUserId(String userId);
    Page<Contact> getByUser(User user,int page, int size, String sortBy, String direction);
}
