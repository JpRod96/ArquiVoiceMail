package Controllers;

import Presenters.ConsolePresenter;
import main.*;

import java.util.Scanner;

/**
 * Created by CORE i7 on 27/05/2018.
 */
public class ConsoleController{
    private Scanner scanner;
    private ConsolePresenter consolePresenter;
    private Connection connection;

    public ConsoleController(Connection connection, Scanner scanner, ConsolePresenter consolePresenter){
        this.scanner=scanner;
        this.consolePresenter=consolePresenter;
        this.connection=connection;
    }

    public void run()
    {
        boolean more = true;
        while (more)
        {
            String input = scanner.nextLine();
            if (input == null)
                return;
            if (input.equalsIgnoreCase("H"))
                connection.hangup();
            else if (input.equalsIgnoreCase("Q"))
                more = false;
            else if (input.length() == 1 && "1234567890#".indexOf(input) >= 0)
                connection.dial(input);
            else
                connection.record(input);
        }
    }
}
