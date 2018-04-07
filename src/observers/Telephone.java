package observers;

import java.util.Scanner;
import main.*;

import main.Connection;
import main.Observable;

/**
   A telephone that takes simulated keystrokes and voice input
   from the user and simulates spoken text.
*/
public class Telephone implements Observer
{
   /**
      Construct phone object.
      @param aScanner that reads text from a character-input stream
   */
	private Observable observable;
   public Telephone(Scanner aScanner)
   {
      scanner = aScanner;
   }
   
   public Telephone(Observable observable) {
		this.observable=observable;
		observable.addObserver(this);
	}
	
	public void update(String update) {
		
		System.out.println(update);
	}

   /**
      Speak a message to System.out.
      @param output the text that will be "spoken"
   */
   public void speak(String output)
   {
      System.out.println(output);
   }

   /**
      Loops reading user input and passes the input to the
      Connection object's methods dial, record or hangup.
      @param c the connection that connects this phone to the
      voice mail system
   */
   public void run(Connection c)
   {
      boolean more = true;
      while (more)
      {
         String input = scanner.nextLine();
         if (input == null) return;
         if (input.equalsIgnoreCase("H"))
            c.hangup();
         else if (input.equalsIgnoreCase("Q"))
            more = false;
         else if (input.length() == 1
            && "1234567890#".indexOf(input) >= 0)
            c.dial(input);
         else
            c.record(input);
      }
   }

   private Scanner scanner;
}
