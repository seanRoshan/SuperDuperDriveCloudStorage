package com.seanroshan.superduperdrivecloudstorage.services.api;

import com.seanroshan.superduperdrivecloudstorage.backend.dao.NoteDAO;
import com.seanroshan.superduperdrivecloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteDAO noteDAO;

    public NoteService(NoteDAO noteDAO) {
        this.noteDAO = noteDAO;
    }

    public Note getNote(int userId, int noteId) {
        return noteDAO.getNote(userId, noteId);
    }

    public List<Note> listNotes(int userId) {
        return noteDAO.getNotesByUserId(userId);
    }

    public boolean addNewNote(Note note) {
        return noteDAO.addNewNote(note);
    }

    public boolean deleteNote(int userId, int noteId) {
        return noteDAO.deleteNote(userId, noteId);
    }

    public boolean updateNote(Note note) {
        return noteDAO.updateNote(note);
    }
}
