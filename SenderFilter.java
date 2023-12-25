import java.util.ArrayList;

public class SenderFilter implements FilterIF,CriteriaIF{

    private String sender;
     private ArrayList<Mail>filtered=new ArrayList<Mail>();

    @Override
    public ArrayList<Mail> filter(ArrayList<Mail> unfiltered) {
        for(Mail x:unfiltered)
        {
            if(x.getSender().equals(sender))
            {
                filtered.add(x);
            }
        }
        return filtered;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Override
    public String Type() {
        return "Sender";
    }

    public ArrayList<Mail> getFiltered() {
        return filtered;
    }
}
