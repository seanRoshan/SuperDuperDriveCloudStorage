package com.seanroshan.superduperdrivecloudstorage.backend.mapper;

import com.seanroshan.superduperdrivecloudstorage.backend.constants.ParameterConstants;
import com.seanroshan.superduperdrivecloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

/**
 * Contains Mapper methods for File Service
 *
 * @author Sean Roshan
 */
@Mapper
public interface FileMapper {


    /**
     * Gets files by userId
     *
     * @param userId - userId
     * @return {@link ArrayList<File>}
     */
    @Select("SELECT * FROM FILES WHERE USER_ID = #{userId}")
    ArrayList<File> getFilesByUserId(@Param(ParameterConstants.USER_ID) int userId);


    /**
     * Insert a new file
     *
     * @param file - file
     * @return {@link Integer}
     */
    @Insert("INSERT INTO FILES ( FILE_NAME , FILE_SIZE , FILE_DATA , CONTENT_TYPE , USER_ID ) "
            + " VALUES ( #{fileName}, #{fileSize}, #{fileData}, #{contentType}, #{userId} ) ")
    @Options(useGeneratedKeys = true, keyColumn = "FILE_ID", keyProperty = "fileId")
    int insertNewFile(File file);


    /**
     * Gets File by fileId and userId
     *
     * @param fileId - fileId
     * @return {@link File}
     */
    @Select("SELECT * FROM FILES WHERE "
            + " FILE_ID = #{fileId} "
            + " and USER_ID = #{userId} "
            + " limit 1")
    File getFile(@Param(ParameterConstants.FILE_ID) int fileId,
                 @Param(ParameterConstants.USER_ID) int userId);


    /**
     * Deletes file by fileId and user Id
     *
     * @param fileId - fileId
     * @return {@link Integer}
     */
    @Delete("DELETE FROM FILES WHERE "
            + " FILE_ID = #{fileId} "
            + " and USER_ID = #{userId} ")
    int deleteFile(@Param(ParameterConstants.FILE_ID) int fileId,
                   @Param(ParameterConstants.USER_ID) int userId);

}
