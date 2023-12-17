package com.omarkhaled.simple.webbased.email.program.controllers;

import com.omarkhaled.simple.webbased.email.program.classes.Attachment;
import com.omarkhaled.simple.webbased.email.program.services.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DownloadController {
    private final AttachmentService attachmentService;

    @Autowired
    public DownloadController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    //download attachment
    @GetMapping ("download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable String id){
        Attachment attachment = attachmentService.getAttachment(id);
        //check if attachment not found
        if(attachment == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
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

}
