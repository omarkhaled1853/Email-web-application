package com.omarkhaled.simple.webbased.email.program.classes;

import com.omarkhaled.simple.webbased.email.program.interfaces.SearchingStrategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SubjectSearch implements SearchingStrategy {

    @Override
    public Collection<Mail> searching(String keyWord, Collection<Mail> mails) {
        Collection<Mail> SearchedMails = new ArrayList<>();
        for(Mail mail: mails){
            if(mail.getSubject().equals(keyWord))
                SearchedMails.add(mail);
        }
        return SearchedMails;
    }
}
