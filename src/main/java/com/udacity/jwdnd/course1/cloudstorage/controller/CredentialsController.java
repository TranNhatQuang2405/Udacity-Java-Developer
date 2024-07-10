package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CredentialsController {

    private final CredentialsService credentialsService;

    @PostMapping("/credentials")
    public String saveOrUpdateCredentials(Authentication authentication, Credentials credential) {
        User user = (User) authentication.getPrincipal();
        if (credential.getCredentialid() > 0) {
            credentialsService.updateCredential(credential);
        }
        else {
            credentialsService.addCredential(credential, user.getUserid());
        }
        return "redirect:/result?success";
    }

    @GetMapping("/credentials/delete")
    public String deleteNote(@RequestParam("id") int credentialid) {
        if (credentialid > 0) {
            credentialsService.deleteCredential(credentialid);
            return "redirect:/result?success";
        }
        return "redirect:/result?error";
    }
}
