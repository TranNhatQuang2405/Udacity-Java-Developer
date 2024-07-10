package com.udacity.jwdnd.course1.cloudstorage.services.impl;

import com.udacity.jwdnd.course1.cloudstorage.repository.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotesServiceImpl implements NotesService {

    private final NotesMapper notesMapper;

    @Override
    public List<Notes> getAllNotes(int userid) throws Exception {
        List<Notes> notes = notesMapper.findByUserId(userid);
        if (notes == null) {
            throw new Exception();
        }
        return notes;
    }

    @Override
    public void addNote(Notes note, int userid) {
        notesMapper.insertNote(note, userid);
    }

    @Override
    public void updateNote(Notes note) {
        notesMapper.updateNote(note);
    }

    @Override
    public void deleteNote(int noteid) {
        notesMapper.deleteNote(noteid);
    }
}
