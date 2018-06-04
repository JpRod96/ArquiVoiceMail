package test;

import Controllers.Util.ConnectionStateLog;
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
    public void noStatesRegistered(){
        connectionStateLog=new ConnectionStateLog();
        boolean isInitialStateEmpty=connectionStateLog.getInitialState()==null;
        boolean isFinalStateEmpty=connectionStateLog.getFinalState()==null;
        assertTrue(isInitialStateEmpty && isFinalStateEmpty);
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

    @Test
    public void shouldSetInitialState(){
        connectionStateLog.setInitialState(finalConnectionState);
        assertTrue(connectionStateLog.getInitialState() instanceof Connected);
    }

    @Test
    public void shouldSetFinalState(){
        connectionStateLog.setFinalState(initialConnectionState);
        assertTrue(connectionStateLog.getFinalState() instanceof InitialState);
    }
}

