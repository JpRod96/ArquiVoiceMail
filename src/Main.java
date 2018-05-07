import java.util.Scanner;

import Persistence.DatabaseService;
import main.Connection;
import main.MailSystem;
import main.PersistenceRepository;
import observers.Telephone;

import javax.swing.*;

/**
   This program tests the mail system. A single phone
   communicates with the program through System.in/System.out.
*/
public class Main
{
   private static final int MAILBOX_COUNT = 20;
   public static void main(String[] args)
   {
      MailSystem system = getMailSystemInfoFromDB();
      Scanner console = new Scanner(System.in);
      Connection connection = new Connection(system);
      Telephone telephone = new Telephone(connection, console);
      MainWindow form= new MainWindow(connection);
      form.setVisible(true);
      form.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      run(connection, console);
      
   }

   public static MailSystem getMailSystemInfoFromDB(){
      PersistenceRepository persistenceRepository=new DatabaseService();
      return persistenceRepository.getMailSystem();
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
         else if (input.equalsIgnoreCase("S")) {
            DatabaseService dbService=new DatabaseService();
            dbService.updateMailSystem(connection.getSystem());
            System.out.println("Saved");
            connection.hangup();
         }
         else if (input.length() == 1 && "1234567890#".indexOf(input) >= 0)
            connection.dial(input);
         else
            connection.record(input);
      }
   }

}
