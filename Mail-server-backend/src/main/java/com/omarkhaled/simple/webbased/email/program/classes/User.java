package com.omarkhaled.simple.webbased.email.program.classes;

import com.omarkhaled.simple.webbased.email.program.interfaces.UserBuilder;

import java.util.List;
import java.util.Map;

public class User {
    private String email;
    private String userName;

    private String password;

    private Map<String, Mail> inbox;

    private Map<String, List<String>> contacts;
    private Map<String, Mail> sent;

    private Map<String, Mail> draft;

    private Map<String, Mail> trash;

    private Map<String, Map<String, Mail>> folders;


    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", inbox=" + inbox +
                ", contacts=" + contacts +
                ", sent=" + sent +
                ", draft=" + draft +
                ", trash=" + trash +
                ", folders=" + folders +
                '}';
    }

    public String getEmail() {
    return email;
}

    public String getUserName() {
        return userName;
    }


    public String getPassword() {
        return password;
    }

    public Map<String, Mail> getInbox() {
        return inbox;
    }

    public Map<String, Mail> getSent() {
        return sent;
    }

    public Map<String, Mail> getDraft() {
        return draft;
    }

    public Map<String, Mail> getTrash() {
        return trash;
    }

    public Map<String, Map<String, Mail>> getFolders() {
        return folders;
    }

    public Map<String, List<String>> getContacts() {
        return contacts;
    }


    public static class Builder implements UserBuilder {
        User user =new User();

        @Override
        public User build() {
            return user;
        }


        @Override
        public UserBuilder setEmail(String email) {
            user.email = email;
            return this;
        }

        @Override
        public UserBuilder setUserName(String userName) {
           user.userName=userName;
           return this;
        }

        @Override
        public UserBuilder setPassword(String password) {
            user.password = password;
            return this;
        }

        @Override
        public UserBuilder setInbox(Map<String, Mail> inbox) {
            user.inbox = inbox;
            return this;
        }

        @Override
        public UserBuilder setSent(Map<String, Mail> sent) {
            user.sent = sent;
            return this;
        }

        @Override
        public UserBuilder setDraft(Map<String, Mail> draft) {
            user.draft = draft;
            return this;
        }

        @Override
        public UserBuilder setTrash(Map<String, Mail> trash) {
            user.trash = trash;
            return this;
        }

        @Override
        public UserBuilder setFolders(Map<String, Map<String, Mail>> folders) {
            user.folders = folders;
            return this;
        }

        @Override
        public UserBuilder setContacts(Map<String, List<String>> contacts) {
            user.contacts = contacts;
            return this;
        }
    }
}
