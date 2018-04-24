package main;

public class Recording implements ConnectionState {
    @Override
    public void dial(String key, Connection connection) {
        if (key.equals("#"))
        {
            if (connection.currentMailbox.checkPasscode(connection.accumulatedKeys))
            {
                changeState(connection,new MailBoxMenuState());
                
                connection.notifyObservers(Connection.MAILBOX_MENU_TEXT);
            }
            else {
                connection.notifyObservers("Incorrect passcode. Try again!");
            }

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
