import java.util.ArrayList;

public class Senderfilter implements FilterIF{

    @Override
    public ArrayList<Mail> filtered_mails(ArrayList<Mail> unfiltered, String criteria) {
        ArrayList<Mail> filtered=new ArrayList<Mail>();
          for (Mail mail: unfiltered)
          {
              if(mail.getSender().equals(criteria))
              {
                  filtered.add(mail);
              }
          }
          return filtered;
    }
}
