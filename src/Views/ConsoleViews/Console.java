package Views.ConsoleViews;

import java.util.Scanner;

import Presenters.ConsolePresenter;
import main.Connection;
import main.Presenter;

public class Console
{
	private Connection connection;
	private ConsolePresenter consolePresenter;
    private Scanner scanner;

   public Console(Scanner aScanner)
   {
      scanner = aScanner;
   }

   public Console(ConsolePresenter consolePresenter){
       this.consolePresenter=consolePresenter;
   }

   public void speak(String output)
   {
      System.out.println(output);
   }
}
