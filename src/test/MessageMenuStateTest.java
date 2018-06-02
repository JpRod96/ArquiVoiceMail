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
    public static final String MESSAGE_MENU_TEXT =
            "Enter 1 to listen to the current message\n"
                    + "Enter 2 to save the current message\n"
                    + "Enter 3 to delete the current message\n"
                    + "Enter 4 to return to the main menu";
    public static final String MAILBOX_MENU_TEXT =
            "Enter 1 to listen to your messages\n"
                    + "Enter 2 to change your passcode\n"
                    + "Enter 3 to change your greeting";
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
        verify(mockedConnection).notifyPresenters();
    }

}
