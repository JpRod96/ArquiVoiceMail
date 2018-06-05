package test;

import Controllers.Util.ConnectionStateLog;
import Controllers.Util.RecordingPerformer;
import Presenters.ConsolePresenter;
import main.Connection;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Jp on 03/06/2018.
 */
public class RecordingPerformerTest {
    RecordingPerformer recordingPerformer;
    Connection mockedConnection;
    ConsolePresenter mockedConsolePresenter;
    ConnectionStateLog mockedConnectionStateLog;

    @Before
    public void init() {
        mockedConnection=mock(Connection.class);
        mockedConsolePresenter=mock(ConsolePresenter.class);
        recordingPerformer=new RecordingPerformer(mockedConnection,mockedConsolePresenter);
        mockedConnectionStateLog=mock(ConnectionStateLog.class);
    }

    @Test
    public void shouldPerformActionWithNoChangesInState(){
        String emptyKey="";
        String TEXT_FOR_INCORRECT_PASSCODE="Incorrect passcode. Try again!";
        when(mockedConnectionStateLog.wasThereAChangeOfStates()).thenReturn(false);
        when(mockedConnection.getAccumulatedKeys()).thenReturn("");

        recordingPerformer.performAction(mockedConnectionStateLog,emptyKey);

        verify(mockedConsolePresenter).parseModel(TEXT_FOR_INCORRECT_PASSCODE);
    }


}
