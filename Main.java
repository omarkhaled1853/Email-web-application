import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Mail m1= new Mail.Builder().setContent("roro").setPriority(2).setSender("yke5k7").setSubject("112").build();
        Mail m2 = new Mail.Builder().setContent("riri").setPriority(3).setSender("1ermtm").setSubject("111").build();
        Mail m3 = new Mail.Builder().setContent("rara").setPriority(4).setSender("rtwhhtt4h").setSubject("111").build();
        Mail m4= new Mail.Builder().setContent("ntmyu,mp").setPriority(2).setSender("1tr").setSubject("112").build();
        Mail m5 = new Mail.Builder().setContent("riri").setPriority(3).setSender("wjnetj").setSubject("111").build();
        Mail m6 = new Mail.Builder().setContent("rara").setPriority(4).setSender("rymy").setSubject("111").build();
        Mail m7= new Mail.Builder().setContent("tttttt").setPriority(2).setSender("aretjw4").setSubject("112").build();
        Mail m8 = new Mail.Builder().setContent("riri").setPriority(3).setSender("nty,u,yu").setSubject("111").build();
        Mail m9 = new Mail.Builder().setContent("rara").setPriority(4).setSender("eetjrj").setSubject("111").build();
        ArrayList<Mail> mails=new ArrayList<Mail>();
        mails.add(m1);
        mails.add(m2);
        mails.add(m3);
        mails.add(m4);
        mails.add(m5);
        mails.add(m6);
        mails.add(m7);
        mails.add(m8);
        mails.add(m9);

        CriteriaFactory factory=new CriteriaFactory();
        FilterIF x1=factory.getcriteria("Subject","112");
        FilterIF x2= factory.getcriteria("Sender","aretjw4");
        ANDcriteria anDcriteria=new ANDcriteria(x1,x2,mails);
        ArrayList<Mail>res=anDcriteria.double_filter();
        for(Mail x:res)
        {
            System.out.println(x.toString());
        }


    }

}
