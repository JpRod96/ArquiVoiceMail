package Presenters;

import Controllers.InterfaceController;

import javax.swing.*;

/**
 * Created by CORE i7 on 27/05/2018.
 */
public class InterfacePresenter implements Presenter {
    InterfaceController interfaceController;

    public InterfacePresenter(InterfaceController interfaceController){
        this.interfaceController = interfaceController;
    }

    public void showView(Object e){

    }
    public void hangUp(){
        interfaceController.hangUp();
    }
    public void recibeData(String key){
        interfaceController.recibeData(key);
    }
    public void record(String message){
        interfaceController.record(message);
    }
    public void assignMessage(JLabel jlabel,String updateString) {
        jlabel.setText("<html>" + updateString.replaceAll("\n", "<br/>") + "</html>");
    }
}
