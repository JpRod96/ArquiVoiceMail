package observers;

import java.util.Scanner;

import main.Subject;

/**
   A telephone that takes simulated keystrokes and voice input
   from the user and simulates spoken text.
*/
public class Telephone implements StateWatcher
{
   /**
      Construct phone object.
      @param aScanner that reads text from a character-input stream
   */
	private Subject subject;

   public Telephone(Scanner aScanner)
   {
      scanner = aScanner;
   }
   
   public Telephone(Subject subject, Scanner aScanner) {
        scanner = aScanner;
		this.subject=subject;
		subject.addObserver(this);
	}
	
	@Override
	public void update(String updateString) {
		speak(updateString);
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
      @param connection the connection that connects this phone to the
      voice mail system
   */

   private Scanner scanner;
}
