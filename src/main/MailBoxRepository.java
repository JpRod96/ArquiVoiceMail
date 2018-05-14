package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jp on 05/05/2018.
 */
public interface MailBoxRepository {

    void saveMailbox(Mailbox mailbox);
    void updateMailbox(Mailbox mailbox);
    Mailbox getMailBoxById(int mailboxId);
    ArrayList<Mailbox> getAllMailBoxes();
}
