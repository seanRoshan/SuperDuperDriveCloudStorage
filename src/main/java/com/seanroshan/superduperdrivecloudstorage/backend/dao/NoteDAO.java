package com.seanroshan.superduperdrivecloudstorage.backend.dao;

import com.seanroshan.superduperdrivecloudstorage.backend.mapper.NoteMapper;
import com.seanroshan.superduperdrivecloudstorage.model.Note;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Contains DAO methods for Note Service
 *
 * @author Sean Roshan
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Component
public class NoteDAO extends TransactionHandlerDAO {

    private static final Logger LOGGER = LogManager.getLogger(NoteDAO.class);
    private final NoteMapper noteMapper;

    public NoteDAO(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    /**
     * Gets notes by userId
     *
     * @param userId - userId
     * @return {@link List<Note>}
     */
    public List<Note> getNotesByUserId(int userId) {
        LOGGER.traceEntry(Integer.toString(userId));
        List<Note> notes = noteMapper.getNotesByUserId(userId);
        LOGGER.traceExit(notes);
        return notes;
    }


    /**
     * Gets note
     *
     * @param noteId - noteId
     * @param userId - userId
     * @return {@link Note}
     */
    public Note getNote(int userId, int noteId) {
        LOGGER.traceEntry(Integer.toString(userId), Integer.toString(noteId));
        Note note = noteMapper.getNote(userId, noteId);
        LOGGER.traceExit(note);
        return note;
    }


    /**
     * Insert a new note
     *
     * @param note - note
     * @return {@link Boolean}
     */
    public boolean addNewNote(Note note) {
        LOGGER.traceEntry(note.toString());
        boolean isAdded = noteMapper.insertNewNote(note) > 0;
        LOGGER.traceExit(isAdded);
        return isAdded;
    }


    /**
     * Delete note
     *
     * @param noteId - noteId
     * @param userId - userId
     * @return {@link Boolean}
     */
    public boolean deleteNote(int userId, int noteId) {
        LOGGER.traceEntry(Integer.toString(userId), Integer.toString(noteId));
        boolean isDeleted = noteMapper.deleteNote(userId, noteId) > 0;
        LOGGER.traceExit(isDeleted);
        return isDeleted;
    }


    /**
     * Update note
     *
     * @param note - note
     * @return {@link Boolean}
     */
    public boolean updateNote(Note note) {
        LOGGER.traceEntry(note.toString());
        boolean isUpdated = noteMapper.updateNote(note) > 0;
        LOGGER.traceExit(isUpdated);
        return isUpdated;
    }


}
