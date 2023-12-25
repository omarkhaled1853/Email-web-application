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

}
