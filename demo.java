import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

       Userpool userpool=new Userpool();
       userpool.setup();
        loggedinUser l1= userpool.acquire();
        loggedinUser l2= userpool.acquire();
        loggedinUser l3= userpool.acquire();
        loggedinUser l4= userpool.acquire();
        loggedinUser l5= userpool.acquire();
        l1.setEmail("Ahmed");
        boolean res1=testloggedin.test(l1);
        l2.setEmail("Ahmed");
        boolean res2=testloggedin.test(l2);
        System.out.println(res1);
        System.out.println(res2);

    }

}
