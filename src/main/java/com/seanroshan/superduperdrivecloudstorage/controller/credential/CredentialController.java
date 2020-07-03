package com.seanroshan.superduperdrivecloudstorage.controller.credential;

import com.seanroshan.superduperdrivecloudstorage.backend.constants.ApplicationConstants;
import com.seanroshan.superduperdrivecloudstorage.backend.constants.BusinessError;
import com.seanroshan.superduperdrivecloudstorage.backend.constants.ParameterConstants;
import com.seanroshan.superduperdrivecloudstorage.model.Credential;
import com.seanroshan.superduperdrivecloudstorage.model.User;
import com.seanroshan.superduperdrivecloudstorage.services.api.CredentialService;
import com.seanroshan.superduperdrivecloudstorage.services.api.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("credential-management")
public class CredentialController {


    private static final Logger LOGGER = LogManager.getLogger(CredentialController.class);
    private final UserService userService;
    private final CredentialService credentialService;

    public CredentialController(UserService userService, CredentialService credentialService) {
        this.userService = userService;
        this.credentialService = credentialService;
    }

    @GetMapping("/delete")
    public String handleFileRemoval(@RequestParam(ParameterConstants.CREDENTIAL_ID) int credentialId, Model model, Authentication authentication) {
        LOGGER.debug(credentialId);

        try {
            User activeUser = userService.getUser(authentication.getName());
            boolean credentialDeleted = credentialService.deleteCredential(credentialId, activeUser.getUserId());
            if (credentialDeleted) {
                model.addAttribute(ApplicationConstants.STATUS_ATTRIBUTE, ApplicationConstants.SUCCESS_STATUS);
                return ApplicationConstants.RESULT_PAGE_NAME;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }

        model.addAttribute(ApplicationConstants.STATUS_ATTRIBUTE, ApplicationConstants.FAILURE_STATUS);
        model.addAttribute(ApplicationConstants.ERROR_MESSAGE_ATTRIBUTE, BusinessError.DELETE_CREDENTIAL_FAILED.getErrorMessage());
        return ApplicationConstants.RESULT_PAGE_NAME;
    }

    @PostMapping
    public String handleAddCredential(Credential credential, Model model, Authentication authentication) {
        User activeUser = userService.getUser(authentication.getName());
        LOGGER.debug(activeUser);

        if (activeUser == null) {
            return ApplicationConstants.LOGIN_PAGE_NAME;
        }

        credential.setUserId(activeUser.getUserId());

        boolean isUpdated = credential.getCredentialId() > 0 ? credentialService.updateCredential(credential) : credentialService.addNewCredential(credential);

        if (isUpdated) {
            model.addAttribute(ApplicationConstants.STATUS_ATTRIBUTE, ApplicationConstants.SUCCESS_STATUS);
        } else {
            model.addAttribute(ApplicationConstants.STATUS_ATTRIBUTE, ApplicationConstants.FAILURE_STATUS);
            model.addAttribute(ApplicationConstants.ERROR_MESSAGE_ATTRIBUTE, credential.getCredentialId() > 0
                    ? BusinessError.UPDATE_CREDENTIAL_FAILED.getErrorMessage()
                    : BusinessError.ADD_CREDENTIAL_FAILED.getErrorMessage());
        }

        return ApplicationConstants.RESULT_PAGE_NAME;
    }

}
