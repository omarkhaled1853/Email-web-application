import java.util.ArrayList;

public interface UserBuilder {
    User build();

    UserBuilder setname(String name);
    UserBuilder set_email(ArrayList<UserE_mail> e_mails);

    UserBuilder setId(String id);

    UserBuilder setinbox(ArrayList<Mail> inbox);
    UserBuilder settrash(ArrayList<Mail> trash);
    UserBuilder setsent(ArrayList<Mail> sent);
    UserBuilder setdraft(ArrayList<Mail> draft);

    UserBuilder setfolders(ArrayList<Folder> folders);

    UserBuilder setcontacts(ArrayList<Contact> contacts);





}
