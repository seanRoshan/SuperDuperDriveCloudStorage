package com.seanroshan.superduperdrivecloudstorage.controller.note;

import com.seanroshan.superduperdrivecloudstorage.backend.constants.ApplicationConstants;
import com.seanroshan.superduperdrivecloudstorage.backend.constants.BusinessError;
import com.seanroshan.superduperdrivecloudstorage.backend.constants.ParameterConstants;
import com.seanroshan.superduperdrivecloudstorage.model.Note;
import com.seanroshan.superduperdrivecloudstorage.model.User;
import com.seanroshan.superduperdrivecloudstorage.services.api.NoteService;
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
@RequestMapping("/note-management")
public class NoteController {


    private static final Logger LOGGER = LogManager.getLogger(NoteController.class);
    private final UserService userService;
    private final NoteService noteService;

    public NoteController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    @GetMapping("/delete")
    public String handleFileRemoval(@RequestParam(ParameterConstants.NOTE_ID) int noteId, Model model, Authentication authentication) {
        LOGGER.debug(noteId);

        try {
            User activeUser = userService.getUser(authentication.getName());
            boolean noteDeleted = noteService.deleteNote(activeUser.getUserId(), noteId);
            if (noteDeleted) {
                model.addAttribute(ApplicationConstants.STATUS_ATTRIBUTE, ApplicationConstants.SUCCESS_STATUS);
                return ApplicationConstants.RESULT_PAGE_NAME;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }

        model.addAttribute(ApplicationConstants.STATUS_ATTRIBUTE, ApplicationConstants.FAILURE_STATUS);
        model.addAttribute(ApplicationConstants.ERROR_MESSAGE_ATTRIBUTE, BusinessError.DELETE_NOTE_FAILED.getErrorMessage());
        return ApplicationConstants.RESULT_PAGE_NAME;
    }

    @PostMapping
    public String handleAddNote(Note note, Model model, Authentication authentication) {
        User activeUser = userService.getUser(authentication.getName());
        LOGGER.debug(activeUser);

        if (activeUser == null) {
            return ApplicationConstants.LOGIN_PAGE_NAME;
        }

        note.setUserId(activeUser.getUserId());


        boolean isUpdated = note.getNoteId() > 0 ? noteService.updateNote(note) : noteService.addNewNote(note);

        if (isUpdated) {
            model.addAttribute(ApplicationConstants.STATUS_ATTRIBUTE, ApplicationConstants.SUCCESS_STATUS);
        } else {
            model.addAttribute(ApplicationConstants.STATUS_ATTRIBUTE, ApplicationConstants.FAILURE_STATUS);
            model.addAttribute(ApplicationConstants.ERROR_MESSAGE_ATTRIBUTE, note.getNoteId() > 0 ? BusinessError.UPDATE_NOTE_FAILED.getErrorMessage() : BusinessError.ADD_NOTE_FAILED.getErrorMessage());
        }

        return ApplicationConstants.RESULT_PAGE_NAME;
    }


}
