import java.util.ArrayList;

public interface BuilderIf {
    Mail build();
    BuilderIf setSender(String sender);
    BuilderIf setReceiver(String receiver);
    BuilderIf setContent(String content);
    BuilderIf setSubject(String subject);
    BuilderIf setDate(long date);
    BuilderIf setAttachments(ArrayList<String> attachments);
    BuilderIf setPriority(int priority);
}

