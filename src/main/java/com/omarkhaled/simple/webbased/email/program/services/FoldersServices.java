package com.omarkhaled.simple.webbased.email.program.services;

import com.omarkhaled.simple.webbased.email.program.classes.Mail;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FoldersServices {
    Map<String, Map<String, Mail>> foldersDB = new HashMap<>();

    public Map<String, Map<String, Mail>> getFoldersDB() {
        return foldersDB;
    }

    public void setFoldersDB(Map<String, Map<String, Mail>> foldersDB) {
        this.foldersDB = foldersDB;
    }

    //delete folder
    public Map<String, Mail> deleteFolder(String name){
        return foldersDB.remove(name);
    }

    //delete mail from specific folder
    public Mail deleteMail(String name, String id){
        return foldersDB.get(name).remove(id);
    }
}
