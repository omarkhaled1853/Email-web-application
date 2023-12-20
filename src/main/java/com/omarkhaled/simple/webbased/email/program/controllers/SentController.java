package com.omarkhaled.simple.webbased.email.program.controllers;

import com.omarkhaled.simple.webbased.email.program.classes.Mail;
import com.omarkhaled.simple.webbased.email.program.classes.User;
import com.omarkhaled.simple.webbased.email.program.services.SentService;
import com.omarkhaled.simple.webbased.email.program.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
@RequestMapping
@CrossOrigin
public class SentController {
    private final SentService sentService;
    private final UsersService usersService;


    @Autowired
    public SentController(SentService sentService, UsersService usersService) {
        this.sentService = sentService;
        this.usersService = usersService;
    }


    //get mails
    @GetMapping ("/mails")
    public Collection<Mail> getMails(){
        return this.sentService.getMails();
    }

    //add mail
    @PostMapping ("/mail/create")
    public ResponseEntity<Boolean> create(@RequestBody Mail mail){

        User receiver = usersService.getUser(mail.getReceiver());

        if(receiver == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Mail mail1 = sentService.buildMail(mail);
        sentService.addMail(mail1);
        return ResponseEntity.ok(true);
    }
}
