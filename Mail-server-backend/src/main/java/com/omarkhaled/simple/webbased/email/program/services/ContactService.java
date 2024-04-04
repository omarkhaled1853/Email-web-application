package com.omarkhaled.simple.webbased.email.program.services;

import com.omarkhaled.simple.webbased.email.program.classes.Mail;
import com.omarkhaled.simple.webbased.email.program.classes.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ContactService {

    //get contacts
    public Map<String, List<String>> getContacts(String id, Map<String, User> usersDB){
        return usersDB.get(id).getContacts();
    }

    //get contact
    public List<String> getContact(String id, String name, Map<String, User> usersDB){
        return usersDB.get(id).getContacts().get(name);
    }

    //create name
    public void createName(String id, String email, String name, Map<String, User> usersDB){
        usersDB.get(id).getContacts().put(name, new ArrayList<>());

    }

    //add email
    public void addEmail(String id, String email, String name, Map<String, User> usersDB){
        usersDB.get(id).getContacts().get(name).add(email);
    }

    //rename contact
    public void renameContact(String id, String oldContactName, String newContactName, Map<String, User> usersDB){
        List<String> mails = usersDB.get(id).getContacts().remove(oldContactName);
        usersDB.get(id).getContacts().put(newContactName, mails);
    }

    //delete contact
    public List<List<String>> deleteMails(String id, List<String> ids, Map<String, User> usersDB){
        List<List<String>> emails = new ArrayList<>();
        for (String name : ids){
            emails.add(usersDB.get(id).getContacts().remove(name));
        }
        return emails;
    }
}
