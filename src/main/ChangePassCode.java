package main;

/**
 * Created by Jp on 24/04/2018.
 */
public class ChangePassCode implements ConnectionState{
    @Override
    public void dial(String key, Connection connection) {
        if (key.equals("#"))
        {
            connection.currentMailbox.setPasscode(connection.accumulatedKeys);
            changeState(connection,new MailBoxMenuState());
            connection.notifyObservers(connection.MAILBOX_MENU_TEXT);
            connection.accumulatedKeys = "";
        }
        else
            connection.accumulatedKeys += key;
    }

    @Override
    public void changeState(Connection connection, ConnectionState connectionState) {
        connection.changeState(connectionState);
    }
}
