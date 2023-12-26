import java.util.ArrayList;
import java.util.List;

public class Userpool {
     private static List<loggedinUser>Free=new ArrayList<>();
     private static List<loggedinUser>used=new ArrayList<>();

     public void setup()
     {

         for (int i = 1; i <= 10 ; i++) {

             loggedinUser loggedinUser=new loggedinUser();
             Free.add(loggedinUser);
         }
     }

     public synchronized loggedinUser acquire()
     {
         if(Free.isEmpty())
         {
             return null;
         }
         else
         {

            loggedinUser user=Free.remove(0);
            used.add(user);
            return user;

         }
     }
     public synchronized void release(loggedinUser useduser)
     {
         useduser.reset();
         used.remove(useduser);
         Free.add(useduser);
     }





}
