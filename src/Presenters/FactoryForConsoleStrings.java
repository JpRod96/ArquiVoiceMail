package Presenters;

import main.*;

/**
 * Created by Jp on 27/05/2018.
 */
public class FactoryForConsoleStrings {

    private static final String MEMORY_STORAGE_OPTION=
                                "Enter 1 to use in memory storage\n" +
                                        "Enter 2 to use persistence";

    private static final String INITIAL_PROMPT =
            "Enter mailbox number followed by #";

    public static final String MAILBOX_MENU_TEXT =
            "Enter 1 to listen to your messages\n"
                    + "Enter 2 to change your passcode\n"
                    + "Enter 3 to change your greeting";


    public static final String MESSAGE_MENU_TEXT =
            "Enter 1 to listen to the current message\n"
                    + "Enter 2 to save the current message\n"
                    + "Enter 3 to delete the current message\n"
                    + "Enter 4 to return to the main menu";



    public static String getStringByState(ConnectionState connectionState){
        String outputString="";
        if(isInitial(connectionState)){
            outputString=MEMORY_STORAGE_OPTION;
        }
        else if(isConnected(connectionState)){
            outputString= INITIAL_PROMPT;
        }
        else if(isRecording(connectionState) || isChangeGreeting(connectionState) || isChangePassCode(connectionState) ){
            outputString= MAILBOX_MENU_TEXT;
        }
        else if(isMailBoxMenu(connectionState)){
            outputString= MAILBOX_MENU_TEXT;
        }
        else {
            outputString= MESSAGE_MENU_TEXT;
        }
        return outputString;
    }

    public static boolean isInitial(ConnectionState connectionState) {
        return connectionState instanceof InitialState;
    }

    public static boolean isConnected(ConnectionState connectionState) {
        return connectionState instanceof Connected;
    }

    public static boolean isRecording(ConnectionState connectionState) {
        return connectionState instanceof Recording;
    }

    public static boolean isChangePassCode(ConnectionState connectionState) {
        return connectionState instanceof ChangePassCode;
    }

    public static boolean isChangeGreeting(ConnectionState connectionState) {
        return connectionState instanceof ChangeGreeting;
    }

    public static boolean isMailBoxMenu(ConnectionState connectionState) {
        return connectionState instanceof MailBoxMenuState;
    }

    public static boolean isMessageMenu(ConnectionState connectionState) {
        return connectionState instanceof MessageMenuState;
    }
}
