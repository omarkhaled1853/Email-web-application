package com.omarkhaled.simple.webbased.email.program.controllers;

import com.omarkhaled.simple.webbased.email.program.classes.Mail;
import com.omarkhaled.simple.webbased.email.program.classes.User;
import com.omarkhaled.simple.webbased.email.program.services.FoldersServices;
import com.omarkhaled.simple.webbased.email.program.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping
@CrossOrigin
public class FoldersController {
    private final UsersService usersService;
    private final FoldersServices foldersServices;

    @Autowired
    public FoldersController(UsersService usersService, FoldersServices foldersServices) {
        this.usersService = usersService;
        this.foldersServices = foldersServices;
    }

    //get mails
    @GetMapping ("/mails/folders")
    public Collection<Mail> getFolder (@RequestParam String id, @RequestParam String folderName){
        User user = usersService.getUser(id);

        if(user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        foldersServices.setFoldersDB(user.getFolders());

        return foldersServices.getMails(folderName);
    }

    //delete folder
    @DeleteMapping ("/folders/{folderName}")
    public void deleteFolder (@PathVariable String folderName){
        Map<String, Mail> mails = foldersServices.deleteFolder(folderName);

        if (mails == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    //delete mail
    @DeleteMapping ("/folders")
    public void deleteMail (@RequestParam String name, @RequestParam String id){
        Mail mail = foldersServices.deleteMail(name, id);

        if (mail == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
