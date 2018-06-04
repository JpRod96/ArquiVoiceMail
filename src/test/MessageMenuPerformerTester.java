package test;

import Controllers.Util.ConnectionStateLog;
import Controllers.Util.MessageMenuPerformer;
import MailVoice.Mailbox;
import MailVoice.Message;
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
public class MessageMenuPerformerTester {
    MessageMenuPerformer messageMenuPerformer;
    Connection mockedConnection;
    ConsolePresenter mockedConsolePresenter;
    ConnectionStateLog mockedConnectionStateLog;

    @Before
    public void init() {
        mockedConnection=mock(Connection.class);
        mockedConsolePresenter=mock(ConsolePresenter.class);
        messageMenuPerformer=new MessageMenuPerformer(mockedConnection,mockedConsolePresenter);
        mockedConnectionStateLog=mock(ConnectionStateLog.class);
    }

    @Test
    public void shouldPerformActionWithAMessageInQueue(){
        String key="1";
        Mailbox mailbox=new Mailbox("","");
        Message message=new Message("Hola");
        mailbox.addMessage(message);

        when(mockedConnection.getCurrentMailbox()).thenReturn(mailbox);

        messageMenuPerformer.performAction(mockedConnectionStateLog,key);

        verify(mockedConsolePresenter).parseModel(message.getText()+ "\n");
    }

    @Test
    public void shouldPerformActionWithNoMessagesInQueue(){
        String key="1";
        String TEXT_FOR_EMPTY_QUEUE="No messages.\n";
        Mailbox mailbox=new Mailbox("","");

        when(mockedConnection.getCurrentMailbox()).thenReturn(mailbox);

        messageMenuPerformer.performAction(mockedConnectionStateLog,key);

        verify(mockedConsolePresenter).parseModel(TEXT_FOR_EMPTY_QUEUE);
    }
}
