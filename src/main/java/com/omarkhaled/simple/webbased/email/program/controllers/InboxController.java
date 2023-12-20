package com.omarkhaled.simple.webbased.email.program.controllers;

import com.omarkhaled.simple.webbased.email.program.classes.Mail;
import com.omarkhaled.simple.webbased.email.program.services.InboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping
@CrossOrigin
public class InboxController {
    private final InboxService inboxService;

    @Autowired
    public InboxController(InboxService inboxService) {
        this.inboxService = inboxService;
    }

    //get mails
    @GetMapping ("/mail/inbox")
    public Collection<Mail> get(){
        return inboxService.getMails();
    }

}
