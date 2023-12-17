package com.omarkhaled.simple.webbased.email.program.services;

import com.omarkhaled.simple.webbased.email.program.classes.User;
import com.omarkhaled.simple.webbased.email.program.helperClasses.UserHelper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    //userEmail mapping id
    private Map<UserHelper, String> usersMap = new HashMap<>();

    //users data base
    private Map<String, User> usersDB = new HashMap<>();

    public Map<UserHelper, String>  getUserMap() {
        return usersMap;
    }

    public void setUserMap(Map<UserHelper, String> usersMap) {
        this.usersMap = usersMap;
    }

    public Map<String, User> getUsersDB() {
        return usersDB;
    }

    public void setUsersDB(Map<String, User> usersDB) {
        this.usersDB = usersDB;
    }

    //get users
    public Collection<User> getUsers(){
        return usersDB.values();
    }

    //mapping userEmail to id
    public void mapping(User user){
        UserHelper userHelper = new UserHelper();
        userHelper.setEmail(user.getEmail());
        userHelper.setPassword(user.getPassword());
        usersMap.put(userHelper, user.getId());
    }

    //add user
    public void addUser(User user){
        usersDB.put(user.getId(), user);
    }

    //get mapping
    public String getMapping(UserHelper userHelper){
        return usersMap.get(userHelper);
    }

    //get user
    public User getUser(String id){
        return usersDB.get(id);
    }

}
