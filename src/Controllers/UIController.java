package Controllers;

import Controllers.Util.ConnectionStateLog;
import MailVoice.Mailbox;
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
    private final String TEXT_FOR_EMPTY_QUEUE="No messages.\n";

    public UIController(Connection connection,UIPresenter uiPresenter){
        this.connection = connection;
        this.connectionState = connection.get_state();
        this.uiPresenter = uiPresenter;
    }

    public void performActionBasedOnStateLog(String input){
        ConnectionStateLog log=new ConnectionStateLog();
        log.setInitialState(connection.get_state());
        connection.dial(input);
        log.setFinalState(connection.get_state());
        performActionBasedOnInitialState(log, input);
    }

    private void performActionBasedOnInitialState(ConnectionStateLog log, String input){

        if(log.getInitialState() instanceof Connected){

            if(log.wasThereAChangeOfStates()){
                uiPresenter.parseModel(connection.getCurrentMailbox().getGreeting());
            }else{
                if(connection.getAccumulatedKeys()=="")
                    uiPresenter.parseModel("Incorrect mailbox number. Try again!");
                    uiPresenter.itHasAMessage(false);
            }
        }

        if(log.getInitialState() instanceof Recording){

            if(!log.wasThereAChangeOfStates() && connection.getAccumulatedKeys()==""){

                uiPresenter.parseModel("Incorrect passcode. Try again!");
                uiPresenter.itHasAMessage(false);
            }
        }

        if(log.getInitialState() instanceof MailBoxMenuState){

            if(log.wasThereAChangeOfStates() && log.getFinalState() instanceof ChangePassCode){

                uiPresenter.parseModel("Enter new passcode followed by the # key");
                uiPresenter.itHasAMessage(false);
            }
            if(log.wasThereAChangeOfStates() && log.getFinalState() instanceof ChangeGreeting){

                uiPresenter.parseModel("Record your greeting, then press the # key");
                uiPresenter.itHasAMessage(false);
            }
        }

        if(log.getInitialState() instanceof MessageMenuState && input.equalsIgnoreCase("1")){

            String output = "";
            Mailbox currentMailbox=connection.getCurrentMailbox();
            Message message = currentMailbox.getCurrentMessage();
            if (message == null || message.getText().equals(""))
                output += TEXT_FOR_EMPTY_QUEUE;
            else
                output += message.getText() + "\n";
            uiPresenter.itHasAMessage(true);
            uiPresenter.parseModel(output);
            uiPresenter.parseModel();
        }

    }

    public void hangUp()
    {
        connection.hangup();
    }

    public void recibeData(String key){
        performActionBasedOnStateLog(key);
    }
    public void record(String message){

        connection.record(message);
    }
}

