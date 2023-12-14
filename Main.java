import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
          Mail mail1=new Mail.Builder().setSender("Ahmed").setSubject("school").build();
        Mail mail2=new Mail.Builder().setSender("Ahmed").setSubject("study").build();
        Mail mail3=new Mail.Builder().setSender("mohamed").setSubject("school").build();
        Mail mail4=new Mail.Builder().setSender("mohamed").setSubject("studt").build();
        Mail mail5=new Mail.Builder().setSender("mido").setSubject("school").build();
        Mail mail6=new Mail.Builder().setSender("modi").setSubject("study").build();
        ArrayList<Mail>mails=new ArrayList<Mail>();
        mails.add(mail1);
        mails.add(mail2);
        mails.add(mail3);
        mails.add(mail4);
        mails.add(mail5);
        mails.add(mail6);
        Senderfilter filter=new Senderfilter();
        ArrayList<Mail> x=filter.filtered_mails(mails,"Ahmed");
        for(Mail mail:x)
        {
            System.out.println(mail.toString());
        }
    }
}