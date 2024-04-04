package com.omarkhaled.simple.webbased.email.program.classes;

import com.omarkhaled.simple.webbased.email.program.interfaces.SortingStrategy;

import java.util.Collection;
import java.util.List;

public class MailSorting {
    private SortingStrategy sortingStrategy;

    public void setSortingStrategy(SortingStrategy sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

    public Collection<Mail> performSort(List<Mail> mails){
        return sortingStrategy.sorting(mails);
    }
}
