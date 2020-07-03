package com.seanroshan.superduperdrivecloudstorage.controller.file;

import com.seanroshan.superduperdrivecloudstorage.backend.constants.ApplicationConstants;
import com.seanroshan.superduperdrivecloudstorage.backend.constants.BusinessError;
import com.seanroshan.superduperdrivecloudstorage.model.User;
import com.seanroshan.superduperdrivecloudstorage.services.api.FileService;
import com.seanroshan.superduperdrivecloudstorage.services.api.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file-upload")
public class FileUploadController {

    private static final Logger LOGGER = LogManager.getLogger(FileUploadController.class);

    private final FileService fileService;
    private final UserService userService;

    public FileUploadController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }


    @PostMapping()
    public String handleFileUpload(@RequestParam("fileUpload") MultipartFile fileUpload, Model model, Authentication authentication) {

        User activeUser = userService.getUser(authentication.getName());

        long fileSize = fileUpload.getSize();

        if (fileSize <= 0L) {
            model.addAttribute(ApplicationConstants.STATUS_ATTRIBUTE, ApplicationConstants.FAILURE_STATUS);
            model.addAttribute(ApplicationConstants.ERROR_MESSAGE_ATTRIBUTE, BusinessError.NO_FILE.getErrorMessage());
            return ApplicationConstants.RESULT_PAGE_NAME;
        }


        boolean isFileExists = fileService.checkFileExists(fileUpload.getOriginalFilename(), activeUser.getUserId());
        if (isFileExists) {
            model.addAttribute(ApplicationConstants.STATUS_ATTRIBUTE, ApplicationConstants.FAILURE_STATUS);
            model.addAttribute(ApplicationConstants.ERROR_MESSAGE_ATTRIBUTE, BusinessError.FILED_ALREADY_UPLOADED.getErrorMessage());
            return ApplicationConstants.RESULT_PAGE_NAME;
        }

        boolean isUploaded = fileService.uploadFile(fileUpload, activeUser.getUserId());

        if (isUploaded) {
            model.addAttribute(ApplicationConstants.STATUS_ATTRIBUTE, ApplicationConstants.SUCCESS_STATUS);
        } else {
            model.addAttribute(ApplicationConstants.STATUS_ATTRIBUTE, ApplicationConstants.FAILURE_STATUS);
            model.addAttribute(ApplicationConstants.ERROR_MESSAGE_ATTRIBUTE, BusinessError.UPLOAD_FILE_FAILED.getErrorMessage());
        }

        return ApplicationConstants.RESULT_PAGE_NAME;
    }

}
