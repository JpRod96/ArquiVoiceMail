package test;

import Controllers.Util.MailboxPerformer;
import Controllers.Util.ConnectionStateLog;
import Presenters.ConsolePresenter;
import main.ChangeGreeting;
import main.ChangePassCode;
import main.Connection;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Jp on 03/06/2018.
 */
public class MailBoxPerformerTest {
    MailboxPerformer mailboxPerformer;
    Connection mockedConnection;
    ConsolePresenter mockedConsolePresenter;
    ConnectionStateLog mockedConnectionStateLog;

    @Before
    public void init() {
        mockedConnection=mock(Connection.class);
        mockedConsolePresenter=mock(ConsolePresenter.class);
        mailboxPerformer=new MailboxPerformer(mockedConnection,mockedConsolePresenter);
        mockedConnectionStateLog=mock(ConnectionStateLog.class);
    }

    @Test
    public void shouldPerformActionWithChangePasscodeState(){
        String emptyKey="",
                STRING_FOR_CHANGEPASSCODE = "Enter new passcode followed by the # key";
        when(mockedConnectionStateLog.getFinalState()).thenReturn(new ChangePassCode());
        when(mockedConnectionStateLog.wasThereAChangeOfStates()).thenReturn(true);

        mailboxPerformer.performAction(mockedConnectionStateLog,emptyKey);

        verify(mockedConsolePresenter).parseModel(STRING_FOR_CHANGEPASSCODE);

    }

    @Test
    public void shouldPerformActionWithChangeGreetingState(){
        String emptyKey="",
                STRING_FOR_CHANGE_GREETING = "Record your greeting, then press the # key";
        when(mockedConnectionStateLog.getFinalState()).thenReturn(new ChangeGreeting());
        when(mockedConnectionStateLog.wasThereAChangeOfStates()).thenReturn(true);

        mailboxPerformer.performAction(mockedConnectionStateLog,emptyKey);

        verify(mockedConsolePresenter).parseModel(STRING_FOR_CHANGE_GREETING);

    }
}
