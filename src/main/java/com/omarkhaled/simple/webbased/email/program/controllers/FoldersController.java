package com.omarkhaled.simple.webbased.email.program.controllers;

import com.omarkhaled.simple.webbased.email.program.classes.Mail;
import com.omarkhaled.simple.webbased.email.program.services.FoldersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping
@CrossOrigin
public class FoldersController {
    private final FoldersServices foldersServices;

    @Autowired
    public FoldersController(FoldersServices foldersServices) {
        this.foldersServices = foldersServices;
    }

    //delete folder
    @DeleteMapping ("/folders/{name}")
    public void deleteFolder (@PathVariable String name){
        Map<String, Mail> mails = foldersServices.deleteFolder(name);

        if (mails == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    //delete mail
    @DeleteMapping ("/folders")
    public void deleteMail (@RequestParam String name, @RequestParam String id){
        Mail mail = foldersServices.deleteMail(name, id);

        if (mail == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
