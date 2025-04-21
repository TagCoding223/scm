package com.scm.controllers;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.forms.ContactForm;
import com.scm.forms.ContactSearchForm;
import com.scm.helpers.AppConstants;
import com.scm.helpers.Helper;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.ContactService;
import com.scm.services.ImageService;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // add contact page
    @RequestMapping("/add")
    public String addContactView(Model model) {
        ContactForm contactForm = new ContactForm();
        // contactForm.setName("null");
        // contactForm.setFavorite(true);
        model.addAttribute("contactForm", contactForm);
        return "user/add_contact";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult bindingResult,
            Authentication authentication, HttpSession session) {

        // Getting User
        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);

        // validation
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> logger.info("error: {}", error));
            session.setAttribute("message",
                    Message.builder().content("Please correct the following errors.").type(MessageType.red).build());
            return "user/add_contact";
        }

        

        // ContactForm convert into Contact
        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setFavorite(contactForm.isFavorite());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setUser(user);
        contact.setLinkedinLink(contactForm.getLinkedinLink());
        contact.setWebsiteLink(contactForm.getWebsiteLink());


        // PROCESS CONTACT PICTURE
        if (contactForm.getContactImage() != null && !contactForm.getContactImage().isEmpty()) {
            String fileName = UUID.randomUUID().toString();
            logger.info("File information: {}", contactForm.getContactImage().getOriginalFilename());
            String fileURL = imageService.uploadImage(contactForm.getContactImage(), fileName);
            contact.setPicture(fileURL);
            contact.setCloudinaryImagePublicId(fileName);
        }

        

        // save contact
        contactService.save(contact);

        session.setAttribute("message",
                Message.builder().content("You have successfully added a new contact").type(MessageType.green).build());

        return "redirect:/user/contacts/add";
    }

    // view contacts
    @RequestMapping
    public String viewContacts(
        @RequestParam(value = "page", defaultValue = "0") int page, 
        @RequestParam(value = "size", defaultValue = AppConstants.PAGE_SIZE+"") int size, 
        @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
        @RequestParam(value = "direction", defaultValue = "asc") String direction,
        Model model, 
        Authentication authentication){

        // load the all user contacts
        String emailOfLoggedInUser = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(emailOfLoggedInUser);

        Page<Contact> pageContact = contactService.getByUser(user, page, size, sortBy, direction);

        logger.info("PageContact Content: {}", pageContact.getContent().isEmpty());
        logger.info("Total Pages: {}", pageContact.getTotalPages());
        logger.info("Current Page: {}", pageContact.getNumber());

        model.addAttribute("pageContact", pageContact);
        model.addAttribute("pageSize", AppConstants.PAGE_SIZE);

        model.addAttribute("contactSearchForm", new ContactSearchForm());

        return "user/contacts";
    }

    // search handler
    @RequestMapping("/search")
    public String searchHandler(
        // @RequestParam("field") String field,
        // @RequestParam("keyword") String value,
        @ModelAttribute ContactSearchForm contactSearchForm,
        @RequestParam(value = "size", defaultValue = AppConstants.PAGE_SIZE+"") int size,
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
        @RequestParam(value = "direction", defaultValue = "asc") String direction,
        Model model,
        Authentication authentication
    ){
        logger.info("field {} keyword {}", contactSearchForm.getField(), contactSearchForm.getValue());

        User user = userService.getUserByEmail(Helper.getEmailOfLoggedInUser(authentication));


        Page<Contact> pageContact = null;
        if (contactSearchForm.getField().equalsIgnoreCase("name")) {
            pageContact = contactService.searchByName(user, contactSearchForm.getValue(), size, page, sortBy, direction);
        }else if(contactSearchForm.getField().equalsIgnoreCase("email")){
            pageContact = contactService.searchByEmail(user, contactSearchForm.getValue(), size, page, sortBy, direction);
        }else if(contactSearchForm.getField().equalsIgnoreCase("phoneNumber")){
            pageContact = contactService.searchByPhoneNumber(user, contactSearchForm.getValue(), size, page, sortBy, direction);
        }else{

        }

        model.addAttribute("pageContact", pageContact);
        
        // model.addAttribute("field", contactSearchForm.getField());
        // model.addAttribute("keyword", contactSearchForm.getValue());

        model.addAttribute("contactSearchForm", contactSearchForm);

        model.addAttribute("pageSize", AppConstants.PAGE_SIZE);

        return "user/search";
    }

    // delete a contact
    @RequestMapping("/delete/{contactId}")
    public String deleteContact(
        @PathVariable("contactId") String contactId,
        HttpSession session
    ){
        contactService.delete(contactId);
        session.setAttribute("message",
            Message.builder().content("Contact is Deleted successfully!").type(MessageType.green).build()
        );
        return "redirect:/user/contacts";
    }

    // update contact view
    @GetMapping("/editContactView/{contactId}")
    public String updateContactFormView(
        @PathVariable String contactId,
        Model model
    ) {
        Contact contact = contactService.getById(contactId);

        ContactForm contactForm = new ContactForm();
        contactForm.setName(contact.getName());
        contactForm.setAddress(contact.getAddress());
        contactForm.setEmail(contact.getEmail());
        contactForm.setDescription(contact.getDescription());
        contactForm.setFavorite(contact.isFavorite());
        contactForm.setPhoneNumber(contact.getPhoneNumber());
        contactForm.setWebsiteLink(contact.getWebsiteLink());
        contactForm.setLinkedinLink(contact.getLinkedinLink());
        contactForm.setPicture(contact.getPicture());

        model.addAttribute("contactForm", contactForm);
        model.addAttribute("contactId", contactId);

        return "user/updateContactView";
    }
    
    // update contact processing
    @RequestMapping(value = "/updateContactProcess/{contactId}",method = RequestMethod.POST)
    public String updateContactProcess(
        @PathVariable("contactId") String contactId,
        @Valid @ModelAttribute ContactForm contactForm,
        BindingResult bindingResult,
        Model model,
        HttpSession session
    ){
        // validation
        if (bindingResult.hasErrors()) {
            return "user/updateContactView";
        }

        Contact contact = contactService.getById(contactId);
        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setFavorite(contactForm.isFavorite());
        contact.setId(contactId);
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setLinkedinLink(contactForm.getLinkedinLink());
        

        // process image if a new image choose // there are bug may be (duplicate image check) previous image still aviable on cloud it is not delete
        if (contactForm.getContactImage() != null && !contactForm.getContactImage().isEmpty()) {
            String fileName = UUID.randomUUID().toString();
            String imageURL = imageService.uploadImage(contactForm.getContactImage(), fileName);
            contact.setCloudinaryImagePublicId(fileName);
            contact.setPicture(imageURL);
            contactForm.setPicture(imageURL);
        }

        Contact updatedContact = contactService.update(contact);
        logger.info("Updated Contact: {}",updatedContact);


        session.setAttribute("message", 
            Message.builder().content("Contact Updated Successfully!").type(MessageType.green).build()
        );
        return "redirect:/user/contacts/editContactView/"+contactId;
    }

}
