package Presenters;

import Views.ConsoleViews.MainWindow;
import main.Connection;
import main.ConnectionState;
import main.Presenter;


/**
 * Created by CORE i7 on 27/05/2018.
 */
public class  UIPresenter implements Presenter{

    private MainWindow mainWindow;
    private Connection connection;
    private boolean hasMessage=false;


    public UIPresenter(MainWindow mainWindow, Connection connection){
        this.mainWindow = mainWindow;
        this.connection=connection;
        connection.addPresenter(this);
    }

    @Override
    public void parseModel(){
        ConnectionState connectionState=connection.get_state();
        String toShowString=FactoryForConsoleStrings.getStringByState(connectionState);
        WindowChooser windowChooser = new WindowChooser(toShowString);
        windowChooser.assignWindow(mainWindow);
        windowChooser.isAMessage(this.hasMessage);
        windowChooser.generateOptions();
    }
    public void itHasAMessage(boolean hasMessage){
        this.hasMessage=hasMessage;
    }
    @Override
    public void parseModel(String toShowString){
        mainWindow.getFieldToWrite().setText("<html>" + toShowString.replaceAll("\n", "<br/>") + "</html>");
    }

}
