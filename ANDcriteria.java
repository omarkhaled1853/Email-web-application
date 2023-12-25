import java.util.ArrayList;

public class ANDcriteria {
     private  FilterIF criteria1;
     private FilterIF criteria2;

     private ArrayList<Mail> unfiltered;

    public ANDcriteria(FilterIF criteria1, FilterIF criteria2, ArrayList<Mail> unfiltered) {
        this.criteria1 = criteria1;
        this.criteria2 = criteria2;
        this.unfiltered = unfiltered;
    }

    public ArrayList<Mail> double_filter()
    {
        ArrayList<Mail>filter1=this.criteria1.filter(unfiltered);
        ArrayList<Mail>filter2=this.criteria2.filter(filter1);
        return filter2;
    }
}
