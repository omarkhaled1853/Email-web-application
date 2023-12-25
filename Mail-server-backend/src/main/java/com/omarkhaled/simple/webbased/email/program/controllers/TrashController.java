package com.omarkhaled.simple.webbased.email.program.controllers;

import com.omarkhaled.simple.webbased.email.program.classes.Mail;
import com.omarkhaled.simple.webbased.email.program.classes.User;
import com.omarkhaled.simple.webbased.email.program.services.TrashService;
import com.omarkhaled.simple.webbased.email.program.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin
public class TrashController {

    private final UsersService usersService;
    private final TrashService trashService;

    @Autowired
    public TrashController(UsersService usersService, TrashService trashService) {
        this.usersService = usersService;
        this.trashService = trashService;
    }

    //get trashed mails
    @GetMapping ("/mails/trash")
    public Collection<Mail> getTrash (@RequestParam String id){
        User user = usersService.getUser(id);

        if(user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return trashService.getMails(id, usersService.getUsersDB());
    }

    //delete trashed mail
    @DeleteMapping ("/mail/delete")
    public void delete(@RequestParam String id, @RequestParam List<String> ids){
        List<Mail> mails = trashService.deleteMails(id, ids, usersService.getUsersDB());

        for (Mail mail : mails)
            if(mail == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
