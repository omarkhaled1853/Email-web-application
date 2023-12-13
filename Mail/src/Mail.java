import java.util.ArrayList;

public class Mail {
    private String sender;
    private String receiver;
    private String content;
    private String subject;
    private String date;
    private ArrayList<String> attachments;
    private int priority;

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }

    public String getSubject() {
        return subject;
    }

    public String getDate() {
        return date;
    }

    public ArrayList<String> getAttachments() {
        return attachments;
    }

    public int getPriority() {
        return priority;
    }
    static class Builder implements BuilderIf{

        Mail mail = new Mail();
        @Override
        public Mail build() {
            return mail;
        }

        @Override
        public BuilderIf setSender(String sender) {
            mail.sender = sender;
            return this;
        }

        @Override
        public BuilderIf setReceiver(String receiver) {
            mail.receiver = receiver;
            return this;
        }

        @Override
        public BuilderIf setContent(String content) {
            mail.content = content;
            return this;
        }

        @Override
        public BuilderIf setSubject(String subject) {
            mail.subject = subject;
            return this;
        }

        @Override
        public BuilderIf setDate(String date) {
            mail.date = date;
            return this;
        }

        @Override
        public BuilderIf setAttachments(ArrayList<String> attachments) {
            mail.attachments = attachments;
            return this;
        }

        @Override
        public BuilderIf setPriority(int priority) {
            mail.priority = priority;
            return this;
        }
    }
}
