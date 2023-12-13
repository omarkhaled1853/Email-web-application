import java.util.ArrayList;

public class Contact {
    private String name;
    private ArrayList<UserE_mail> e_mails;

    public ArrayList<UserE_mail> getE_mails() {
        return e_mails;
    }

    public void setE_mails(ArrayList<UserE_mail> e_mails) {
        this.e_mails = e_mails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
