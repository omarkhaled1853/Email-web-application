package com.omarkhaled.simple.webbased.email.program.interfaces;

import com.omarkhaled.simple.webbased.email.program.classes.Mail;
import com.omarkhaled.simple.webbased.email.program.classes.User;

import java.util.List;
import java.util.Map;

public interface UserBuilder {
    User build();

    UserBuilder setEmail(String email);

    UserBuilder setUserName(String userName);

    UserBuilder setPassword(String password);

    UserBuilder setInbox(Map<String, Mail> inbox);

    UserBuilder setSent(Map<String, Mail> sent);

    UserBuilder setDraft(Map<String, Mail> draft);

    UserBuilder setTrash(Map<String, Mail> trash);

    UserBuilder setFolders(Map<String, Map<String, Mail>> folders);

    UserBuilder setContacts(Map<String, List<String>> contacts);

}
