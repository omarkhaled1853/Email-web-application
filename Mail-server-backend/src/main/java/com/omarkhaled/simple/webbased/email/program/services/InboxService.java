package com.omarkhaled.simple.webbased.email.program.services;

import com.omarkhaled.simple.webbased.email.program.classes.Mail;
import com.omarkhaled.simple.webbased.email.program.classes.User;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class InboxService {

    //add mail
    public void addMail(Mail mail, Map<String, User> usersDB){
        usersDB.get(mail.getReceiver()).getInbox().put(mail.getId(), mail);
    }

    //get mails
    public Collection<Mail> getMails(String id, Map<String, User> usersDB){
        return usersDB.get(id).getInbox().values();
    }

    //get mail
    public Mail getMail(String id, String mailId, Map<String, User> usersDB){
        return usersDB.get(id).getInbox().get(mailId);
    }

    //delete mails
    public List<Mail> deleteMails(String id, List<String> ids, Map<String, User> usersDB){        List<Mail> mails = new ArrayList<>();
        final long days = (long) (2.592 * 1e9);
        for (String maiId : ids){
            Mail mail = usersDB.get(id).getInbox().remove(maiId);
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

}
