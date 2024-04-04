package com.omarkhaled.simple.webbased.email.program.services;


import com.omarkhaled.simple.webbased.email.program.classes.Attachment;
import com.omarkhaled.simple.webbased.email.program.classes.Mail;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class AttachmentService {

    //build attachment
    public Attachment buildAttachment(String fileName, String contentType, byte[] data){
        return new Attachment
                .Builder()
                .setId(UUID.randomUUID().toString())
                .setAttachmentName(fileName)
                .setContentType(contentType)
                .setData(data)
                .build();
    }

    //get attachments
    public List<Attachment> getAttachments(List<MultipartFile> files) throws IOException {
        List<Attachment> attachments = new ArrayList<>();
        for(MultipartFile file: files){
            Attachment attachment = buildAttachment(file.getOriginalFilename(), file.getContentType(), file.getBytes());
            attachments.add(attachment);
        }
        return attachments;
    }

    //get attachment
    public Attachment getAttachment(Mail mail, String attachmentId){
        List<Attachment> attachments = mail.getAttachments();
        for (Attachment attachment: attachments){
            if(attachment.getId().equals(attachmentId))
                return attachment;
        }
        return null;
    }

}
