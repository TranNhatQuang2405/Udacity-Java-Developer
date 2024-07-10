package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class NotesController {

    private final NotesService notesService;

    @PostMapping("/notes")
    public String createOrUpdateNote(Authentication authentication, Notes note) {
        User appUser = (User) authentication.getPrincipal();
        if (note.getNoteid() > 0) {
            notesService.updateNote(note);
        } else {
            notesService.addNote(note, appUser.getUserid());
        }
        return "redirect:/result?success";
    }

    @GetMapping("/notes/delete")
    public String deleteNote(@RequestParam("id") int noteid) {
        if (noteid > 0) {
            notesService.deleteNote(noteid);
            return "redirect:/result?success";
        }
        return "redirect:/result?error";
    }
}
