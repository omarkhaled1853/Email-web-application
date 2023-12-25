import java.util.ArrayList;

public class PriorityFilter implements FilterIF,CriteriaIF{
    private int priority;
    private ArrayList<Mail>filtered=new ArrayList<Mail>();

    @Override
    public ArrayList<Mail> filter(ArrayList<Mail> unfiltered) {
        for(Mail x:unfiltered)
        {
            if(x.getPriority()==priority)
            {
                filtered.add(x);
            }
        }
        return filtered;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public ArrayList<Mail> getFiltered() {
        return filtered;
    }

    @Override
    public String Type() {
        return "Priority";
    }


}
