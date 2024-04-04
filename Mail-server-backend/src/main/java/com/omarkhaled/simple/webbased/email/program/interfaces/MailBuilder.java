package com.omarkhaled.simple.webbased.email.program.interfaces;

import com.omarkhaled.simple.webbased.email.program.classes.Mail;
import com.omarkhaled.simple.webbased.email.program.classes.Attachment;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface MailBuilder {
    Mail build();
    MailBuilder setId(String id);

    MailBuilder setSender(String sender);
    MailBuilder setReceiver(String receiver);
    MailBuilder setContent(String content);
    MailBuilder setSubject(String subject);
    MailBuilder setDate(String date);
    MailBuilder setAttachments(List<Attachment> attachments);
    MailBuilder setPriority(int priority);

    MailBuilder setCreateOn(long createOn);

    MailBuilder setDestroyOn(long destroyOn);
}

