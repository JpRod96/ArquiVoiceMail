package test;

import Controllers.ConnectionStateLog;
import main.Connected;
import main.ConnectionState;
import main.InitialState;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Created by Jp on 03/06/2018.
 */
public class ConnectionStateLogTest {
    ConnectionStateLog connectionStateLog;
    ConnectionState initialConnectionState;
    ConnectionState finalConnectionState;

    @Before
    public void init() {
        initialConnectionState=mock(InitialState.class);
        finalConnectionState=mock(Connected.class);
        connectionStateLog=new ConnectionStateLog(initialConnectionState,finalConnectionState);
    }

    @Test
    public void thereShouldBeAChangeInStates(){
        assertTrue(connectionStateLog.wasThereAChangeOfStates());
    }

    @Test
    public void thereShouldNotBeAChangeInStates(){
        connectionStateLog.setFinalState(initialConnectionState);
        assertFalse(connectionStateLog.wasThereAChangeOfStates());
    }
}

