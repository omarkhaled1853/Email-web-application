package com.omarkhaled.simple.webbased.email.program.classes;

import com.omarkhaled.simple.webbased.email.program.interfaces.SearchingStrategy;

import java.util.Collection;
import java.util.List;

public class MailSearching {
    private SearchingStrategy searchingStrategy;

    public void setSearchingStrategy(SearchingStrategy searchingStrategy) {
        this.searchingStrategy = searchingStrategy;
    }

    public Collection<Mail> performSearch(String keyWord, Collection<Mail> mails){
        return searchingStrategy.searching(keyWord, mails);
    }
}
