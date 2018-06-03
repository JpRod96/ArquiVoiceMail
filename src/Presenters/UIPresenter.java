package Presenters;

import Views.ConsoleViews.MainWindow;
import main.Connection;
import main.ConnectionState;
import main.Presenter;

import javax.swing.*;


/**
 * Created by CORE i7 on 27/05/2018.
 */
public class  UIPresenter implements Presenter{

    private MainWindow mainWindow;
    private Connection connection;

    private JLabel messageToShow;
    private JTextArea userInput;

    public UIPresenter(MainWindow mainWindow, Connection connection){
        this.mainWindow = mainWindow;
        this.connection=connection;
        connection.addPresenter(this);
    }



    @Override
    public void parseModel(){
        ConnectionState connectionState=connection.get_state();
        String toShowString=FactoryForConsoleStrings.getStringByState(connectionState);

        mainWindow.getFieldToWrite().setText("<html>" + toShowString.replaceAll("\n", "<br/>") + "</html>");
    }

    @Override
    public void parseModel(String toShowString){

    }
}
