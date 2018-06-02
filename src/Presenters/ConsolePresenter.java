package Presenters;

import Controllers.ConsoleController;
import Views.ConsoleViews.Console;
import main.Connection;
import main.ConnectionState;
import main.Presenter;

/**
 * Created by Jp on 27/05/2018.
 */
public class ConsolePresenter implements Presenter{

    private Console console;
    private Connection connection;

    public ConsolePresenter(Console console, Connection connection){
        this.console = console;
        this.connection=connection;
        connection.addPresenter(this);
    }

    @Override
    public void parseModel(){
        ConnectionState connectionState=connection.get_state();
        String toShowString=FactoryForConsoleStrings.getStringByState(connectionState);
        console.speak(toShowString);
    }

    @Override
    public void parseModel(String toShowString){
        console.speak(toShowString);
    }

}
