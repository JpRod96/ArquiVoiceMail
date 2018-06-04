package test;

import Presenters.ConsolePresenter;
import Views.ConsoleViews.Console;
import main.*;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Jp on 04/06/2018.
 */
public class ConsolePresenterTest {
    ConsolePresenter consolePresenter;
    Connection mockedConnection;
    Console mockedConsole;

    @Before
    public void init(){
        mockedConnection=mock(Connection.class);
        mockedConsole=mock(Console.class);
        consolePresenter=new ConsolePresenter(mockedConsole,mockedConnection);
    }

    @Test
    public void shouldParseModelForInitialState(){
        String MEMORY_STORAGE_OPTION=
                "Enter 1 to use in memory storage\n" +
                        "Enter 2 to use persistence";
        ConnectionState state=new InitialState();

        when(mockedConnection.get_state()).thenReturn(state);

        consolePresenter.parseModel();

        verify(mockedConsole).speak(MEMORY_STORAGE_OPTION);
    }

    @Test
    public void shouldParseModelForConnectedState(){
        String INITIAL_PROMPT =
                "Enter mailbox number followed by #";
        ConnectionState state=new Connected();

        when(mockedConnection.get_state()).thenReturn(state);

        consolePresenter.parseModel();

        verify(mockedConsole).speak(INITIAL_PROMPT);
    }

    @Test
    public void shouldParseModelForRecordingState(){
        String MAILBOX_MENU_TEXT =
                "Enter 1 to listen to your messages\n"
                        + "Enter 2 to change your passcode\n"
                        + "Enter 3 to change your greeting";
        ConnectionState state=new Recording();

        when(mockedConnection.get_state()).thenReturn(state);

        consolePresenter.parseModel();

        verify(mockedConsole).speak(MAILBOX_MENU_TEXT);
    }

    @Test
    public void shouldParseModelForChnagePasscodeState(){
        String MAILBOX_MENU_TEXT =
                "Enter 1 to listen to your messages\n"
                        + "Enter 2 to change your passcode\n"
                        + "Enter 3 to change your greeting";
        ConnectionState state=new ChangePassCode();

        when(mockedConnection.get_state()).thenReturn(state);

        consolePresenter.parseModel();

        verify(mockedConsole).speak(MAILBOX_MENU_TEXT);
    }

    @Test
    public void shouldParseModelForChangeGreetingState(){
        String MAILBOX_MENU_TEXT =
                "Enter 1 to listen to your messages\n"
                        + "Enter 2 to change your passcode\n"
                        + "Enter 3 to change your greeting";
        ConnectionState state=new ChangeGreeting();

        when(mockedConnection.get_state()).thenReturn(state);

        consolePresenter.parseModel();

        verify(mockedConsole).speak(MAILBOX_MENU_TEXT);
    }

    @Test
    public void shouldParseModelForMailBoxMenuState(){
        String MAILBOX_MENU_TEXT =
                "Enter 1 to listen to your messages\n"
                        + "Enter 2 to change your passcode\n"
                        + "Enter 3 to change your greeting";
        ConnectionState state=new MailBoxMenuState();

        when(mockedConnection.get_state()).thenReturn(state);

        consolePresenter.parseModel();

        verify(mockedConsole).speak(MAILBOX_MENU_TEXT);
    }

    @Test
    public void shouldParseModelForMessageMenuState(){
        String MESSAGE_MENU_TEXT =
                "Enter 1 to listen to the current message\n"
                        + "Enter 2 to save the current message\n"
                        + "Enter 3 to delete the current message\n"
                        + "Enter 4 to return to the main menu";
        ConnectionState state=new MessageMenuState();

        when(mockedConnection.get_state()).thenReturn(state);

        consolePresenter.parseModel();

        verify(mockedConsole).speak(MESSAGE_MENU_TEXT);
    }

    @Test
    public void shouldParseModelForUnknownState(){
        when(mockedConnection.get_state()).thenReturn(null);

        consolePresenter.parseModel();

        verify(mockedConsole).speak("");
    }

    @Test
    public void shouldPaerModelWithAString(){
        String stringToParse="this is a test";

        consolePresenter.parseModel(stringToParse);

        verify(mockedConsole).speak(stringToParse);
    }
}
