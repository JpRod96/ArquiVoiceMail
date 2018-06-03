package Controllers;

import MailVoice.Message;
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
        performActionBasedOnInitialState(log, input);
    }

    private void performActionBasedOnInitialState(ConnectionStateLog log, String input){

        if(log.getInitialState() instanceof Connected){
            if(log.wasThereAChangeOfStates()){
                consolePresenter.parseModel(connection.getCurrentMailbox().getGreeting());
            }else{
                if(connection.getAccumulatedKeys()=="")
                    consolePresenter.parseModel("Incorrect mailbox number. Try again!");
            }
        }

        if(log.getInitialState() instanceof Recording){
            if(!log.wasThereAChangeOfStates() && connection.getAccumulatedKeys()==""){
                consolePresenter.parseModel("Incorrect passcode. Try again!");
            }
        }

        if(log.getInitialState() instanceof MailBoxMenuState){
            if(log.wasThereAChangeOfStates() && log.getFinalState() instanceof ChangePassCode){
                consolePresenter.parseModel("Enter new passcode followed by the # key");
            }
            if(log.wasThereAChangeOfStates() && log.getFinalState() instanceof ChangeGreeting){
                consolePresenter.parseModel("Record your greeting, then press the # key");
            }
        }

        if(log.getInitialState() instanceof MessageMenuState && input.equalsIgnoreCase("1")){
            String output = "";
            Message m = connection.getCurrentMailbox().getCurrentMessage();
            if (m == null || m.getText().equals(""))
                output += "No messages." + "\n";
            else
                output += m.getText() + "\n";

            consolePresenter.parseModel(output);
            consolePresenter.parseModel();
        }
    }
}
