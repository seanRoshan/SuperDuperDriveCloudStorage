package com.seanroshan.superduperdrivecloudstorage.controller;

import com.seanroshan.superduperdrivecloudstorage.backend.constants.ApplicationConstants;
import com.seanroshan.superduperdrivecloudstorage.backend.constants.BusinessError;
import com.seanroshan.superduperdrivecloudstorage.model.User;
import com.seanroshan.superduperdrivecloudstorage.services.api.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(ApplicationConstants.SIGN_UP_PAGE_NAME)
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getSignUpPage(User user, Model model) {
        model.addAttribute(ApplicationConstants.STATUS_ATTRIBUTE, ApplicationConstants.IDLE_STATUS);
        return ApplicationConstants.SIGN_UP_PAGE_NAME;
    }

    @PostMapping
    public String registerNewUser(User user, Model model) {
        BusinessError businessError = this.userService.verifyUser(user);
        if (businessError != null) {
            model.addAttribute(ApplicationConstants.STATUS_ATTRIBUTE, ApplicationConstants.FAILURE_STATUS);
            model.addAttribute(ApplicationConstants.ERROR_MESSAGE_ATTRIBUTE, businessError.getErrorMessage());
            return ApplicationConstants.SIGN_UP_PAGE_NAME;
        }

        boolean isRegistered = this.userService.registerUser(user);
        if (isRegistered) {
            model.addAttribute(ApplicationConstants.STATUS_ATTRIBUTE, ApplicationConstants.SUCCESS_STATUS);
        } else {
            model.addAttribute(ApplicationConstants.STATUS_ATTRIBUTE, ApplicationConstants.FAILURE_STATUS);
            model.addAttribute(ApplicationConstants.ERROR_MESSAGE_ATTRIBUTE, BusinessError.REQUIRED_FIELDS.getErrorMessage());
        }
        return ApplicationConstants.SIGN_UP_PAGE_NAME;
    }

}
