package Controllers;

import main.Connection;
import main.ConnectionState;
import observers.StateWatcher;

/**
 * Created by CORE i7 on 27/05/2018.
 */
public class InterfaceController implements StateWatcher {
    private ConnectionState connectionState;
    private Connection connection;

    public InterfaceController (Connection connection){
        this.connection = connection;
        this.connectionState = connection.get_state();
    }
    public void update(String updateString){

    }
    public void addObserver(StateWatcher stateWatcher){
        connection.addObserver(stateWatcher);
    }
    public void hangUp(){
        connection.hangup();
    }
    public void recibeData(String key){
        connection.recibeData(key);
    }
    public void record(String message){
        connection.record(message);
    }
}
