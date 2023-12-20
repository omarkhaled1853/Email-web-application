package com.omarkhaled.simple.webbased.email.program.services;

import com.omarkhaled.simple.webbased.email.program.classes.Mail;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class SentService {
    Map<String, Mail> sentDB = new HashMap<>();

    public Map<String, Mail> getInboxDB() {
        return sentDB;
    }

    public void setInboxDB(Map<String, Mail> sentDB) {
        this.sentDB = sentDB;
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
        return sentDB.values();
    }

    //add mail
    public void addMail(Mail mail){
        sentDB.put(mail.getId(), mail);
    }

    //delete mails from inbox
    public List<Mail> deleteMails(List<String> ids){
        List<Mail> mails = new ArrayList<>();
        for (String id : ids){
            mails.add(sentDB.remove(id));
        }
        return mails;
    }
}
