import java.util.Scanner;

import Persistence.MailboxPersistenceService;
import Persistence.MessagePersistenceService;
import main.Connection;
import main.MailSystem;
import main.MailBoxRepository;
import main.MessageRepository;
import Views.ConsoleViews.Console;

import javax.swing.*;

public class Main
{
   private static final int MAILBOX_COUNT = 20;
   public static void main(String[] args)
   {
      String connectionString="jdbc:sqlite:db.db";
      Scanner console = new Scanner(System.in);
      MailBoxRepository mailBoxRepository=new MailboxPersistenceService(connectionString);
      MessageRepository messageRepository=new MessagePersistenceService(connectionString);
      MailSystem system = new MailSystem(mailBoxRepository);
      Connection connection = new Connection(system,mailBoxRepository,messageRepository);

      setObservers(connection, console);

      run(connection, console);
      
   }

   public static void setObservers(Connection connection, Scanner console){
      Console telephone = new Console(connection, console);

      MainWindow form= new MainWindow(connection);
      setMainWindow(form);
   }

   public static void setMainWindow(MainWindow form){
      form.setVisible(true);
      form.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
   }

   public static void run(Connection connection, Scanner scanner)
   {
      boolean more = true;
      while (more)
      {
         String input = scanner.nextLine();
         if (input == null) return;
         if (input.equalsIgnoreCase("H"))
            connection.hangup();
         else if (input.equalsIgnoreCase("Q"))
            more = false;
         else if (input.length() == 1 && "1234567890#".indexOf(input) >= 0)
            connection.dial(input);
         else
            connection.record(input);
      }
   }

}
