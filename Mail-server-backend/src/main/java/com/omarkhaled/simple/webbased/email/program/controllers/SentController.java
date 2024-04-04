package com.omarkhaled.simple.webbased.email.program.controllers;

import com.omarkhaled.simple.webbased.email.program.classes.Attachment;
import com.omarkhaled.simple.webbased.email.program.classes.Mail;
import com.omarkhaled.simple.webbased.email.program.classes.User;
import com.omarkhaled.simple.webbased.email.program.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping
@CrossOrigin
public class SentController {
    private final UsersService usersService;
    private final SentService sentService;

    private final InboxService inboxService;
    private final TrashService trashService;

    private final SortingService sortingService;

    private final SearchingService searchingService;

    private final AttachmentService attachmentService;

    @Autowired
    public SentController(UsersService usersService, SentService sentService, InboxService inboxService, TrashService trashService, SortingService sortingService, SearchingService searchingService, AttachmentService attachmentService) {
        this.usersService = usersService;
        this.sentService = sentService;
        this.inboxService = inboxService;
        this.trashService = trashService;
        this.sortingService = sortingService;
        this.searchingService = searchingService;
        this.attachmentService = attachmentService;
    }

    //get mails
    @GetMapping ("/mails/sent")
    public Collection<Mail> getSent(@RequestParam String id){

        User user = usersService.getUser(id);

        if(user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return sentService.getMails(id, usersService.getUsersDB());
    }

    //get attachments
    @PostMapping ("/mail/sent/attachments")
    public List<Attachment> getAttachments(@RequestPart("data") List<MultipartFile> files) throws IOException {
        return attachmentService.getAttachments(files);
    }

    //add mail
    @PostMapping ("/mail/sent/create")
    public ResponseEntity<Boolean> create(@RequestBody Mail mail){
        String[] receivers = mail.getReceiver().split("-");
        for(String receiver: receivers) {
            if (receiver.equals(mail.getSender())) throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
            User receiverUser = usersService.getUser(receiver);
            if (receiverUser == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            Mail mail1 = sentService.buildMail(mail, receiver);
            sentService.addMail(mail1, usersService.getUsersDB());
            //update inbox receiver
            inboxService.addMail(mail1, usersService.getUsersDB());
        }
        return ResponseEntity.ok(true);
    }


    //download attachment
    @GetMapping ("/mail/attachments/sent/download")
    public ResponseEntity<byte[]> download(@RequestParam String id, @RequestParam String mailId, @RequestParam String attachmentId){
        Mail mail = sentService.getMail(id, mailId, usersService.getUsersDB());
        Attachment attachment = attachmentService.getAttachment(mail, attachmentId);
        //check if attachment not found
        if (attachment == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        //data of attachment
        byte[] data = attachment.getData();
        //headers of attachment
        HttpHeaders headers = new HttpHeaders();
        //set content type of attachment
        headers.setContentType(MediaType.valueOf(attachment.getContentType()));
        //build content disposition
        ContentDisposition build = ContentDisposition
                .builder("attachment")
                .filename(attachment.getAttachmentName())
                .build();
        //set content disposition builder for header
        headers.setContentDisposition(build);

        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

    //sort mails
    @GetMapping ("/mails/sent/sort")
    public Collection<Mail> sortInbox(@RequestParam String id, @RequestParam String type){
        Collection<Mail> mails = sentService.getMails(id, usersService.getUsersDB());
        sortingService.setStrategy(type);
        return sortingService.getMails(mails);
    }

    //search mails
    @GetMapping ("mails/sent/search")
    public Collection<Mail> searchInbox(@RequestParam String id, @RequestParam String type, @RequestParam String keyWord){
        Collection<Mail> mails = sentService.getMails(id, usersService.getUsersDB());
        searchingService.setStrategy(type);
        return searchingService.getMails(keyWord, mails);
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
