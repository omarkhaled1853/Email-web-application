package com.omarkhaled.simple.webbased.email.program.controllers;

import com.omarkhaled.simple.webbased.email.program.classes.Mail;
import com.omarkhaled.simple.webbased.email.program.classes.User;
import com.omarkhaled.simple.webbased.email.program.services.InboxService;
import com.omarkhaled.simple.webbased.email.program.services.SentService;
import com.omarkhaled.simple.webbased.email.program.services.TrashService;
import com.omarkhaled.simple.webbased.email.program.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin
public class SentController {
    private final UsersService usersService;
    private final SentService sentService;

    private final InboxService inboxService;
    private final TrashService trashService;

    @Autowired
    public SentController(UsersService usersService, SentService sentService, InboxService inboxService, TrashService trashService) {
        this.usersService = usersService;
        this.sentService = sentService;
        this.inboxService = inboxService;
        this.trashService = trashService;
    }

    //get mails
    @GetMapping ("/mails/sent")
    public Collection<Mail> getSent(@RequestParam String id){

        User user = usersService.getUser(id);

        if(user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return sentService.getMails(id, usersService.getUsersDB());
    }

    //add mail
    @PostMapping ("/mail/sent/create")
    public ResponseEntity<Boolean> create(@RequestBody Mail mail){

        User receiver = usersService.getUser(mail.getReceiver());

        if(receiver == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Mail mail1 = sentService.buildMail(mail);
        sentService.addMail(mail1, usersService.getUsersDB());

        //update inbox receiver
        inboxService.addMail(mail1, usersService.getUsersDB());

        return ResponseEntity.ok(true);
    }

    //trash mail
    @DeleteMapping ("/mail/sent/trash")
    public void trash(@RequestParam String id, @RequestParam List<String> ids){
        List<Mail> mails = sentService.deleteMails(id, ids, usersService.getUsersDB());

        for (Mail mail : mails)
            if(mail == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        trashService.addMails(id, mails, usersService.getUsersDB());
    }
}
