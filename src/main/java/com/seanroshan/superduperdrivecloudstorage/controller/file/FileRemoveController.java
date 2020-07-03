package com.seanroshan.superduperdrivecloudstorage.controller.file;

import com.seanroshan.superduperdrivecloudstorage.backend.constants.ApplicationConstants;
import com.seanroshan.superduperdrivecloudstorage.backend.constants.BusinessError;
import com.seanroshan.superduperdrivecloudstorage.backend.constants.ParameterConstants;
import com.seanroshan.superduperdrivecloudstorage.model.User;
import com.seanroshan.superduperdrivecloudstorage.services.api.FileService;
import com.seanroshan.superduperdrivecloudstorage.services.api.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/file-remove")
public class FileRemoveController {

    private static final Logger LOGGER = LogManager.getLogger(FileRemoveController.class);

    private final FileService fileService;
    private final UserService userService;

    public FileRemoveController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @GetMapping()
    public String handleFileRemoval(@RequestParam(ParameterConstants.FILE_ID) int fileId, Model model, Authentication authentication) {
        LOGGER.debug(fileId);

        try {
            User activeUser = userService.getUser(authentication.getName());
            boolean fileDeleted = fileService.deleteFile(fileId, activeUser.getUserId());
            if (fileDeleted) {
                model.addAttribute(ApplicationConstants.STATUS_ATTRIBUTE, ApplicationConstants.SUCCESS_STATUS);
                return ApplicationConstants.RESULT_PAGE_NAME;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }

        model.addAttribute(ApplicationConstants.STATUS_ATTRIBUTE, ApplicationConstants.FAILURE_STATUS);
        model.addAttribute(ApplicationConstants.ERROR_MESSAGE_ATTRIBUTE, BusinessError.DELETE_FILE_FAILED.getErrorMessage());
        return ApplicationConstants.RESULT_PAGE_NAME;
    }

}
