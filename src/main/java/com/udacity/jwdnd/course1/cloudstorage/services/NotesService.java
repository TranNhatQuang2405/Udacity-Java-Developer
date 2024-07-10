package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.Notes;

import java.util.List;

public interface NotesService {
    List<Notes> getAllNotes(int userid) throws Exception;

    void addNote(Notes note, int userid);

    void updateNote(Notes note);

    void deleteNote(int noteid);
}
