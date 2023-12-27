package com.omarkhaled.simple.webbased.email.program.classes;

import com.omarkhaled.simple.webbased.email.program.interfaces.MailBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class Mail {
    private String id;
    private String sender;
    private String receiver;
    private String subject;
    private String content;
    private String date;
    private int priority;
    private Map<String, Attachment> attachments;

    private long createOn;
    private long destroyOn;

    @Override
    public String toString() {
        return "Mail{" +
                "id='" + id + '\'' +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", content='" + content + '\'' +
                ", subject='" + subject + '\'' +
                ", date=" + date +
                ", attachments=" + attachments +
                ", priority=" + priority +
                ", createOn=" + createOn +
                ", destroyOn=" + destroyOn +
                '}';
    }

    public String getId() {return id;}

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public int getPriority() {
        return priority;
    }

    public Map<String, Attachment> getAttachments() {
        return attachments;
    }

    public long getCreateOn() {
        return createOn;
    }

    public long getDestroyOn() {
        return destroyOn;
    }

    public static class Builder implements MailBuilder {

        Mail mail = new Mail();
        @Override
        public Mail build() {
            return mail;
        }

        @Override
        public MailBuilder setId(String id) {
            mail.id = id;
            return this;
        }

        @Override
        public MailBuilder setSender(String sender) {
            mail.sender = sender;
            return this;
        }

        @Override
        public MailBuilder setReceiver(String receiver) {
            mail.receiver = receiver;
            return this;
        }

        @Override
        public MailBuilder setContent(String content) {
            mail.content = content;
            return this;
        }

        @Override
        public MailBuilder setSubject(String subject) {
            mail.subject = subject;
            return this;
        }

        @Override
        public MailBuilder setDate(String date) {
            mail.date = date;
            return this;
        }

        @Override
        public MailBuilder setAttachments(Map<String, Attachment> attachments) {
            mail.attachments = attachments;
            return this;
        }

        @Override
        public MailBuilder setPriority(int priority) {
            mail.priority = priority;
            return this;
        }

        @Override
        public MailBuilder setCreateOn(long createOn) {
            mail.createOn = createOn;
            return this;
        }

        @Override
        public MailBuilder setDestroyOn(long destroyOn) {
            mail.destroyOn = destroyOn;
            return this;
        }
    }
}
