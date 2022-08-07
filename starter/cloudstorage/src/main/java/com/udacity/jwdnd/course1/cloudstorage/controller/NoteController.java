package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/note")
public class NoteController {
    private NoteService noteService;

    private UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping(value = "/add")
    public String addNote(Authentication authentication, @ModelAttribute("newNote") NoteForm newNote, Model model) {
        String userName = authentication.getName();
        User user = userService.getUser(userName);

        String notetitle = newNote.getNotetitle();
        String notedescription = newNote.getNotedescription();

        if(newNote.getNoteId().isEmpty()) {
            Note note = new Note(0, notetitle, notedescription, user.getUserId());
            if(noteService.addNote(note) > 0) {
                model.addAttribute("isSuccess", "changeSuccess");
            } else {
                model.addAttribute("isSuccess", "changeError");
            }
        } else {
            if(noteService.updateNote(Integer.valueOf(newNote.getNoteId()), notetitle, notedescription) > 0) {
                model.addAttribute("isSuccess", "changeSuccess");
            } else {
                model.addAttribute("isSuccess", "changeError");
            }
        }


        return "result";
    }

    @GetMapping(value = "/delete/{noteId}")
    public String deleteNote(Authentication authentication, @PathVariable Integer noteId, Model model) {
        String userName = authentication.getName();
        User user = userService.getUser(userName);

        if(noteService.deleteNote(noteId) > 0) {
            model.addAttribute("isSuccess", "changeSuccess");
        } else {
            model.addAttribute("isSuccess", "changeError");
        }

        return "result";
    }

}
