package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Base64;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    private CredentialService credentialService;
    private UserService userService;
    private EncryptionService encryptionService;

    public CredentialController(CredentialService credentialService, UserService userService, EncryptionService encryptionService) {
        this.credentialService = credentialService;
        this.userService = userService;
        this.encryptionService = encryptionService;
    }

    @PostMapping(value = "/add")
    public String addCredential(Authentication authentication, @ModelAttribute("newCredential") CredentialForm newCredential, Model model) {
        String authUserName = authentication.getName();
        User user = userService.getUser(authUserName);

        String url = newCredential.getUrl();
        String username = newCredential.getUsername();
        String password = newCredential.getPassword();

        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(password, encodedKey);

        if(newCredential.getCredentialId().isEmpty()) {
            Credential credential = new Credential(0, url, username, encodedKey, encryptedPassword, user.getUserId());

            if(credentialService.addCredential(credential) > 0) {
                model.addAttribute("isSuccess", "changeSuccess");
            } else {
                model.addAttribute("isSuccess", "changeError");
            }
        } else {
            if(credentialService.updateCredential(Integer.valueOf(newCredential.getCredentialId()), url, username, encryptedPassword, encodedKey) > 0) {
                model.addAttribute("isSuccess", "changeSuccess");
            } else {
                model.addAttribute("isSuccess", "changeError");
            }
        }

        return "result";
    }

    @GetMapping(value = "/delete/{credentialId}")
    public String deleteCredential(Authentication authentication, @PathVariable Integer credentialId, Model model) {
        String userName = authentication.getName();
        User user = userService.getUser(userName);

        if(credentialService.deleteCredential(credentialId) > 0) {
            model.addAttribute("isSuccess", "changeSuccess");
        } else {
            model.addAttribute("isSuccess", "changeError");
        }

        return "result";
    }
}
