package com.omarkhaled.simple.webbased.email.program.services;

import com.omarkhaled.simple.webbased.email.program.classes.Mail;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class InboxService {

    Map<String, Mail> inboxDB = new HashMap<>();

    public Map<String, Mail> getInboxDB() {
        return inboxDB;
    }

    public void setInboxDB(Map<String, Mail> inboxDB) {
        this.inboxDB = inboxDB;
    }

    //get mails
    public Collection<Mail> getMails(){
        return inboxDB.values();
    }

    //delete mails
    public List<Mail> deleteMails(List<String> ids){
        List<Mail> mails = new ArrayList<>();
        for (String id : ids){
            mails.add(inboxDB.remove(id));
        }
        return mails;
    }
}
