package com.omarkhaled.simple.webbased.email.program.services;

import com.omarkhaled.simple.webbased.email.program.classes.Mail;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class TrashService {
    Map<String, Mail> trashDB = new HashMap<>();

    public Map<String, Mail> getTrashDB() {
        return trashDB;
    }

    public void setTrashDB(Map<String, Mail> trashDB) {
        this.trashDB = trashDB;
    }

    public List<Mail> buildMails(List<Mail> mails){
        mails.replaceAll(mail -> new Mail
                .Builder()
                .setId(mail.getId())
                .setReceiver(mail.getReceiver())
                .setSender(mail.getSender())
                .setSubject(mail.getSubject())
                .setContent(mail.getContent())
                .setAttachments(mail.getAttachments())
                .setPriority(mail.getPriority())
                .setDate(LocalDateTime.now())
                .setCreateOn(Clock.systemDefaultZone().millis())
                .setDestroyOn((long) (Clock.systemDefaultZone().millis() + 2.592 * 1e9))
                .build());
        return mails;
    }

    //add mail
    public void addMails(List<Mail> mails){
        for (Mail mail : mails)
            trashDB.put(mail.getId(), mail);
    }

    //delete mail
    public List<Mail> deleteMails(List<String> ids){
        List<Mail> mails = new ArrayList<>();
        for (String id : ids){
            mails.add(trashDB.remove(id));
        }
        return mails;
    }
}
