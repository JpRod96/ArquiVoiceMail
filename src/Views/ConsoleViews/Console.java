package Views.ConsoleViews;

import java.util.Scanner;

import Presenters.ConsolePresenter;
import main.Connection;
import main.Presenter;

public class Console implements Presenter
{
	private Connection connection;
	private ConsolePresenter consolePresenter;
    private Scanner scanner;

   public Console(Scanner aScanner)
   {
      scanner = aScanner;
   }

   public Console(Connection connection, Scanner aScanner) {
        scanner = aScanner;
		this.connection=connection;
		connection.addPresenter(this);
   }

   public Console(ConsolePresenter consolePresenter){
       this.consolePresenter=consolePresenter;
   }

	@Override
	public void parseModel() {

	}

    @Override
    public void parseModel(String string) {
        speak(string);
    }

   public void speak(String output)
   {
      System.out.println(output);
   }
}
