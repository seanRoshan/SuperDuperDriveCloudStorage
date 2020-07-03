package com.seanroshan.superduperdrivecloudstorage.controller;

import com.seanroshan.superduperdrivecloudstorage.backend.constants.ApplicationConstants;
import com.seanroshan.superduperdrivecloudstorage.model.File;
import com.seanroshan.superduperdrivecloudstorage.model.Note;
import com.seanroshan.superduperdrivecloudstorage.model.User;
import com.seanroshan.superduperdrivecloudstorage.services.api.FileService;
import com.seanroshan.superduperdrivecloudstorage.services.api.NoteService;
import com.seanroshan.superduperdrivecloudstorage.services.api.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {


    private static final Logger LOGGER = LogManager.getLogger(HomeController.class);
    private final FileService fileService;
    private final UserService userService;
    private final NoteService noteService;

    public HomeController(FileService fileService, UserService userService, NoteService noteService) {
        this.fileService = fileService;
        this.userService = userService;
        this.noteService = noteService;
    }

    @GetMapping
    public String getHomePage(Model model) {
        model.addAttribute("noteToEdit", new Note());
        return ApplicationConstants.HOME_PAGE_NAME;
    }

    @ModelAttribute(ApplicationConstants.FILES_ATTRIBUTE)
    public List<File> listFiles(Authentication authentication) {
        User activeUser = userService.getUser(authentication.getName());
        if (activeUser == null) return null;
        int userId = activeUser.getUserId();
        return this.fileService.listFiles(userId);
    }

    @ModelAttribute(ApplicationConstants.NOTES_ATTRIBUTE)
    public List<Note> listNotes(Authentication authentication) {
        User activeUser = userService.getUser(authentication.getName());
        if (activeUser == null) return null;
        int userId = activeUser.getUserId();
        return this.noteService.listNotes(userId);
    }


}
