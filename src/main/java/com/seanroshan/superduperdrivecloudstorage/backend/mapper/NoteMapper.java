package com.seanroshan.superduperdrivecloudstorage.backend.mapper;


import com.seanroshan.superduperdrivecloudstorage.backend.constants.ParameterConstants;
import com.seanroshan.superduperdrivecloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Contains Mapper methods for Note Service
 *
 * @author Sean Roshan
 */
@Mapper
public interface NoteMapper {


    /**
     * Gets notes by userId
     *
     * @param userId - userId
     * @return {@link List <Note>}
     */
    @Select("SELECT * FROM NOTES WHERE USER_ID = #{userId}")
    List<Note> getNotesByUserId(@Param(ParameterConstants.USER_ID) int userId);


    /**
     * Gets note
     *
     * @param noteId - noteId
     * @param userId - userId
     * @return {@link Note}
     */
    @Select("SELECT * FROM NOTES WHERE "
            + " USER_ID = #{userId} "
            + " AND NOTE_ID = #{noteId} "
            + " LIMIT 1")
    Note getNote(@Param(ParameterConstants.USER_ID) int userId,
                 @Param(ParameterConstants.NOTE_ID) int noteId);


    /**
     * Insert a new note
     *
     * @param note - note
     * @return {@link Integer}
     */
    @Insert("INSERT INTO NOTES (NOTE_TITLE, NOTE_DESCRIPTION, USER_ID ) "
            + " VALUES ( #{noteTitle}, #{noteDescription}, #{userId} )")
    @Options(useGeneratedKeys = true, keyColumn = "NOTE_ID", keyProperty = "noteId")
    int insertNewNote(Note note);


    /**
     * Delete note
     *
     * @param noteId - noteId
     * @param userId - userId
     * @return {@link Integer}
     */
    @Delete("DELETE FROM NOTES WHERE "
            + " USER_ID = #{userId} "
            + " AND NOTE_ID = #{noteId} ")
    int deleteNote(@Param(ParameterConstants.USER_ID) int userId,
                   @Param(ParameterConstants.NOTE_ID) int noteId);


    /**
     * Update note
     *
     * @param note - note
     * @return {@link Integer}
     */
    @Update("UPDATE NOTES "
            + " SET NOTE_TITLE = #{noteTitle}, NOTE_DESCRIPTION = #{noteDescription} "
            + " WHERE USER_ID = #{userId} AND NOTE_ID = #{noteId} ")
    int updateNote(Note note);


}
