package test;

import main.ChangePassCode;
import main.Connection;
import MailVoice.Mailbox;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Jp on 24/04/2018.
 */
public class ChangePassCodeTest {

    public static final String MAILBOX_MENU_TEXT =
            "Enter 1 to listen to your messages\n"
                    + "Enter 2 to change your passcode\n"
                    + "Enter 3 to change your greeting";

    Connection mockedConnection;
    ChangePassCode changePassCode;

    @Before
    public void init() {
        mockedConnection = mock(Connection.class);
        changePassCode=new ChangePassCode();
    }

    @Test
    public void shouldNotifyObserversWithMailBoxMenuText(){
        String key="#";

        when(mockedConnection.getCurrentMailbox()).thenReturn(new Mailbox(key, ""));

        changePassCode.dial(key, mockedConnection);
        verify(mockedConnection).notifyPresenters();
    }

}
