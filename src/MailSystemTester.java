  

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.Connection;
import main.MailSystem;
import observers.*;
import observers.Telephone;

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
      Connection c = new Connection(system);
      Telephone telephone = new Telephone(c, console);
      mainWindow mainwindow = new mainWindow();
      
      telephone.run(c);
   // mainwindow.main(args, c);
   }

   private static final int MAILBOX_COUNT = 20;
}
