import Persistence.MailboxPersistenceService;
import main.Mailbox;

import static spark.Spark.*;
public class main2 {
    public static void main(String[] args) {
        get("/hello", (req, res) -> {
            MailboxPersistenceService m=new MailboxPersistenceService("jdbc:sqlite:db.db");
            Mailbox a= m.getMailBoxById(1);
            return a.getGreeting();
        });

    }
}
