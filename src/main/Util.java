package main;

import Persistence.MailboxPersistenceService;
import Persistence.MessagePersistenceService;

/**
 * Created by CORE i7 on 13/05/2018.
 */
public class Util {
    public static MailBoxRepository getMailBoxRepository() {
        return mailBoxRepository;
    }

    public static final MailBoxRepository mailBoxRepository = new MailboxPersistenceService("jdbc:sqlite:db.db");
    public static final MessageRepository messageRepository = new MessagePersistenceService("jdbc:sqlite:db.db");

}
