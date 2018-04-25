package test;

import main.Connection;
import main.Mailbox;
import main.MessageMenuState;
import org.junit.Before;
import org.junit.Test;
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
        
    }
}
