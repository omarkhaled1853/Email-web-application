package com.omarkhaled.simple.webbased.email.program.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.omarkhaled.simple.webbased.email.program.classes.Mail;
import com.omarkhaled.simple.webbased.email.program.classes.User;
import com.omarkhaled.simple.webbased.email.program.services.InboxService;
import com.omarkhaled.simple.webbased.email.program.services.TrashService;
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
public class InboxController {
    private final UsersService usersService;
    private final InboxService inboxService;

    private final TrashService trashService;

    @Autowired
    public InboxController(UsersService usersService, InboxService inboxService, TrashService trashService) {
        this.usersService = usersService;
        this.inboxService = inboxService;
        this.trashService = trashService;
    }

    //get mails
    @GetMapping ("/mail/inbox")
    public Collection<Mail> getInbox (@RequestParam String id){
        System.out.println(id);

        User user = usersService.getUser(id);

        if(user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        inboxService.setInboxDB(user.getInbox());

        System.out.println(inboxService.getMails());

        return inboxService.getMails();
    }

    //trash mail
    @DeleteMapping ("/mail/inbox/trash")
    public void trash(@RequestParam List<String> ids) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        List<String> ids = mapper.readValue(list, List.class);
        List<Mail> mails = inboxService.deleteMails(ids);

        for (Mail mail : mails)
            if(mail == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        //update
        //update
        usersService.updateInbox(mails.get(0).getSender(), inboxService.getInboxDB());

        trashService.addMails(mails);
    }
}
