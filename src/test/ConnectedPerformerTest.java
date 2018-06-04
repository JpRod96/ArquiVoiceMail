package test;

import Controllers.Util.ConnectedPerformer;
import Controllers.Util.ConnectionStateLog;
import MailVoice.Mailbox;
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
public class ConnectedPerformerTest {
    ConnectedPerformer connectedPerformer;
    Connection mockedConnection;
    ConsolePresenter mockedConsolePresenter;
    ConnectionStateLog mockedConnectionStateLog;

    @Before
    public void init() {
        mockedConnection=mock(Connection.class);
        mockedConsolePresenter=mock(ConsolePresenter.class);
        connectedPerformer=new ConnectedPerformer(mockedConnection,mockedConsolePresenter);
        mockedConnectionStateLog=mock(ConnectionStateLog.class);
    }

    @Test
    public void shouldPerformActionWithChangesInState(){
        String emptyKey="",
        passcode="1",
        greeting="Prueba";
        Mailbox mailboxTest=new Mailbox(passcode, greeting);
        when(mockedConnectionStateLog.wasThereAChangeOfStates()).thenReturn(true);
        when(mockedConnection.getCurrentMailbox()).thenReturn(mailboxTest);

        connectedPerformer.performAction(mockedConnectionStateLog,emptyKey);

        verify(mockedConsolePresenter).parseModel(mailboxTest.getGreeting());

    }

    @Test
    public void shouldPerformActionWithoutChangesInState(){
        String emptyKey="",
        outputForIncorrectNumber="Incorrect mailbox number. Try again!";
        when(mockedConnectionStateLog.wasThereAChangeOfStates()).thenReturn(false);
        when(mockedConnection.getAccumulatedKeys()).thenReturn("");

        connectedPerformer.performAction(mockedConnectionStateLog,emptyKey);

        verify(mockedConsolePresenter).parseModel(outputForIncorrectNumber);

    }
}
