package com.omarkhaled.simple.webbased.email.program.services;

import com.omarkhaled.simple.webbased.email.program.classes.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SortingService {
    private final MailSorting mailSorting = new MailSorting();


    //set sorting strategy
    public void setStrategy(String type){
        switch (type) {
            case "Sender" -> mailSorting.setSortingStrategy(new SenderSort());
            case "Receiver" -> mailSorting.setSortingStrategy(new ReceiverSort());
            case "Content" -> mailSorting.setSortingStrategy(new ContentSort());
            case "Subject" -> mailSorting.setSortingStrategy(new SubjectSort());
            case "Date" -> mailSorting.setSortingStrategy(new DateSort());
            case "Attachment" -> mailSorting.setSortingStrategy(new AttachmentSort());
            case "Priority" -> mailSorting.setSortingStrategy(new PrioritySort());
            default -> {
            }
        }
    }

    //get sorting
    public Collection<Mail> getMails(Collection<Mail> mails){
        List<Mail> mailsList = new ArrayList<>(mails);
        return mailSorting.performSort(mailsList);
    }
}
