package observers;

import java.util.Scanner;

import Presenters.ConsolePresenter;
import main.Connection;

public class Telephone implements StateWatcher
{
	private Connection connection;
	private ConsolePresenter consolePresenter;
    private Scanner scanner;

   public Telephone(Scanner aScanner)
   {
      scanner = aScanner;
   }

   public Telephone(Connection connection, Scanner aScanner) {
        scanner = aScanner;
		this.connection=connection;
		connection.addObserver(this);
   }

   public Telephone(ConsolePresenter consolePresenter){
       this.consolePresenter=consolePresenter;
   }

	@Override
	public void update(String updateString) {
		speak(updateString);
	}

   public void speak(String output)
   {
      System.out.println(output);
   }
}
