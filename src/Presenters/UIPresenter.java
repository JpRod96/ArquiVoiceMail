package Presenters;

import Controllers.UIController;
import main.Connection;

import javax.swing.*;

/**
 * Created by CORE i7 on 27/05/2018.
 */
public class  UIPresenter{
    UIController UIController;

    public UIPresenter(UIController UIController){
        this.UIController = UIController;
    }

    public Connection getModelFromView(){
        return null;
    }
    public void hangUp(){
        UIController.hangUp();
    }
    public void recibeData(String key){
        UIController.recibeData(key);
    }
    public void record(String message){
        UIController.record(message);
    }
    public void assignMessage(JLabel jlabel,String updateString) {
        jlabel.setText("<html>" + updateString.replaceAll("\n", "<br/>") + "</html>");
    }
}
