import java.util.ArrayList;
import java.util.List;

public class testloggedin {
    private static List<loggedinUser> test=new ArrayList<>();
    public static boolean test(loggedinUser x)
    {
        for (loggedinUser y:test) {
            if((x.getEmail()==y.getEmail()))
            {
                return false;
            }
        }
        test.add(x);
        return true;
    }
}
