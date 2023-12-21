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

    //get trashed mails
    public Collection<Mail> getMails(){
        return trashDB.values();
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
