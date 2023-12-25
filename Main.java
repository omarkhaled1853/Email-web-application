import org.w3c.dom.ls.LSException;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Map<Integer, List<Integer>>mp = new HashMap<>();
//        List<Integer> x = new ArrayList<>();
//        x.add(4);
//        mp.put(5, x);
//        System.out.println(mp.get(5).remove(4));
//        x.add(5);
//        System.out.println(mp.get(5).size());
//        String x = "mido ahmed";
////
//        LocalDateTime localDateTime = LocalDateTime.now();
//        System.out.println(localDateTime);
//
//        Clock clock = Clock.systemDefaultZone();
//        ZoneId milliSeconds=clock.getZone();
//        System.out.println(milliSeconds);
//
//
//
////        System.out.println(x.contains("ahmed"));
//
//        long millaiSeconds=clock.millis();
//        System.out.println(milliSeconds);
//
//
        LocalDateTime localDateTime ;
//        Date date = new Date();
//        LocalDateTime date = localDateTime;
//        System.out.println(date);
//        System.out.println(date.getTime());
        ArrayList<String> attachments = new ArrayList<>();
        ArrayList<String> attachments2 = new ArrayList<>();
//        attachments2.add("image");
//        attachments2.add("image2");
//        attachments.add("image");
//        System.out.println(attachments2.contains("image2"));
        MailSorting mailSorting = new MailSorting();
        localDateTime = LocalDateTime.now();
        String y = localDateTime.toString();
        Mail mail1 = new Mail.Builder().setSender("Mohamed@gmail.com").setReceiver("Ahmed@gmail.com").
        setSubject(y).setPriority(2).setAttachments(attachments2).
        setContent("CHello").setDate(localDateTime).build();
        int x = scanner.nextInt();
        localDateTime = LocalDateTime.now();
        y = localDateTime.toString();
        Mail mail2 = new Mail.Builder().setSender("Mohamed@gmail.com").setReceiver("Ahmed@gmail.com").
        setSubject(y).setPriority(5).setAttachments(attachments).
        setContent("ZHello").setDate(localDateTime).build();
        x = scanner.nextInt();
        localDateTime = LocalDateTime.now();
        y = localDateTime.toString();
        Mail mail3= new Mail.Builder().setSender("Mohamed@gmail.com").setReceiver("Ahmed@gmail.com").
        setSubject(y).setPriority(1).setAttachments(attachments2).
        setContent("BHello").setDate(localDateTime).setCreateOn(323232L).setDestroyOn(344443L).build();
        ArrayList<Mail> mails = new ArrayList<>();
        MailSearching mailSearching = new MailSearching();
        mails.add(mail2);
        mails.add(mail1);
        mails.add(mail3);
        System.out.println(mail1.getDate());
        System.out.println(mail2.getDate());
        System.out.println(mail3.getDate());
        ArrayList<Mail> result = new ArrayList<>();
        result = mailSearching.dateSearching(localDateTime, mails);
        System.out.println(result.get(0).getPriority());
        LocalDateTime localDateTime1 = LocalDateTime.parse(y);
        System.out.println(localDateTime1);
//        System.out.println();
////        System.out.println(mail1.getCreateOn());
////        System.out.println(mail1.getDestroyOn());
////        result = mailSorting.prioritySort(mails);
////        System.out.println(result.get(0).getPriority());
////        System.out.println(result.get(1).getPriority());
////        System.out.println(result.get(2).getPriority());
//
//        result = mailSorting.subjectSort(mails);
//        System.out.println(result.get(0).getSubject());
//        System.out.println(result.get(1).getSubject());
//        System.out.println(result.get(2).getSubject());
//
//        result = mailSorting.contentSort(mails);
//        System.out.println(result.get(0).getContent());
//        System.out.println(result.get(1).getContent());
//        System.out.println(result.get(2).getContent());
//
//        result = mailSorting.dateSort(mails);
//        System.out.println(result.get(0).getDate());
//        System.out.println(result.get(1).getDate());
//        System.out.println(result.get(2).getDate());

//        System.out.println(mails.get(0).getSubject());
//        System.out.println(mails.get(1).getSubject());
//        System.out.println(mails.get(2).getSubject());
//        MailSorting mailSorting = new MailSorting();
//        mails = mailSorting.subjectSort(mails);
//        System.out.println(mails.get(0).getSubject());
//        System.out.println(mails.get(1).getSubject());
//        System.out.println(mails.get(2).getSubject());
//        mails.sort((o1, o2) -> {
//            int length1 = o1.getAttachments().size();
//            int length2 = o2.getAttachments().size();
//            return Integer.compare(length2, length1);
//        });
//        System.out.println(mails.get(0).getAttachments().size());
//        System.out.println(mails.get(1).getAttachments().size());
//        System.out.println(mails.get(2).getAttachments().size());
//        mails.sort(Comparator.comparing(Mail :: getPriority));
////        Collections.sort(mails, Comparator.comparingInt());
//        Collections.reverse(mails);
//        System.out.println(mails.get(0).getPriority());
//        System.out.println(mails.get(1).getPriority());
//        System.out.println(mails.get(2).getPriority());
//
//        mails.sort(Comparator.comparing(Mail::getSubject));
//        System.out.println(mails.get(0));
//        System.out.println(mails.get(1));
//        System.out.println(mails.get(2));








//        mails.sort((o1, o2) -> o2.getSubject().compareTo(o1.getSubject()));
//        Collections.sort(mails);
//        System.out.println(mails.get(0).getSubject());
//        System.out.println(mails.get(1).getSubject());
//        System.out.println(mails.get(2).getSubject());
//        LocalDateTime localDateTime = LocalDateTime.now();
//        System.out.println(localDateTime);
//        LocalDateTime localDateTime1 = LocalDateTime.now();
//        System.out.println(localDateTime1);
//        Date date;
//        ArrayList<Integer> aa = new ArrayList<>();
//        aa.add(3);
//        aa.add(5);
//        aa.add(32);
//        aa.add(4);
//        aa.add(4);
//        aa.add(90);
//        aa.add(45);
//        Collections.sort(aa);
//        System.out.println(aa);
////        Collections.sort(aa, Collections.reverseOrder());
//        aa.sort(Collections.reverseOrder());

//        System.out.println(aa);
//        ArrayList<String> attachments = new ArrayList<>();
//        attachments.add("image");
//        Mail mail = new Mail.Builder().setSender("Mohamed@gmail.com").setReceiver("Ahmed@gmail.com").
//                setSubject("Work").setPriority(5).setAttachments(attachments).
//                setContent("Hello").setDate("12/12/2023 10:5:30").build();
//
//        System.out.println(mail.getSender());
//        System.out.println(mail.getReceiver());
//        System.out.println(mail.getSubject());
//        System.out.println(mail.getPriority());
//        System.out.println(mail.getAttachments());
//        System.out.println(mail.getContent());
//        System.out.println(mail.getDate());
    }
}