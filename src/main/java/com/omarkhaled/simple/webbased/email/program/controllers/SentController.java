package com.omarkhaled.simple.webbased.email.program.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.omarkhaled.simple.webbased.email.program.classes.Mail;
import com.omarkhaled.simple.webbased.email.program.classes.User;
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
    private final TrashService trashService;

    @Autowired
    public SentController(UsersService usersService, SentService sentService, TrashService trashService) {
        this.usersService = usersService;
        this.sentService = sentService;
        this.trashService = trashService;
    }

    //get mails
    @GetMapping ("/mails/sent")
    public Collection<Mail> getSent(@RequestParam String id){

        System.out.println(id);

        User user = usersService.getUser(id);

        if(user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        sentService.setSentDB(user.getSent());

        return sentService.getMails();
    }

    //add mail
    @PostMapping ("/mail/sent/create")
    public ResponseEntity<Boolean> create(@RequestBody Mail mail){
        User receiver = usersService.getUser(mail.getReceiver());

        if(receiver == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Mail mail1 = sentService.buildMail(mail);
        sentService.addMail(mail1);

        //update sent sender
        usersService.updateSent(mail.getSender(), sentService.getSentDB());

        //update inbox receiver
//        usersService.addToInbox(receiver.getEmail(), mail);

        usersService.getUsersDB().get(receiver.getEmail()).getInbox().put(mail1.getId(), mail1);

        return ResponseEntity.ok(true);
    }

    //trash mail
    @DeleteMapping ("/mail/sent/trash")
    public void trash(@RequestParam List<String> ids) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        List<String> ids = mapper.readValue(list, List.class);
        List<Mail> mails = sentService.deleteMails(ids);

        for (Mail mail : mails)
            if(mail == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        //update
        usersService.updateSent(mails.get(0).getSender(), sentService.getSentDB());

        trashService.addMails(mails);
    }
}
