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
public class MainControllers {
    public static void main(String[] args){
        String connectionString="jdbc:sqlite:db.db";
        Scanner console = new Scanner(System.in);
        MailBoxRepository mailBoxRepository=new MailboxPersistenceService(connectionString);
        MessageRepository messageRepository=new MessagePersistenceService(connectionString);
        MailSystem system = new MailSystem(mailBoxRepository);
        Connection connection = new Connection(system,mailBoxRepository,messageRepository);

        Console telephone=new Console(console);
        ConsolePresenter consolePresenter=new ConsolePresenter(telephone, connection);

        ConsoleController consoleController=new ConsoleController(connection, console, consolePresenter);
        consoleController.run();
    }
}
