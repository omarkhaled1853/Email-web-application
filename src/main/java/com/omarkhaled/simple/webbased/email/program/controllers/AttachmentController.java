package com.omarkhaled.simple.webbased.email.program.controllers;


import com.omarkhaled.simple.webbased.email.program.classes.Attachment;
import com.omarkhaled.simple.webbased.email.program.services.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;

@RestController
public class AttachmentController {

    private final AttachmentService attachmentService;

    @Autowired
    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    //get attachments
    @GetMapping ("/attachments")
    public Collection<Attachment> get(){
        return attachmentService.getAttachments();
    }

    //create attachment
    @PostMapping ("/attachments")
    public Attachment create(@RequestPart("data") MultipartFile file) throws IOException {
        return attachmentService.uploadAttachment(file.getOriginalFilename(), file.getContentType(), file.getBytes());
    }

    //delete attachment
    @DeleteMapping ("/attachments/{id}")
    public void delete(@PathVariable String id){
        Attachment attachment = attachmentService.removeAttachment(id);
        //if attachment not found
        if(attachment == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

}
