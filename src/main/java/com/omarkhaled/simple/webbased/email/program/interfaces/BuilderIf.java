package com.omarkhaled.simple.webbased.email.program.interfaces;

import com.omarkhaled.simple.webbased.email.program.classes.Mail;
import com.omarkhaled.simple.webbased.email.program.classes.Attachment;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface BuilderIf {
    Mail build();
    BuilderIf setId(String id);

    BuilderIf setSender(String sender);
    BuilderIf setReceiver(String receiver);
    BuilderIf setContent(String content);
    BuilderIf setSubject(String subject);
    BuilderIf setDate(LocalDateTime date);
    BuilderIf setAttachments(ArrayList<Attachment> attachments);
    BuilderIf setPriority(int priority);

    BuilderIf setCreateOn(long createOn);

    BuilderIf setDestroyOn(long destroyOn);
}

