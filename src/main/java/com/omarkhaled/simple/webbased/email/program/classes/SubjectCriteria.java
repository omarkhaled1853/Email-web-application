package com.omarkhaled.simple.webbased.email.program.classes;

import com.omarkhaled.simple.webbased.email.program.interfaces.Criteria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SubjectCriteria implements Criteria {
   private String subject;

    public SubjectCriteria(String subject) {
        this.subject = subject;
    }

    @Override
    public Collection<Mail> filter(Collection<Mail> mails) {
        Collection<Mail> mails1 = new ArrayList<>();
        for (Mail mail: mails){
            if(mail.getSubject().equals(subject))
                mails1.add(mail);
        }
        return mails1;
    }
}
