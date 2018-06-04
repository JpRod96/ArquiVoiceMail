package Controllers.Util;

import Presenters.ConsolePresenter;
import main.*;

/**
 * Created by Jp on 03/06/2018.
 */
public class FactoryForActionPerformer {

    public static ActionPerformer getActionPerformerByInitialState(ConnectionState initialState, Connection connection, ConsolePresenter consolePresenter){
        if(isConnected(initialState)){
            return new ConnectedPerformer(connection,consolePresenter);
        }

        if(isRecording(initialState)){
            return new RecordingPerformer(connection,consolePresenter);
        }

        if(isMailBoxMenuState(initialState)){
            return new MailboxPerformer(connection,consolePresenter);
        }

        if(isMessageMenuState(initialState)){
            return new MessageMenuPerformer(connection,consolePresenter);
        }
        return null;
    }

    private static boolean isMessageMenuState(ConnectionState connectionState) {
        return connectionState instanceof MessageMenuState;
    }

    private static boolean isMailBoxMenuState(ConnectionState connectionState) {
        return connectionState instanceof MailBoxMenuState;
    }

    private static boolean isRecording(ConnectionState connectionState) {
        return connectionState instanceof Recording;
    }

    private static boolean isConnected(ConnectionState connectionState) {
        return connectionState instanceof Connected;
    }
}
