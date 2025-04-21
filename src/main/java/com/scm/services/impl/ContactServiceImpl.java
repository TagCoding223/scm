package com.scm.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.helpers.ResourceNotFoundException;
import com.scm.repositories.ContactRepo;
import com.scm.services.ContactService;

@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepo contactRepo;

    @Override
    public Contact save(Contact contact) {
        contact.setId(UUID.randomUUID().toString());
        return contactRepo.save(contact);
    }

    @Override
    public Contact update(Contact contact) {
        Contact oldContact = contactRepo.findById(contact.getId()).orElseThrow(()-> new ResourceNotFoundException("Contact not found!"));

        // update old contact
        oldContact.setName(contact.getName());
        oldContact.setAddress(contact.getAddress());
        oldContact.setEmail(contact.getEmail());
        oldContact.setDescription(contact.getDescription());
        oldContact.setFavorite(contact.isFavorite());
        oldContact.setLinkedinLink(contact.getLinkedinLink());
        oldContact.setWebsiteLink(contact.getWebsiteLink());
        oldContact.setPhoneNumber(contact.getPhoneNumber());
        oldContact.setPicture(contact.getPicture());
        oldContact.setCloudinaryImagePublicId(contact.getCloudinaryImagePublicId());
        // oldContact.setLinks(contact.getLinks());

        return contactRepo.save(oldContact);
    }

    @Override
    public List<Contact> getAll() {
        return contactRepo.findAll();
    }

    @Override
    public Contact getById(String id) {
        return contactRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Contact not found with given id: "+id));
    }

    @Override
    public void delete(String id) {
        Contact contact = contactRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Contact not found with given id: "+id));
        if (contact!=null) {
            contactRepo.delete(contact);
        }
    }

    @Override
    public List<Contact> getByUserId(String userId) {
        return contactRepo.findByUserId(userId);
    }

    @Override
    public Page<Contact> getByUser(User user,int page, int size, String sortBy, String direction) {
        Sort sort = direction.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        PageRequest pageable = PageRequest.of(page, size, sort);
        return contactRepo.findByUser(user,pageable);
    }

    @Override
    public Page<Contact> searchByEmail(User user, String email, int size, int page, String sortBy, String order) {
        Sort sort = order.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        PageRequest pageable = PageRequest.of(page, size, sort);
        return contactRepo.findByUserAndEmailContaining(user, email, pageable);
    }

    @Override
    public Page<Contact> searchByName(User user, String name, int size, int page, String sortBy, String order) {
        Sort sort = order.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        PageRequest pageable = PageRequest.of(page, size, sort);
        return contactRepo.findByUserAndNameContaining(user, name, pageable);
    }

    @Override
    public Page<Contact> searchByPhoneNumber(User user, String phoneNumber, int size, int page, String sortBy, String order) {
        Sort sort = order.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        PageRequest pageable = PageRequest.of(page, size, sort);
        return contactRepo.findByUserAndPhoneNumberContaining(user, phoneNumber, pageable);
    }

   

}
