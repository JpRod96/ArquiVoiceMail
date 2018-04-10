import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.Connection;
import main.MailSystem;
import observers.*;
import observers.Telephone;

import javax.swing.*;

import static java.awt.BorderLayout.*;

/**
   This program tests the mail system. A single phone
   communicates with the program through System.in/System.out.
*/
public class MailSystemTester
{
   public static void main(String[] args)
   {
      MailSystem system = new MailSystem(MAILBOX_COUNT);
      Scanner console = new Scanner(System.in);
      Connection connection = new Connection(system);
      Telephone telephone = new Telephone(connection, console);
       GUI form= new GUI();
       form.setVisible(true);
       form.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      run(connection, console);
      
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
         else if (input.length() == 1
            && "1234567890#".indexOf(input) >= 0)
            connection.dial(input);
         else
            connection.record(input);
      }
   }

   private static final int MAILBOX_COUNT = 20;
}
