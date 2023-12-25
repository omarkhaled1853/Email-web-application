import java.time.LocalDateTime;
import java.util.ArrayList;
public class MailSearching {
    private ArrayList<Mail> searchResult;
    ArrayList<Mail> senderSearch(String sender, ArrayList<Mail> mails){
        searchResult = new ArrayList<>();
        for (Mail mail: mails){
            if(mail.getSender().equals(sender))
                searchResult.add(mail);
        }
        return searchResult;
    }
    ArrayList<Mail> receiverSearch(String receiver, ArrayList<Mail> mails){
        searchResult = new ArrayList<>();
        for(Mail mail: mails){
            if(mail.getReceiver().equals(receiver))
                searchResult.add(mail);
        }
        return searchResult;
    }
    ArrayList<Mail> contentSearch(String content, ArrayList<Mail> mails){
        searchResult = new ArrayList<>();
        for (Mail mail: mails){
            if(mail.getContent().contains(content))
                searchResult.add(mail);
        }
        return searchResult;
    }
    ArrayList<Mail> subjectSearch(String subject, ArrayList<Mail> mails){
        searchResult = new ArrayList<>();
        for(Mail mail: mails){
            if(mail.getReceiver().equals(subject))
                searchResult.add(mail);
        }
        return searchResult;
    }
    ArrayList<Mail> dateSearching(LocalDateTime date, ArrayList<Mail> mails){
        searchResult = new ArrayList<>();
        for(Mail mail: mails){
            if(mail.getDate() == date)
                searchResult.add(mail);
        }
        return searchResult;
    }
    ArrayList<Mail> attachmentSearch(String attachment, ArrayList<Mail> mails){
        searchResult = new ArrayList<>();
        for(Mail mail: mails){
            if(mail.getAttachments().contains(attachment))
                searchResult.add(mail);
        }
        return searchResult;
    }
    ArrayList<Mail> prioritySearch(int priority, ArrayList<Mail> mails){
        searchResult = new ArrayList<>();
        for(Mail mail: mails){
            if(mail.getPriority() == priority)
                searchResult.add(mail);
        }
        return searchResult;
    }
}