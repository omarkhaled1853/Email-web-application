package com.omarkhaled.simple.webbased.email.program.classes;

import com.omarkhaled.simple.webbased.email.program.interfaces.UserBuilder;

public class User {
    private String id;
    private String email;
    private String name;

    private String password;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //    private Inbox inbox;
    //    private ArrayList<UserE_mail>userEMails;



//    private ArrayList<Contact> contacts;

//    private ArrayList<Folder> folders;
//    private Trash trash;
//    private Sent sent;

//    private Draft draft;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public ArrayList<UserE_mail> getUserEMails() {
//        return userEMails;
//    }

//    public void setUserEMails(ArrayList<UserE_mail> userEMails) {
//        this.userEMails = userEMails;
//    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public Inbox getInbox() {
//        return inbox;
//    }

//    public void setInbox(Inbox inbox) {
//        this.inbox = inbox;
//    }

//    public ArrayList<Contact> getContacts() {
//        return contacts;
//    }

//    public void setContacts(ArrayList<Contact> contacts) {
//        this.contacts = contacts;
//    }

//    public ArrayList<Folder> getFolders() {
//        return folders;
//    }

//    public void setFolders(ArrayList<Folder> folders) {
//        this.folders = folders;
//    }

//    public Trash getTrash() {
//        return trash;
//    }

//    public void setTrash(Trash trash) {
//        this.trash = trash;
//    }

//    public Sent getSent() {
//        return sent;
//    }

//    public void setSent(Sent sent) {
//        this.sent = sent;
//    }

//    public Draft getDraft() {
//        return draft;
//    }

//    public void setDraft(Draft draft) {
//        this.draft = draft;
//    }

    public static class Builder implements UserBuilder {
          User user =new User();
        @Override
        public User build() {
            return user;
        }

        @Override
        public UserBuilder setId(String id) {
            user.id=id;
            return this;
        }

        @Override
        public UserBuilder setEmail(String email) {
            user.email = email;
            return this;
        }

        @Override
        public UserBuilder setName(String name) {
           user.name=name;
           return this;
        }

        @Override
        public UserBuilder setPassword(String password) {
            user.password = password;
            return this;
        }
//        @Override
//        public UserBuilder set_email(ArrayList<UserE_mail> e_mails) {
//            user.userEMails=e_mails;
//            return this;

//        }

//        @Override
//        public UserBuilder setInbox(ArrayList<Mail> inbox) {
//           user.inbox.setInboxMails(inbox);
//           return this;
//        }

//        @Override
//        public UserBuilder setTrash(ArrayList<Mail> trash) {
//            user.trash.setTrashMails(trash);
//            return this;
//        }

//        @Override
//        public UserBuilder setSent(ArrayList<Mail> sent) {
//            user.sent.setSentMails(sent);
//            return this;
//        }

//        @Override
//        public UserBuilder setDraft(ArrayList<Mail> draft) {
//            user.draft.setDrafts(draft);
//            return this;
//        }

//        @Override
//        public UserBuilder setFolders(ArrayList<Folder> folders) {
//            user.folders=folders;
//            return null;
//        }

//        @Override
//        public UserBuilder setContacts(ArrayList<Contact> contacts) {
//            user.contacts=contacts;
//            return this;
//        }
    }

//    @Override
//    public String toString() {
//        return "User{" +
//                "name='" + name + '\'' +
//                ", userEMails=" + userEMails +
//                ", password='" + password + '\'' +
//                ", id='" + id + '\'' +
//                ", inbox=" + inbox +
//                ", contacts=" + contacts +
//                ", folders=" + folders +
//                ", trash=" + trash +
//                ", sent=" + sent +
//                ", draft=" + draft +
//                '}';
//    }
}
