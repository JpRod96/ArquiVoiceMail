package observers;

import java.util.Scanner;
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

	public Telephone(){}
   public Telephone(Scanner aScanner)
   {
      scanner = aScanner;
   }
   
   public Telephone(Observable observable, Scanner aScanner) {
        scanner = aScanner;
		this.observable=observable;
		observable.addObserver(this);
	}
	
	@Override
	public void update() {
		speak(observable.toString());
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
