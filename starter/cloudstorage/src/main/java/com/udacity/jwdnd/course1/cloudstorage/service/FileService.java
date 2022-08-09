package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService {

    private TimeStampService timeStampService;
    private final FileMapper fileMapper;


    public FileService(FileMapper fileMapper, TimeStampService timeStampService) {
        this.fileMapper = fileMapper;
        this.timeStampService = timeStampService;
    }

    public String[] getFileList(Integer userId) {
        return fileMapper.getFileList(userId);
    }

    public int addFile(MultipartFile multipartFile, User user) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        String contentType = multipartFile.getContentType();
        String fileSize = String.valueOf(multipartFile.getSize());
        Integer userId = user.getUserId();
        byte[] fileData = multipartFile.getBytes();

        File file = new File(0, fileName, contentType, fileSize, userId, fileData);

        return fileMapper.insert(file);
    }

    public boolean isFileNameDuplicate(Integer userId, MultipartFile multipartFile) {
        return fileMapper.getFileByFileName(userId, multipartFile.getOriginalFilename()) != null;
    }

    public File getFile(Integer userId, String fileName) {
        return fileMapper.getFileByFileName(userId, fileName);
    }

    public int deleteFile(Integer userId, String fileName) {
        return fileMapper.delete(userId, fileName);
    }

}
