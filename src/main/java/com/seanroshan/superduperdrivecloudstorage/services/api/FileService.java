package com.seanroshan.superduperdrivecloudstorage.services.api;

import com.seanroshan.superduperdrivecloudstorage.backend.dao.FileDAO;
import com.seanroshan.superduperdrivecloudstorage.model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Service
public class FileService {

    private final FileDAO fileDAO;

    public FileService(FileDAO fileDAO) {
        this.fileDAO = fileDAO;
    }

    public ArrayList<File> listFiles(int userId) {
        return fileDAO.getUserByUserId(userId);
    }


    public boolean uploadFile(MultipartFile fileUpload, int userId) {
        try {
            File file = new File();
            file.setContentType(fileUpload.getContentType());
            file.setFileName(fileUpload.getOriginalFilename());
            file.setFileData(fileUpload.getBytes());
            file.setFileSize(Long.toString(fileUpload.getSize()));
            file.setUserId(userId);
            return fileDAO.insertNewFile(file);
        } catch (Exception e) {
            return false;
        }
    }


    public boolean checkFileExists(String fileName, int userId) {
        return fileDAO.getFileByFileName(fileName, userId) != null;
    }


    public File downloadFile(int fileId, int userId) {
        return fileDAO.getFile(fileId, userId);
    }

    public boolean deleteFile(int fileId, int userId) {
        return fileDAO.deleteFile(fileId, userId);
    }

}
