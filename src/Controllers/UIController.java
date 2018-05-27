package Controllers;

import main.Connected;
import main.Connection;
import main.ConnectionState;
import observers.StateWatcher;

/**
 * Created by CORE i7 on 27/05/2018.
 */
public class UIController implements StateWatcher {
    private ConnectionState connectionState;
    private Connection connection;

    public UIController(Connection connection){
        this.connection = connection;
        this.connectionState = connection.get_state();
    }
    public void update(String updateString){
        connectionState=connection.get_state();

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
