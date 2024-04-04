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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
@CrossOrigin
public class DraftController {
    private final UsersService usersService;
    private final DraftService draftService;
    private final TrashService trashService;

    private final SortingService sortingService;

    private final SearchingService searchingService;

    public final AttachmentService attachmentService;

    @Autowired
    public DraftController(UsersService usersService, DraftService draftService, TrashService trashService, SortingService sortingService, SearchingService searchingService, AttachmentService attachmentService) {
        this.usersService = usersService;
        this.draftService = draftService;
        this.trashService = trashService;
        this.sortingService = sortingService;
        this.searchingService = searchingService;
        this.attachmentService = attachmentService;
    }

    //get mails
    @GetMapping ("/mails/draft")
    public Collection<Mail> get(@RequestParam String id){
        User user = usersService.getUser(id);

        if(user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return draftService.getMails(id, usersService.getUsersDB());
    }

    //get attachments
    @PostMapping ("/mail/draft/attachments")
    public List<Attachment> getAttachments(@RequestPart("data") List<MultipartFile> files) throws IOException {
        return attachmentService.getAttachments(files);
    }

    //draft mail
    @PostMapping ("/mail/draft/create")
    public void create(@RequestBody Mail mail){
        Mail mail1 = draftService.buildMail(mail);
        draftService.draftMail(mail1, usersService.getUsersDB());
    }

    //download attachment
    @GetMapping ("/mail/attachments/draft/download")
    public ResponseEntity<byte[]> download(@RequestParam String id, @RequestParam String mailId, @RequestParam String attachmentID){
        Mail mail = draftService.getMail(id, mailId, usersService.getUsersDB());
        Attachment attachment = attachmentService.getAttachment(mail, attachmentID);
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
    @PostMapping ("/mails/draft/sort")
    public Collection<Mail> sortInbox(@RequestParam String id, @RequestParam String type){
        Collection<Mail> mails = draftService.getMails(id, usersService.getUsersDB());
        sortingService.setStrategy(type);
        return sortingService.getMails(mails);
    }

    //search mails
    @GetMapping ("/mails/draft/search")
    public Collection<Mail> searchInbox(@RequestParam String id, @RequestParam String type, @RequestParam String keyWord){
        Collection<Mail> mails = draftService.getMails(id, usersService.getUsersDB());
        searchingService.setStrategy(type);
        return searchingService.getMails(keyWord, mails);
    }

    //trash mail
    @DeleteMapping ("/mail/draft/trash")
    public void trash(@RequestParam String id, @RequestParam List<String> ids){
        List<Mail> mails = draftService.deleteMails(id, ids, usersService.getUsersDB());

        System.out.println(mails);

        for (Mail mail : mails)
            if(mail == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        System.out.println(mails);
        trashService.addMails(id, mails, usersService.getUsersDB());
    }
}
