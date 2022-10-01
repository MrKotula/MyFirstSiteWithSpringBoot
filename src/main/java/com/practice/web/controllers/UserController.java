package com.practice.web.controllers;

import com.practice.web.models.Role;
import com.practice.web.models.User;
import com.practice.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/user")
    public String user(Principal principal, Model model){
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/user/update")
    public String userHide(Principal principal, Model model){
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user_update";
    }

    @GetMapping("/reg")
    public String reg(@RequestParam(name = "error", defaultValue = "", required = false ) String error, Model model){
        if(error.equals("username")) {
            model.addAttribute("error", "This login is already taken");
        }
        return "reg";
    }

    @PostMapping("/reg")
    public String addUser(@RequestParam String username, @RequestParam String email,
                          @RequestParam String password){
        if(userRepository.findByUsername(username) != null){
            return "redirect:/reg?error=username";
        }
        password = passwordEncoder.encode(password);
        User user = new User(username, password, email, true, Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:/login";
    }

    @PostMapping("/user/update")
    public String editData(Principal principal, User userForm){
        User user =  userRepository.findByUsername(principal.getName());
        userForm.getEmail();
        userForm.getPassword();
        userForm.getRoles();

        user.setEmail(userForm.getEmail());
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));
        user.setRoles(userForm.getRoles());
        userRepository.save(user);

            return "redirect:/user";
    }

   }
