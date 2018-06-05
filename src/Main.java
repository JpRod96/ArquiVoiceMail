import Controllers.ConsoleController;
import Controllers.UIController;
import Persistence.MailboxPersistenceService;
import Persistence.MessagePersistenceService;
import Presenters.ConsolePresenter;
import Presenters.UIPresenter;
import Views.ConsoleViews.Console;
import Views.ConsoleViews.MainWindow;
import main.Connection;
import main.MailBoxRepository;
import main.MailSystem;
import main.MessageRepository;

import javax.swing.*;
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
    private static UIPresenter uiPresenter;
    public static UIController uiController;

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

        MainWindow mainWindow = new MainWindow();
        uiPresenter = new UIPresenter(mainWindow,connection);
        mainWindow.setUiPresenter(uiPresenter);
        uiController = new UIController(connection,uiPresenter);
        mainWindow.setController(uiController);

        mainWindow.setVisible(true);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        consoleController.run();
    }
}
