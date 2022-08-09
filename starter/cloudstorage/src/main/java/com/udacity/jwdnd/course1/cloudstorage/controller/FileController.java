package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileController {

    private FileService fileService;
    private UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping(value = "/add")
    public String addFile(Authentication authentication, @ModelAttribute("newFile") FileForm fileForm, Model model) throws IOException {
        String userName = authentication.getName();
        User user = userService.getUser(userName);

        MultipartFile multipartFile = fileForm.getFile();
        if(multipartFile.isEmpty() || fileService.isFileNameDuplicate(user.getUserId(), multipartFile) || fileService.addFile(multipartFile, user) <= 0) {
            model.addAttribute("isSuccess", "changeError");
        } else {
            model.addAttribute("isSuccess", "changeSuccess");
        }

        return "result";
    }

    @GetMapping(value = "/get/{fileName}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public byte[] getFile(Authentication authentication, @PathVariable String fileName) {
        String userName = authentication.getName();
        User user = userService.getUser(userName);

        return fileService.getFile(user.getUserId(), fileName).getFileData();
    }

    @GetMapping(value = "/delete/{fileName}")
    public String deleteFile(Authentication authentication, @PathVariable String fileName, Model model) {
        String userName = authentication.getName();
        User user = userService.getUser(userName);

        if(fileService.deleteFile(user.getUserId(), fileName) > 0) {
            model.addAttribute("isSuccess", "changeSuccess");
        } else {
            model.addAttribute("isSuccess", "changeError");
        }

        return "result";
    }
}
