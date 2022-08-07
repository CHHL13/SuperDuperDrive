package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

@Service
public class NoteService {

    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public Note[] getNoteList(Integer userId) {
        return noteMapper.getNoteList(userId);
    }

    public int addNote(Note note) {
        return noteMapper.insert(note);
    }

    public int deleteNote(Integer noteId) {
        return noteMapper.deleteNote(noteId);
    }

    public int updateNote(Integer noteId, String notetitle, String notedescription) {
        return noteMapper.updateNote(noteId, notetitle, notedescription);
    }
}
