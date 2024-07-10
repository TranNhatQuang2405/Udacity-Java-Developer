package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final NotesService notesService;

    private final CredentialsService credentialsService;

    private final FilesService filesService;

    @GetMapping(value = {"/", "/home"})
    public ModelAndView getHomePage(Authentication authentication) throws  Exception {
        User user = (User) authentication.getPrincipal();
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("notes", notesService.getAllNotes(user.getUserid()));
        modelAndView.addObject("credentials", credentialsService.getAllCredentials(user.getUserid()));
        modelAndView.addObject("files", filesService.getAllFiles(user.getUserid()));
        return modelAndView;
    }
}
