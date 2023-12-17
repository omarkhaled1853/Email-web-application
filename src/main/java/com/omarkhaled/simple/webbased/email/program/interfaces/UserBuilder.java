package com.omarkhaled.simple.webbased.email.program.interfaces;

import com.omarkhaled.simple.webbased.email.program.classes.User;

public interface UserBuilder {
    User build();

    UserBuilder setId(String id);
    UserBuilder setEmail(String email);

    UserBuilder setName(String name);

    UserBuilder setPassword(String password);

//    UserBuilder setEmail(ArrayList<UserE_mail> e_mails);

//    UserBuilder setInbox(ArrayList<Mail> inbox);
//    UserBuilder setTrash(ArrayList<Mail> trash);
//    UserBuilder setSent(ArrayList<Mail> sent);
//    UserBuilder setDraft(ArrayList<Mail> draft);

//    UserBuilder setFolders(ArrayList<Folder> folders);

//    UserBuilder setContacts(ArrayList<Contact> contacts);





}
