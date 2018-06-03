import Controllers.ConsoleController;
import Persistence.MailboxPersistenceService;
import Persistence.MessagePersistenceService;
import Presenters.ConsolePresenter;
import Views.ConsoleViews.Console;
import main.Connection;
import main.MailBoxRepository;
import main.MailSystem;
import main.MessageRepository;

import java.util.Scanner;

/**
 * Created by Jp on 01/06/2018.
 */
public class Main {
    private static final int MAILBOX_COUNT = 20;
    private static MailBoxRepository mailBoxRepository;
    private static MessageRepository messageRepository;
    private static Connection connection;
    private static ConsolePresenter consolePresenter;
    private static ConsoleController consoleController;

    public static void main(String[] args){

        String connectionString="jdbc:sqlite:db.db";
        Scanner console = new Scanner(System.in);

        mailBoxRepository=new MailboxPersistenceService(connectionString);
        messageRepository=new MessagePersistenceService(connectionString);

        MailSystem system = new MailSystem(mailBoxRepository);
        connection = new Connection(system,mailBoxRepository,messageRepository);

        Console telephone=new Console(console);
        consolePresenter=new ConsolePresenter(telephone, connection);
        consoleController=new ConsoleController(connection, console, consolePresenter);

        consoleController.run();
    }
}
