package com.omarkhaled.simple.webbased.email.program.services;

import com.omarkhaled.simple.webbased.email.program.classes.Mail;
import com.omarkhaled.simple.webbased.email.program.classes.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class UsersService {
    //users data base
    private Map<String, User> usersDB = new HashMap<>();

    public Map<String, User> getUsersDB() {
        return usersDB;
    }

    public void setUsersDB(Map<String, User> usersDB) {
        this.usersDB = usersDB;
    }

    //build user
    public User buildUser(User user){
        return new User
                .Builder()
                .setEmail(user.getEmail())
                .setUserName(user.getUserName())
                .setPassword(user.getPassword())
                .setInbox(new HashMap<>())
                .setContacts(new HashMap<>())
                .setDraft(new HashMap<>())
                .setSent(new HashMap<>())
                .setTrash(new HashMap<>())
                .setFolders(new HashMap<>())
                .build();
    }

    //get users
    public Collection<User> getUsers(){
        return usersDB.values();
    }

    //add user
    public void addUser(User user){
        usersDB.put(user.getEmail(), user);
    }

    //get user
    public User getUser(String id){
        return usersDB.get(id);
    }

    //add in inbox
    public void addToInbox(String id, Mail mail){
        User user = usersDB.get(id);
        user.getInbox().put(mail.getId(), mail);
        usersDB.put(user.getEmail(), user);
    }

    //update draft
    public void updateDraft(String id, Map<String, Mail> updatedDraft){
        User user = usersDB.get(id);
        user.getDraft().clear();
        user.getDraft().putAll(updatedDraft);
        usersDB.put(user.getEmail(), user);
    }

    //update sent
    public void updateSent(String id, Map<String, Mail> updatedSent){
        User user = usersDB.get(id);
        user.getSent().clear();
        user.getSent().putAll(updatedSent);
        usersDB.put(user.getEmail(), user);
    }

    //update inbox
    public void updateInbox(String id, Map<String, Mail> updatedInbox){
        User user = usersDB.get(id);
        user.getInbox().clear();
        user.getInbox().putAll(updatedInbox);
        usersDB.put(user.getEmail(), user);
    }

    //update trash
    public void updateTrash(String id, Map<String, Mail> updatedTrash){
        User user = usersDB.get(id);
        user.getTrash().clear();
        user.getTrash().putAll(updatedTrash);
        usersDB.put(user.getEmail(), user);
    }
}
