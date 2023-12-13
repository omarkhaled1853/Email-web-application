import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> attachments = new ArrayList<>();
        attachments.add("image");
        Mail mail = new Mail.Builder().setSender("Mohamed@gmail.com").setReceiver("Ahmed@gmail.com").
                setSubject("Work").setPriority(5).setAttachments(attachments).
                setContent("Hello").setDate("12/12/2023 10:5:30").build();

        System.out.println(mail.getSender());
        System.out.println(mail.getReceiver());
        System.out.println(mail.getSubject());
        System.out.println(mail.getPriority());
        System.out.println(mail.getAttachments());
        System.out.println(mail.getContent());
        System.out.println(mail.getDate());
    }
}