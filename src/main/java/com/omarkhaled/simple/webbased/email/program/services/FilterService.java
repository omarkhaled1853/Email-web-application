package com.omarkhaled.simple.webbased.email.program.services;

import com.omarkhaled.simple.webbased.email.program.classes.AndCriteria;
import com.omarkhaled.simple.webbased.email.program.classes.CriteriaFactory;
import com.omarkhaled.simple.webbased.email.program.classes.Mail;
import com.omarkhaled.simple.webbased.email.program.interfaces.Criteria;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FilterService {

    //get filter
    public Collection<Mail> getFilter(String[] criteria, String[] keyWords, Collection<Mail> mails){
        Criteria criteria1 = new CriteriaFactory().getCriteria(criteria[0], keyWords[0]);
        Criteria criteria2 = new CriteriaFactory().getCriteria(criteria[1], keyWords[1]);
        AndCriteria andCriteria = new AndCriteria(criteria1, criteria2);
        return andCriteria.filter(mails);
    }
}
