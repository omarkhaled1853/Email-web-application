package com.omarkhaled.simple.webbased.email.program.services;

import com.omarkhaled.simple.webbased.email.program.classes.Mail;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
}
