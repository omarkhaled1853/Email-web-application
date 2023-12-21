package com.omarkhaled.simple.webbased.email.program.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omarkhaled.simple.webbased.email.program.classes.Mail;
import com.omarkhaled.simple.webbased.email.program.classes.User;
import com.omarkhaled.simple.webbased.email.program.services.DraftService;
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
public class DraftController {
    private final UsersService usersService;
    private final DraftService draftService;
    private final TrashService trashService;

    @Autowired
    public DraftController(UsersService usersService, DraftService draftService, TrashService trashService) {
        this.usersService = usersService;
        this.draftService = draftService;
        this.trashService = trashService;
    }

    //get mails
    @GetMapping ("/mails/draft")
    public Collection<Mail> get(@RequestParam String id){
        User user = usersService.getUser(id);

        if(user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        draftService.setDraftDB(user.getDraft());

        return draftService.getMails();
    }

    //draft mail
    @PostMapping ("/mail/draft/create")
    public void create(@RequestBody Mail mail){
        draftService.draftMail(mail);

        //update
        usersService.updateDraft(mail.getSender(), draftService.getDraftDB());
    }

    //trash mail
    @DeleteMapping ("/mail/draft/trash")
    public void trash(@RequestParam List<String> ids) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        List<String> ids = mapper.readValue(list, List.class);
        List<Mail> mails = draftService.deleteMails(ids);

        for (Mail mail : mails)
            if(mail == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        //update
        usersService.updateDraft(mails.get(0).getSender(), draftService.getDraftDB());

        trashService.addMails(mails);
    }
}
