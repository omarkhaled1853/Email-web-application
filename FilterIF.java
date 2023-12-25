import java.util.ArrayList;

public interface FilterIF {
     ArrayList<Mail>filtered=new ArrayList<Mail>();
    public ArrayList<Mail> filter(ArrayList<Mail> unfiltered);

    public ArrayList<Mail> getFiltered();



}
