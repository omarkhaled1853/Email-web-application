import java.util.ArrayList;

public class SubjectFilter implements FilterIF,CriteriaIF{
   private String subject;
   private ArrayList<Mail>filtered=new ArrayList<Mail>();
    @Override
    public ArrayList<Mail> filter(ArrayList<Mail> unfiltered) {

        for(Mail x:unfiltered)
        {
            if(x.getSubject().equals(subject))
            {
                filtered.add(x);
            }
        }
        return filtered;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String Type() {
        return "Subject";
    }

    public ArrayList<Mail> getFiltered() {
        return filtered;
    }
}
