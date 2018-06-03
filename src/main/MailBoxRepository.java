package main;

import MailVoice.Mailbox;

import java.util.ArrayList;

/**
 * Created by Jp on 05/05/2018.
 */
public interface MailBoxRepository {

    void saveMailbox(Mailbox mailbox);
    void saveMailbox2(Mailbox mailbox);
    void updateMailbox(Mailbox mailbox);
    Mailbox getMailBoxById(int mailboxId);
    ArrayList<Mailbox> getAllMailBoxes();
}
