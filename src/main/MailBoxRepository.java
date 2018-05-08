package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jp on 05/05/2018.
 */
public interface MailBoxRepository {

    void saveMailbox(Mailbox mailbox, int id);
    void updateMailbox(Mailbox mailbox, int mailboxId);
    Mailbox getMailBoxById(int mailboxId);
    ArrayList<Mailbox> getAllMailBoxes();
}
