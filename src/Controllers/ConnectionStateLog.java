package Controllers;

import main.ConnectionState;

/**
 * Created by Jp on 02/06/2018.
 */
public class ConnectionStateLog {
    private ConnectionState initialState;
    private ConnectionState finalState;

    ConnectionStateLog(){}

    ConnectionStateLog(ConnectionState initialState, ConnectionState finalState){
        this.initialState =initialState;
        this.finalState=finalState;
    }

    public boolean wasThereAChangeOfStates(){
        return initialState!=finalState;
    }

    public ConnectionState getInitialState() {
        return initialState;
    }

    public void setInitialState(ConnectionState initialState) {
        this.initialState = initialState;
    }

    public ConnectionState getFinalState() {
        return finalState;
    }

    public void setFinalState(ConnectionState finalState) {
        this.finalState = finalState;
    }
}
