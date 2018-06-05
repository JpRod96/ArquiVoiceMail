package Controllers.Util;


import MailVoice.Mailbox;
import Presenters.ConsolePresenter;
import main.Connection;

/**
 * Created by Jp on 03/06/2018.
 */
public class ConnectedPerformer implements ActionPerformer{
    private Connection connection;
    private ConsolePresenter consolePresenter;
    private final String STRING_FOR_INCORRECT_MAILBOX_NUMBER="Incorrect mailbox number. Try again!";

    public ConnectedPerformer(Connection connection, ConsolePresenter consolePresenter){
        this.connection=connection;
        this.consolePresenter=consolePresenter;
    }

    public void performAction(ConnectionStateLog log,String key){
        if(log.wasThereAChangeOfStates()){
            Mailbox currentMailbox=connection.getCurrentMailbox();
            consolePresenter.parseModel(currentMailbox.getGreeting());
        }else{
            if(connection.getAccumulatedKeys()=="")
                consolePresenter.parseModel(STRING_FOR_INCORRECT_MAILBOX_NUMBER);
        }
    }
}
