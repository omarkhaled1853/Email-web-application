import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface MailBuilder {
    Mail build();
    MailBuilder setId(String id);

    MailBuilder setSender(String sender);
    MailBuilder setReceiver(String receiver);
    MailBuilder setContent(String content);
    MailBuilder setSubject(String subject);
    MailBuilder setDate(LocalDateTime date);
    MailBuilder setPriority(int priority);

    MailBuilder setCreateOn(long createOn);

    MailBuilder setDestroyOn(long destroyOn);
}