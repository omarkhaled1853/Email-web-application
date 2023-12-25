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

    //delete mails
    public List<Mail> deleteMails(String id, List<String> ids, Map<String, User> usersDB){
        List<Mail> mails = new ArrayList<>();
        for (String maiId : ids){
            mails.add(usersDB.get(id).getInbox().remove(maiId));
        }
        return mails;
    }

}
