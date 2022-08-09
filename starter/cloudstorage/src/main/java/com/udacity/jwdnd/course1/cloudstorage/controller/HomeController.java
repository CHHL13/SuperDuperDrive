package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.*;
import com.udacity.jwdnd.course1.cloudstorage.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    private UserService userService;
    private FileService fileService;
    private NoteService noteService;
    private CredentialService credentialService;

    private EncryptionService encryptionService;

    public HomeController(UserService userService, FileService fileService, NoteService noteService,
                          CredentialService credentialService, EncryptionService encryptionService) {
        this.userService = userService;
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @GetMapping()
    public String getHomePage(Authentication authentication, @ModelAttribute("newFile") FileForm fileForm,
                              @ModelAttribute("newNote") NoteForm newNote, @ModelAttribute("newCredential") CredentialForm newCredential, Model model) {
        String userName = authentication.getName();
        User user = userService.getUser(userName);

        model.addAttribute("fileList", fileService.getFileList(user.getUserId()));
        model.addAttribute("noteList", noteService.getNoteList(user.getUserId()));
        model.addAttribute("credentialList", credentialService.getCredentialList(user.getUserId()));
        model.addAttribute("encryptionService",encryptionService);

        return "home";
    }


}
