package Views.ConsoleViews;

import java.util.Scanner;

import Presenters.ConsolePresenter;
import main.Connection;
import main.Presenter;

public class Console
{

    private Scanner scanner;

   public Console(Scanner aScanner)
   {
      scanner = aScanner;
   }

   public void speak(String output)
   {
      System.out.println(output);
   }
}
