package com.omarkhaled.simple.webbased.email.program.services;

import com.omarkhaled.simple.webbased.email.program.classes.Mail;
import com.omarkhaled.simple.webbased.email.program.classes.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FoldersServices {
    //get folders
    public Collection<String> getFolders(String id, Map<String, User> usersDB){
        return usersDB.get(id).getFolders().keySet();
    }


    //get folder mails
    public Collection<Mail> getMails(String id, String folderName, Map<String, User> usersDB){
        return usersDB.get(id).getFolders().get(folderName).values();
    }

    //create folder
    public void createFolder(String id, String folderName, Map<String, User> usersDB){
        usersDB.get(id).getFolders().put(folderName, new HashMap<>());
    }

    //add mails
    public void addMails(String id, String folderName, List<String> ids, Map<String, User> usersDB){
        Map<String, Mail> mails = usersDB.get(id).getInbox();
        for (String mailId : ids)
            if (mails.containsKey(mailId))
                usersDB.get(id).getFolders().get(folderName).put(mailId, mails.get(mailId));
    }

    //delete folder
    public Map<String, Mail> deleteFolder(String id, String folderName, Map<String, User> usersDB){
        return usersDB.get(id).getFolders().remove(folderName);
    }

    //delete mails from specific folder
    public List<Mail> deleteMails(String id, String folderName, List<String> ids, Map<String, User> usersDB){
        List<Mail> mails = new ArrayList<>();
        for (String maiId : ids){
            mails.add(usersDB.get(id).getFolders().get(folderName).remove(maiId));
        }
        return mails;
    }
}
