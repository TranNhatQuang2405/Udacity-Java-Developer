package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class FilesController {

    private final FilesService filesService;

    @PostMapping("/files")
    public String saveFile(Authentication authentication, MultipartFile fileUpload) throws IOException {
        User user = (User) authentication.getPrincipal();
        if (fileUpload.isEmpty()) {
            return "redirect:/result?error";
        }
        filesService.addFile(fileUpload, user.getUserid());
        return "redirect:/result?success";
    }

    @GetMapping("/files/delete")
    public String deleteNote(@RequestParam("id") int fileid) {
        if (fileid > 0) {
            filesService.deleteFile(fileid);
            return "redirect:/result?success";
        }
        return "redirect:/result?error";
    }
}
