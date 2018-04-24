package main;

/**
 * Created by Jp on 24/04/2018.
 */
public class ChangeGreeting implements ConnectionState{
    @Override
    public void dial(String key, Connection connection) {
        if (key.equals("#"))
        {
            connection.currentMailbox.setGreeting(connection.currentRecording);
            connection.currentRecording = "";
            //connection.state = connection.MAILBOX_MENU;
            changeState(connection, new MailBoxMenuState());
            connection.notifyObservers(connection.MAILBOX_MENU_TEXT);
        }
    }

    @Override
    public void changeState(Connection connection, ConnectionState connectionState) {
        connection.changeState(connectionState);
    }
}
