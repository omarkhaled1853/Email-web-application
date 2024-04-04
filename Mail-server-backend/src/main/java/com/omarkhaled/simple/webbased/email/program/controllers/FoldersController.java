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
import java.util.List;
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

    //get folders
    @GetMapping ("/folders")
    public Collection<String> getFolders(@RequestParam String id){
        User user = usersService.getUser(id);

        if(user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return foldersServices.getFolders(id, usersService.getUsersDB());
    }

    //get mails
    @GetMapping ("/mails/folders")
    public Collection<Mail> getFolder (@RequestParam String id, @RequestParam String folderName){
        User user = usersService.getUser(id);

        if(user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        if(usersService.getUser(id).getFolders().get(folderName) == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        System.out.println(foldersServices.getMails(id, folderName, usersService.getUsersDB()));

        return foldersServices.getMails(id, folderName, usersService.getUsersDB());
    }

    //add mail to folder
    @PostMapping ("/folders/add")
    public void add (@RequestParam String id, @RequestParam String folderName, @RequestParam List<String> ids){
        User user = usersService.getUser(id);

        if(user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        if(!usersService.getUser(id).getFolders().containsKey(folderName))
            foldersServices.createFolder(id, folderName, usersService.getUsersDB());

        foldersServices.addMails(id, folderName, ids, usersService.getUsersDB());


    }
    //rename folder
    @PutMapping ("/folders/rename")
    public void rename(@RequestParam String id, @RequestParam String oldFolderName, @RequestParam String newFolderName){
        if(usersService.getUser(id).getFolders().containsKey(newFolderName)) throw new ResponseStatusException(HttpStatus.CREATED);
        foldersServices.rename(id, oldFolderName, newFolderName, usersService.getUsersDB());
    }

    //delete folder
    @DeleteMapping ("/folders/delete")
    public void deleteFolder (@RequestParam String id, @RequestParam String folderName){
        Map<String, Mail> mails = foldersServices.deleteFolder(id, folderName, usersService.getUsersDB());

        if (mails == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    //delete mail
    @DeleteMapping ("/folders/mail/delete")
    public void deleteMail (@RequestParam String id, @RequestParam String folderName, @RequestParam List<String> ids){
        List<Mail> mails = foldersServices.deleteMails(id, folderName, ids, usersService.getUsersDB());

        for (Mail mail: mails)
            if (mail == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
