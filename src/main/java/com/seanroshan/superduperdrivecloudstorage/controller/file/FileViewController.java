package com.seanroshan.superduperdrivecloudstorage.controller.file;

import com.seanroshan.superduperdrivecloudstorage.model.File;
import com.seanroshan.superduperdrivecloudstorage.model.User;
import com.seanroshan.superduperdrivecloudstorage.services.FileService;
import com.seanroshan.superduperdrivecloudstorage.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Controller
@RequestMapping("/file-view")
public class FileViewController {

    private static final Logger LOGGER = LogManager.getLogger(FileRemoveController.class);

    private final FileService fileService;
    private final UserService userService;

    public FileViewController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @GetMapping()
    public String handleFileView(@RequestParam("fileId") int fileId, Model model, Authentication authentication, HttpServletResponse response) {
        LOGGER.debug(fileId);

        try {
            User activeUser = userService.getUser(authentication.getName());
            File file = fileService.downloadFile(fileId, activeUser.getUserId());
            response.setContentType(file.getContentType());
            response.setHeader("Content-Disposition", "inline; filename=\"" + file.getFileName() + "\"");
            response.setContentLength((Integer.parseInt(file.getFileSize())));
            InputStream inputStream = new ByteArrayInputStream(file.getFileData());
            FileCopyUtils.copy(inputStream, response.getOutputStream());
            return "result";
        } catch (Exception e) {
            model.addAttribute("status", "FAILED");
            model.addAttribute("errorMessage", "Failed to download file");
            return "result";
        }

    }

}
