package com.omarkhaled.simple.webbased.email.program.services;

import com.omarkhaled.simple.webbased.email.program.classes.*;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class SearchingService {
    private final MailSearching mailSearching = new MailSearching();

    //set searching strategy
    public void setStrategy(String type){
        switch (type) {
            case "Sender" -> mailSearching.setSearchingStrategy(new SenderSearch());
            case "Receiver" -> mailSearching.setSearchingStrategy(new ReceiverSearch());
            case "Content" -> mailSearching.setSearchingStrategy(new ContentSearch());
            case "Subject" -> mailSearching.setSearchingStrategy(new SubjectSearch());
            case "Date" -> mailSearching.setSearchingStrategy(new DateSearch());
            case "Attachment" -> mailSearching.setSearchingStrategy(new AttachmentSearch());
            case "Priority" -> mailSearching.setSearchingStrategy(new PrioritySearch());
            default -> {
            }
        }
    }

    //get searching
    public Collection<Mail> getMails(String keyWord, Collection<Mail> mails){
        return mailSearching.performSearch(keyWord,  mails);
    }
}
