package com.omarkhaled.simple.webbased.email.program.services;


import com.omarkhaled.simple.webbased.email.program.classes.Attachment;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

@Service
public class AttachmentService {

    private Map<String, Attachment> attachmentMap;

    public Map<String, Attachment> getAttachmentMap() {
        return attachmentMap;
    }

    public void setAttachmentMap(Map<String, Attachment> attachmentMap) {
        this.attachmentMap = attachmentMap;
    }

    public Collection<Attachment> getAttachments() {
        return attachmentMap.values();
    }

    public Attachment getAttachment(String id){
        return attachmentMap.get(id);
    }

    public Attachment removeAttachment(String id){
        return attachmentMap.remove(id);
    }

    public Attachment uploadAttachment(String fileName, String contentType, byte[] data){
        Attachment attachment = new Attachment();
        attachment.setId(UUID.randomUUID().toString());
        attachment.setAttachmentName(fileName);
        attachment.setData(data);
        attachment.setContentType(contentType);
        attachmentMap.put(attachment.getId(), attachment);
        return attachment;
    }

}
