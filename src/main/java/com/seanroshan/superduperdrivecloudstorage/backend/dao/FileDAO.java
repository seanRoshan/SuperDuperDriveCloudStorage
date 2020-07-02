package com.seanroshan.superduperdrivecloudstorage.backend.dao;

import com.seanroshan.superduperdrivecloudstorage.backend.mapper.FileMapper;
import com.seanroshan.superduperdrivecloudstorage.model.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Contains DAO methods for User Service
 *
 * @author Sean Roshan
 */
@SuppressWarnings({"SpringJavaInjectionPointsAutowiringInspection", "SpringJavaAutowiredFieldsWarningInspection"})
@Component
public class FileDAO extends TransactionHandlerDAO {

    private static final Logger LOGGER = LogManager.getLogger(FileDAO.class);

    @Autowired
    FileMapper fileMapper;

    /**
     * Gets files by userId
     *
     * @param userId - userId
     * @return {@link ArrayList<File>}
     */
    public ArrayList<File> getUserByUserId(int userId) {
        LOGGER.traceEntry(Integer.toString(userId));
        ArrayList<File> files = fileMapper.getFilesByUserId(userId);
        LOGGER.traceExit(files);
        return files;
    }

    /**
     * Insert a new file
     *
     * @param file - file
     * @return {@link Boolean}
     */
    public boolean insertNewFile(File file) {
        LOGGER.traceEntry(file.toString());
        boolean isUploaded = fileMapper.insertNewFile(file) > 0;
        LOGGER.traceExit(isUploaded);
        return isUploaded;
    }


    /**
     * Gets File by fileId and userId
     *
     * @param userId - userId
     * @param fileId - fileId
     * @return {@link File}
     */
    public File getFile(int fileId, int userId) {
        LOGGER.traceEntry(Integer.toString(fileId), Integer.toString(userId));
        File file = fileMapper.getFile(fileId, userId);
        LOGGER.traceExit(file);
        return file;
    }


    /**
     * Deletes file by fileId and user Id
     *
     * @param userId - userId
     * @param fileId - fileId
     * @return {@link Boolean}
     */
    public boolean deleteFile(int fileId, int userId) {
        LOGGER.traceEntry(Integer.toString(fileId), Integer.toString(userId));
        boolean isDeleted = fileMapper.deleteFile(fileId, userId) > 0;
        LOGGER.traceExit(isDeleted);
        return isDeleted;
    }


}
