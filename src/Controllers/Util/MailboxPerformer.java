package Controllers.Util;

import Presenters.ConsolePresenter;
import main.ChangeGreeting;
import main.ChangePassCode;
import main.Connection;

/**
 * Created by Jp on 03/06/2018.
 */
public class MailboxPerformer implements ActionPerformer{
    private Connection connection;
    private ConsolePresenter consolePresenter;
    private final String STRING_FOR_CHANGEPASSCODE = "Enter new passcode followed by the # key",
            STRING_FOR_CHANGE_GREETING="Record your greeting, then press the # key";

    public MailboxPerformer(Connection connection, ConsolePresenter consolePresenter){
        this.connection=connection;
        this.consolePresenter=consolePresenter;
    }

    public void performAction(ConnectionStateLog log, String key){
        if(log.wasThereAChangeOfStates() && isChangePasscode(log)){
            consolePresenter.parseModel(STRING_FOR_CHANGEPASSCODE);
        }
        if(log.wasThereAChangeOfStates() && isChangeGreeting(log)){
            consolePresenter.parseModel(STRING_FOR_CHANGE_GREETING);
        }
    }

    private boolean isChangeGreeting(ConnectionStateLog log) {
        return log.getFinalState() instanceof ChangeGreeting;
    }

    private boolean isChangePasscode(ConnectionStateLog log) {
        return log.getFinalState() instanceof ChangePassCode;
    }


}
