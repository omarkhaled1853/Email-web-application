import java.util.ArrayList;

public class Subjectfilter  implements FilterIF{

    @Override
    public ArrayList<Mail> filtered_mails(ArrayList<Mail> unfiltered, String criteria) {
        ArrayList<Mail> filtered=new ArrayList<Mail>();
        for (Mail mail: unfiltered)
        {
            if(mail.getSubject().equals(criteria))
            {
                filtered.add(mail);
            }
        }
        return filtered;
    }
}

