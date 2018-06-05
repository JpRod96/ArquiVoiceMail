package test;

import Controllers.ConsoleController;
import Presenters.ConsolePresenter;
import Views.ConsoleViews.Console;
import main.Connected;
import main.Connection;
import main.InitialState;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Jp on 03/06/2018.
 */
public class ConsoleControllerTest {
    ConsoleController consoleController;
    Connection mockedConnection;
    ConsolePresenter consolePresenter;
    Scanner scanner;

    @Before
    public void init() {
        scanner=new Scanner(System.in);
        Console telephone=new Console(scanner);
        mockedConnection=mock(Connection.class);
        consolePresenter=new ConsolePresenter(telephone, mockedConnection);
        consoleController=new ConsoleController(mockedConnection, scanner,consolePresenter);
    }

    @Test
    public void shouldDialConnection(){
        String key="1";

        when(mockedConnection.get_state()).thenReturn(new Connected());

        consoleController.performAction(key);

        verify(mockedConnection).dial(key);
    }
}
