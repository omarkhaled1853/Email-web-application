package com.omarkhaled.simple.webbased.email.program.classes;

import com.omarkhaled.simple.webbased.email.program.interfaces.SortingStrategy;

import java.util.Collection;
import java.util.List;

public class ContentSort implements SortingStrategy {

    @Override
    public Collection<Mail> sorting(List<Mail> mails) {
        mails.sort((o1,o2) -> o2.getContent().compareTo(o1.getContent()));
        return mails;
    }
}
