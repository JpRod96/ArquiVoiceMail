package Controllers;

import main.Connection;
import main.ConnectionState;
import main.Presenter;

/**
 * Created by CORE i7 on 27/05/2018.
 */
public class UIController{
    private ConnectionState connectionState;
    private Connection connection;

    public UIController(Connection connection){
        this.connection = connection;
        this.connectionState = connection.get_state();
    }
    public void update(String updateString){
        connectionState=connection.get_state();

    }
    public void addObserver(Presenter presenter){
        connection.addPresenter(presenter);
    }
    public void hangUp(){
        connection.hangup();
    }
    public void recibeData(String key){
        connection.reciveData(key);
    }
    public void record(String message){
        connection.record(message);
    }
}
