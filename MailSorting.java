import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MailSorting {
    ArrayList<Mail> senderSort(ArrayList<Mail> mails){
        mails.sort((o1,o2) -> o2.getSender().compareTo(o1.getSender()));
        return mails;
    }
    ArrayList<Mail> receiverSort(ArrayList<Mail> mails){
        mails.sort((o1,o2) -> o2.getReceiver().compareTo(o1.getReceiver()));
        return mails;
    }
    ArrayList<Mail> contentSort(ArrayList<Mail> mails){
        mails.sort((o1,o2) -> o2.getContent().compareTo(o1.getContent()));
        return mails;
    }
    ArrayList<Mail> subjectSort(ArrayList<Mail> mails){
        mails.sort((o1,o2) -> o2.getSubject().compareTo(o1.getSubject()));
        return mails;
    }
    ArrayList<Mail> dateSort(ArrayList<Mail> mails){
        mails.sort(Comparator.comparing(Mail::getDate));
        Collections.reverse(mails);
        return mails;
    }
    ArrayList<Mail> attachmentSort(ArrayList<Mail> mails){
        mails.sort((o1,o2) -> {
            int sizeO1 = o1.getAttachments().size();
            int sizeO2 = o2.getAttachments().size();
            return Integer.compare(sizeO2, sizeO1);
        });
        return mails;
    }
    ArrayList<Mail> prioritySort(ArrayList<Mail> mails){
        mails.sort(Comparator.comparing(Mail::getPriority));
        Collections.reverse(mails);
        return mails;
    }
}
