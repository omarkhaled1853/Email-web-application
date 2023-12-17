package com.omarkhaled.simple.webbased.email.program.classes;

import com.omarkhaled.simple.webbased.email.program.interfaces.BuilderIf;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Mail {
    private String id;
    private String sender;
    private String receiver;
    private String content;
    private String subject;
    private LocalDateTime date;
    private ArrayList<Attachment> attachments;
    private int priority;

    private long createOn;
    private long destroyOn;

    public String getId() {
        return id;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }

    public String getSubject() {
        return subject;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public ArrayList<Attachment> getAttachments() {
        return attachments;
    }

    public int getPriority() {
        return priority;
    }

    public long getCreateOn() {
        return createOn;
    }

    public long getDestroyOn() {
        return destroyOn;
    }

    public static class Builder implements BuilderIf {

        Mail mail = new Mail();
        @Override
        public Mail build() {
            return mail;
        }

        @Override
        public BuilderIf setId(String id) {
            mail.id = id;
            return this;
        }

        @Override
        public BuilderIf setSender(String sender) {
            mail.sender = sender;
            return this;
        }

        @Override
        public BuilderIf setReceiver(String receiver) {
            mail.receiver = receiver;
            return this;
        }

        @Override
        public BuilderIf setContent(String content) {
            mail.content = content;
            return this;
        }

        @Override
        public BuilderIf setSubject(String subject) {
            mail.subject = subject;
            return this;
        }

        @Override
        public BuilderIf setDate(LocalDateTime date) {
            mail.date = date;
            return this;
        }

        @Override
        public BuilderIf setAttachments(ArrayList<Attachment> attachments) {
            mail.attachments = attachments;
            return this;
        }

        @Override
        public BuilderIf setPriority(int priority) {
            mail.priority = priority;
            return this;
        }

        @Override
        public BuilderIf setCreateOn(long createOn) {
            mail.createOn = createOn;
            return this;
        }

        @Override
        public BuilderIf setDestroyOn(long destroyOn) {
            mail.destroyOn = destroyOn;
            return this;
        }
    }
}
