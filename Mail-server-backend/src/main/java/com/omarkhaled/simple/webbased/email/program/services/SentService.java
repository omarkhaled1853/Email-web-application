package com.omarkhaled.simple.webbased.email.program.services;

import com.omarkhaled.simple.webbased.email.program.classes.Attachment;
import com.omarkhaled.simple.webbased.email.program.classes.Mail;
import com.omarkhaled.simple.webbased.email.program.classes.User;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class SentService {
    //build mail
    public Mail buildMail(Mail mail, String receiver){
        return new Mail
                .Builder()
                .setId(UUID.randomUUID().toString())
                .setReceiver(receiver)
                .setSender(mail.getSender())
                .setSubject(mail.getSubject())
                .setContent(mail.getContent())
                .setAttachments(mail.getAttachments())
                .setPriority(mail.getPriority())
                .setDate(LocalDateTime.now().toString())
                .build();
    }

    //get mails
    public Collection<Mail> getMails(String id, Map<String, User> usersDB){
        return usersDB.get(id).getSent().values();
    }

    //add mail
    public void addMail(Mail mail, Map<String, User> usersDB){
        usersDB.get(mail.getSender()).getSent().put(mail.getId(), mail);
    }

    //delete mails
    public List<Mail> deleteMails(String id, List<String> ids, Map<String, User> usersDB){
        List<Mail> mails = new ArrayList<>();
        final long days = (long) (2.592 * 1e9);
        for (String maiId : ids){
            Mail mail = usersDB.get(id).getSent().remove(maiId);
            Mail mailDestroyOn = new Mail
                    .Builder()
                    .setId(mail.getId())
                    .setReceiver(mail.getReceiver())
                    .setSender(mail.getSender())
                    .setSubject(mail.getSubject())
                    .setContent(mail.getContent())
                    .setAttachments(mail.getAttachments())
                    .setPriority(mail.getPriority())
                    .setDate(mail.getDate())
                    .setDestroyOn(System.currentTimeMillis() + days)
                    .build();
            mails.add(mailDestroyOn);
        }
        return mails;
    }


    //get mail
    public Mail getMail(String id, String mailId, Map<String, User> usersDB) {
        return usersDB.get(id).getSent().get(mailId);
    }
}
