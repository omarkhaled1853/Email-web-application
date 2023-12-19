package com.omarkhaled.simple.webbased.email.program.services;

import com.omarkhaled.simple.webbased.email.program.classes.Mail;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class DraftService {
    Map<String, Mail> draftDB = new HashMap<>();

    public Map<String, Mail> getDraftDB() {
        return draftDB;
    }

    public void setDraftDB(Map<String, Mail> draftDB) {
        this.draftDB = draftDB;
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

    //draft mail
    public void draftMail(Mail mail){
        draftDB.put(mail.getId(), mail);
    }

    //delete mail
    public List<Mail> deleteMails(List<String> ids){
        List<Mail> mails = new ArrayList<>();
        for (String id : ids){
            mails.add(draftDB.remove(id));
        }
        return mails;
    }
}
