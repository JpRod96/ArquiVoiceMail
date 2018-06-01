package test;

import main.Connection;
import MailVoice.Mailbox;
import main.MessageMenuState;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
/**
 * Created by CORE i7 on 25/04/2018.
 */
public class MessageMenuStateTest {
    Connection mockedConnection;
    MessageMenuState messageMenuState;

    @Before
    public void init() {
        mockedConnection = mock(Connection.class);
        messageMenuState=new MessageMenuState();
    }

    @Test
    public void shouldNotifyObserverWithMailBoxMenuTxt(){
        String key="4";

        when(mockedConnection.getCurrentMailbox()).thenReturn(new Mailbox(key, ""));

        messageMenuState.dial(key, mockedConnection);
        verify(mockedConnection).notifyPresenters(messageMenuState.MAILBOX_MENU_TEXT);
    }

}
