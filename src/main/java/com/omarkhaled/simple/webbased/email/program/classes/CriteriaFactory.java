package com.omarkhaled.simple.webbased.email.program.classes;

import com.omarkhaled.simple.webbased.email.program.interfaces.Criteria;

public class CriteriaFactory {
    public Criteria getCriteria(String criteria, String typedCriteria){
        return switch (criteria) {
            case "Subject" -> new SubjectCriteria(typedCriteria);
            case "Sender" -> new SenderCriteria(typedCriteria);
            case "Priority" -> new PriorityCriteria(Integer.parseInt(typedCriteria));
            default -> null;
        };
    }
}
