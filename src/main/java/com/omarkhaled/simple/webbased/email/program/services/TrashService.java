package com.omarkhaled.simple.webbased.email.program.services;

import com.omarkhaled.simple.webbased.email.program.classes.Mail;
import com.omarkhaled.simple.webbased.email.program.classes.User;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class TrashService {

    //get trashed mails
    public Collection<Mail> getMails(String id, Map<String, User> usersDB){
        return usersDB.get(id).getTrash().values();
    }

    //add mail
    public void addMails(String id, List<Mail> mails, Map<String, User> usersDB){
        for (Mail mail : mails)
            usersDB.get(id).getTrash().put(mail.getId(), mail);
    }

    //delete mail
    public List<Mail> deleteMails(String id, List<String> ids, Map<String, User> usersDB){
        List<Mail> mails = new ArrayList<>();
        for (String maiId : ids){
            mails.add(usersDB.get(id).getTrash().remove(maiId));
        }
        return mails;
    }
}
