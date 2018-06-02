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
                performActionBasedOnStateLog(input);
            else
                connection.record(input);
        }
    }
    private void performActionBasedOnStateLog(String input){
        ConnectionStateLog log=new ConnectionStateLog();
        log.setInitialState(connection.get_state());
        connection.dial(input);
        log.setFinalState(connection.get_state());
        performActionBasedOnInitialState(log);
    }

    private void performActionBasedOnInitialState(ConnectionStateLog log){
        if(log.getInitialState() instanceof Connected){
            if(log.wasThereAChangeOfStates()){
                consolePresenter.parseModel(connection.getCurrentMailbox().getGreeting());
            }else{
                if(connection.getAccumulatedKeys()=="")
                    consolePresenter.parseModel("Incorrect mailbox number. Try again!");
            }
        }
    }
}
