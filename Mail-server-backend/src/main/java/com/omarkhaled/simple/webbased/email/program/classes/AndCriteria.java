package com.omarkhaled.simple.webbased.email.program.classes;

import com.omarkhaled.simple.webbased.email.program.interfaces.Criteria;

import java.util.Collection;
import java.util.List;

public class AndCriteria implements Criteria {
    private final Criteria criteria1;
    private final Criteria criteria2;

    public AndCriteria(Criteria criteria1, Criteria criteria2) {
        this.criteria1 = criteria1;
        this.criteria2 = criteria2;
    }

    @Override
    public Collection<Mail> filter(Collection<Mail> mails) {
        Collection<Mail> mails1 = criteria1.filter(mails);
        return criteria2.filter(mails1);
    }
}
