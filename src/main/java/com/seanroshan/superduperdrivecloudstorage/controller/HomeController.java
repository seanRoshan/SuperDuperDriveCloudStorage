package com.seanroshan.superduperdrivecloudstorage.controller;

import com.seanroshan.superduperdrivecloudstorage.model.User;
import com.seanroshan.superduperdrivecloudstorage.services.FileService;
import com.seanroshan.superduperdrivecloudstorage.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {


    private static final Logger LOGGER = LogManager.getLogger(HomeController.class);
    private final FileService fileService;
    private final UserService userService;

    public HomeController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @GetMapping
    public String getHomePage(Model model, Authentication authentication) {

        User activeUser = userService.getUser(authentication.getName());

        model.addAttribute("files", this.fileService.listFiles(activeUser.getUserId()));

        return "home";
    }


}
