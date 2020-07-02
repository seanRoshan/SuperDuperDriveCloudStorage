package com.seanroshan.superduperdrivecloudstorage.controller;

import com.seanroshan.superduperdrivecloudstorage.backend.constants.BusinessError;
import com.seanroshan.superduperdrivecloudstorage.model.User;
import com.seanroshan.superduperdrivecloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("signup")
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getSignUpPage(User user, Model model) {
        model.addAttribute("status", "idle");
        return "signup";
    }

    @PostMapping
    public String registerNewUser(User user, Model model) {
        BusinessError businessError = this.userService.verifyUser(user);
        if (businessError != null) {
            model.addAttribute("status", "FAILED");
            model.addAttribute("errorMessage", businessError.getErrorMessage());
            return "signup";
        }

        boolean isRegistered = this.userService.registerUser(user);
        if (isRegistered) {
            model.addAttribute("status", "SUCCESS");
        } else {
            model.addAttribute("status", "FAILED");
            model.addAttribute("errorMessage", "Registration failed!, please try again later!");
        }
        return "signup";
    }

}
