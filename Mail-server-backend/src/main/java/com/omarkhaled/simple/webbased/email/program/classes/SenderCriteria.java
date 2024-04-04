package com.omarkhaled.simple.webbased.email.program.classes;

import com.omarkhaled.simple.webbased.email.program.interfaces.Criteria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SenderCriteria implements Criteria {

    private final String sender;

    public SenderCriteria(String sender) {
        this.sender = sender;
    }

    @Override
    public Collection<Mail> filter(Collection<Mail> mails) {
        Collection<Mail> mails1 = new ArrayList<>();
        for (Mail mail: mails){
            if(mail.getSender().equals(sender))
                mails1.add(mail);
        }
        return mails1;
    }
}
