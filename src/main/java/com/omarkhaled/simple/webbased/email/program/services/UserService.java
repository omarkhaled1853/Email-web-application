package com.omarkhaled.simple.webbased.email.program.services;

import com.omarkhaled.simple.webbased.email.program.classes.Mail;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    Map<String, Mail> mailDB = new HashMap<>();
    Map<String, Mail> trashDB = new HashMap<>();

    Map<String, Mail> draftDB = new HashMap<>();


    public Map<String, Mail> getDraftDB() {
        return draftDB;
    }

    public void setDraftDB(Map<String, Mail> draftDB) {
        this.draftDB = draftDB;
    }

    public Map<String, Mail> getTrashDB() {
        return trashDB;
    }

    public void setTrashDB(Map<String, Mail> trashDB) {
        this.trashDB = trashDB;
    }

    public Map<String, Mail> getMailDB() {
        return mailDB;
    }

    public void setMailDB(Map<String, Mail> mailDB) {
        this.mailDB = mailDB;
    }


}
