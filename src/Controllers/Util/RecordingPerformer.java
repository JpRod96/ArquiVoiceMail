package Controllers.Util;

import Presenters.ConsolePresenter;
import main.Connection;

/**
 * Created by Jp on 03/06/2018.
 */
public class RecordingPerformer implements ActionPerformer{
    private Connection connection;
    private ConsolePresenter consolePresenter;
    private final String TEXT_FOR_INCORRECT_PASSCODE="Incorrect passcode. Try again!";

    public RecordingPerformer(Connection connection, ConsolePresenter consolePresenter){
        this.connection=connection;
        this.consolePresenter=consolePresenter;
    }

    public void performAction(ConnectionStateLog log, String key){
        if(!log.wasThereAChangeOfStates() && connection.getAccumulatedKeys()==""){
            consolePresenter.parseModel(TEXT_FOR_INCORRECT_PASSCODE);
        }
    }
}
