package com.omarkhaled.simple.webbased.email.program.classes;

import com.omarkhaled.simple.webbased.email.program.interfaces.Criteria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PriorityCriteria implements Criteria {
    private final int priority;

    public PriorityCriteria(int priority) {
        this.priority = priority;
    }
    @Override
    public Collection<Mail> filter(Collection<Mail> mails) {
        Collection<Mail> mails1 = new ArrayList<>();
        for (Mail mail: mails){
            if(mail.getPriority() == (priority))
                mails1.add(mail);
        }
        return mails1;
    }

}
