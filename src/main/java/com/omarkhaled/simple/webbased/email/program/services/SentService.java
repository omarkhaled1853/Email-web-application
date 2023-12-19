package com.omarkhaled.simple.webbased.email.program.services;

import com.omarkhaled.simple.webbased.email.program.classes.Mail;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class SentService {
    Map<String, Mail> inboxDB = new HashMap<>();

    public Map<String, Mail> getInboxDB() {
        return inboxDB;
    }

    public void setInboxDB(Map<String, Mail> inboxDB) {
        this.inboxDB = inboxDB;
    }

    //build mail
    public Mail buildMail(Mail mail){
        return new Mail
                .Builder()
                .setId(UUID.randomUUID().toString())
                .setReceiver(mail.getReceiver())
                .setSender(mail.getSender())
                .setSubject(mail.getSubject())
                .setContent(mail.getContent())
                .setAttachments(mail.getAttachments())
                .setPriority(mail.getPriority())
                .setDate(LocalDateTime.now())
                .build();
    }

    //get mails
    public Collection<Mail> getMails(){
        return inboxDB.values();
    }

    //add mail
    public void addMail(Mail mail){
        inboxDB.put(mail.getId(), mail);
    }

    //delete mails from inbox
    public List<Mail> deleteMails(List<String> ids){
        List<Mail> mails = new ArrayList<>();
        for (String id : ids){
            mails.add(inboxDB.remove(id));
        }
        return mails;
    }
}
