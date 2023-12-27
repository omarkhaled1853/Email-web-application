package com.omarkhaled.simple.webbased.email.program.classes;

import com.omarkhaled.simple.webbased.email.program.interfaces.SortingStrategy;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PrioritySort implements SortingStrategy {

    @Override
    public Collection<Mail> sorting(List<Mail> mails) {
        mails.sort(Comparator.comparing(Mail::getPriority));
        Collections.reverse(mails);
        return mails;
    }
}
