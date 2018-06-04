package test;

import Controllers.Util.*;
import main.*;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Created by Jp on 03/06/2018.
 */
public class FactoryForActionPerformerTest {
    ConnectionState mockedConnectionState;

    @Test
    public void shouldReturnAConnectedPerformer(){
        mockedConnectionState=mock(Connected.class);

        ActionPerformer actionPerformer=FactoryForActionPerformer.getActionPerformerByInitialState(mockedConnectionState,null,null);

        assertTrue(actionPerformer instanceof ConnectedPerformer);
    }

    @Test
    public void shouldReturnARecordingPerformer(){
        mockedConnectionState=mock(Recording.class);

        ActionPerformer actionPerformer=FactoryForActionPerformer.getActionPerformerByInitialState(mockedConnectionState,null,null);

        assertTrue(actionPerformer instanceof RecordingPerformer);
    }

    @Test
    public void shouldReturnAMailboxPerformer(){
        mockedConnectionState=mock(MailBoxMenuState.class);

        ActionPerformer actionPerformer=FactoryForActionPerformer.getActionPerformerByInitialState(mockedConnectionState,null,null);

        assertTrue(actionPerformer instanceof MailboxPerformer);
    }

    @Test
    public void shouldReturnAMessageMenuPerformer(){
        mockedConnectionState=mock(MessageMenuState.class);

        ActionPerformer actionPerformer=FactoryForActionPerformer.getActionPerformerByInitialState(mockedConnectionState,null,null);

        assertTrue(actionPerformer instanceof MessageMenuPerformer);
    }

    @Test
    public void shouldReturnNull(){
        mockedConnectionState=mock(ChangePassCode.class);

        ActionPerformer actionPerformer=FactoryForActionPerformer.getActionPerformerByInitialState(mockedConnectionState,null,null);

        assertTrue(actionPerformer==null);
    }

}
