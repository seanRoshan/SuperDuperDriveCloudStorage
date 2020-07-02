package com.seanroshan.superduperdrivecloudstorage.controller.file;

import com.seanroshan.superduperdrivecloudstorage.model.User;
import com.seanroshan.superduperdrivecloudstorage.services.FileService;
import com.seanroshan.superduperdrivecloudstorage.services.UserService;
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

        boolean isUploaded = fileService.uploadFile(fileUpload, activeUser.getUserId());

        if (isUploaded) {
            model.addAttribute("status", "SUCCESS");
        } else {
            model.addAttribute("status", "FAILED");
            model.addAttribute("errorMessage", "File upload failed!");
        }

        return "result";
    }

}
