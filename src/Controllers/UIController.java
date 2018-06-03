package Controllers;

import MailVoice.Message;
import Presenters.UIPresenter;
import main.*;

import javax.swing.*;

/**
 * Created by CORE i7 on 27/05/2018.
 */
public class UIController{
    private ConnectionState connectionState;
    private Connection connection;
    private UIPresenter uiPresenter;




    public UIController(Connection connection,UIPresenter uiPresenter){
        this.connection = connection;
        this.connectionState = connection.get_state();
        this.uiPresenter = uiPresenter;
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

