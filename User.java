import java.util.ArrayList;

public class User {
    private String name;
    private ArrayList<UserE_mail>userEMails;
    private String password;
    private String id;
    private Inbox inbox;

    private ArrayList<Contact> contacts;

    private ArrayList<Folder> folders;
    private Trash trash;
    private Sent sent;

    private Draft draft;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<UserE_mail> getUserEMails() {
        return userEMails;
    }

    public void setUserEMails(ArrayList<UserE_mail> userEMails) {
        this.userEMails = userEMails;
    }

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

    public Inbox getInbox() {
        return inbox;
    }

    public void setInbox(Inbox inbox) {
        this.inbox = inbox;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    public ArrayList<Folder> getFolders() {
        return folders;
    }

    public void setFolders(ArrayList<Folder> folders) {
        this.folders = folders;
    }

    public Trash getTrash() {
        return trash;
    }

    public void setTrash(Trash trash) {
        this.trash = trash;
    }

    public Sent getSent() {
        return sent;
    }

    public void setSent(Sent sent) {
        this.sent = sent;
    }

    public Draft getDraft() {
        return draft;
    }

    public void setDraft(Draft draft) {
        this.draft = draft;
    }
}
