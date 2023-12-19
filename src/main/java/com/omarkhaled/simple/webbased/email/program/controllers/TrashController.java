package com.omarkhaled.simple.webbased.email.program.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omarkhaled.simple.webbased.email.program.classes.Mail;
import com.omarkhaled.simple.webbased.email.program.services.SentService;
import com.omarkhaled.simple.webbased.email.program.services.TrashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin
public class TrashController {

    private final TrashService trashService;
    private final SentService sentService;

    @Autowired
    public TrashController(TrashService trashService, SentService sentService) {
        this.trashService = trashService;
        this.sentService = sentService;
    }

    //trash mail
    @DeleteMapping ("/mail/trash/{list}")
    public void trash(@PathVariable String list) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<String> ids = mapper.readValue(list, List.class);
        List<Mail> mails = sentService.deleteMails(ids);

        for (Mail mail : mails)
            if(mail == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        mails = trashService.buildMails(mails);

        trashService.addMails(mails);
    }

//    //restore trashed mail
//    @GetMapping ("/mail/restore/{id}")
//    public void restore(@PathVariable String id){
//        Mail mail = trashService.deleteMail(id);
//
//        if(mail == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//
//        mailService.addMail(mail);
//    }

    //delete trashed mail
    @DeleteMapping ("/mail/delete/{list}")
    public void delete(@PathVariable String list) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<String> ids = mapper.readValue(list, List.class);

        List<Mail> mails = trashService.deleteMails(ids);

        for (Mail mail : mails)
            if(mail == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
