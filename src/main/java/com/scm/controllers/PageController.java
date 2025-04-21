package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.helpers.SessionHelper;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("Home Page Handler");
        model.addAttribute("name", "Vishal Chouhan");
        model.addAttribute("google", "https://www.google.com");
        model.addAttribute("con", false);
        model.addAttribute("para",
                "Lorem ipsum dolor, sit amet consectetur adipisicing elit. Porro expedita veniam incidunt aliquid aperiam optio corporis id! Provident blanditiis quaerat dolor soluta eligendi, dolorem pariatur? Blanditiis ad expedita eaque! Rerum?");
        return "home";
    }

    // about page
    @RequestMapping("/about")
    public String aboutPage() {
        System.out.println("About Page.");
        return "about";
    }

    // services
    @RequestMapping("/services")
    public String serviecesPage() {
        System.out.println("services Page.");
        return "services";
    }

    // contact
    @RequestMapping("/contact")
    public String contactPage() {
        System.out.println("contact Page.");
        return "contact";
    }

    // login
    @RequestMapping("/login")
    public String loginPage() {
        System.out.println("login Page.");
        return "login";
    }

    // this endpoint use to handle logged out and worng username and password
    // message
    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout,
            Model model) {
        if (error != null) {
            SessionHelper.addMessage("Invalid username and password.");
        } else if (logout != null) {
            SessionHelper.addMessage("You have been logged out.");
        }
        return "login"; // Replace with the actual view name
    }

    // register
    @RequestMapping("/register")
    public String registerPage(Model model) {
        System.out.println("register Page.");

        UserForm userForm = new UserForm();
        // userForm.setName("Vishal"); pasing defualt value
        model.addAttribute("userForm", userForm);
        return "register";
    }

    // do-register (register/signup process )
    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult,
            HttpSession session) {
        System.out.println("do-register Page.");
        System.out.println("Processing Registration.");

        // recevie data from register view
        System.out.println(userForm);

        // validate form data
        if (bindingResult.hasErrors()) {
            return "register";
        }

        // userservice
        // userFrom -> User

        // skip builder because default value not saved , if we use this wy then we need
        // to include @Builder in User class.
        // User user = User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .about(userForm.getAbout())
        // .phoneNumber(userForm.getPhoneNumber())
        // .profilePic("https://img.freepik.com/free-vector/blue-circle-with-white-user_78370-4707.jpg?t=st=1741882756~exp=1741886356~hmac=7a12d94f4e44e1e3e853e9806a23537176c7544c45322b2d651084badf473429&w=740")
        // .build();

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setEnabled(false);
        user.setProfilePic(
                "https://img.freepik.com/free-vector/blue-circle-with-white-user_78370-4707.jpg?t=st=1741882756~exp=1741886356~hmac=7a12d94f4e44e1e3e853e9806a23537176c7544c45322b2d651084badf473429&w=740");

        User savedUser = userService.saveUser(user);

        System.out.println("user saved: " + savedUser);
        // validate data

        if (savedUser==null) {
            session.setAttribute("message", 
                Message.builder().content("User already exist!").type(MessageType.blue).build()
            );
            return "redirect:/login";
        }

        // add the message
        Message message = Message.builder().content("Registraction Successful").type(MessageType.green).build();
        session.setAttribute("message", message);

        return "redirect:/register";
    }
}
