package main;

public class Recording
        implements ConnectionState {
    Connection connection;
    public static final String MAILBOX_MENU_TEXT =
            "Enter 1 to listen to your messages\n"
                    + "Enter 2 to change your passcode\n"
                    + "Enter 3 to change your greeting";

    @Override
    public void dial(String key, Connection connection) {
        this.connection = connection;
        if (key.equals("#"))
        {
            if (connection.getCurrentMailbox().checkPasscode(connection.getAccumulatedKeys()))
            {
                changeState(connection,new MailBoxMenuState());

                connection.notifyObservers(MAILBOX_MENU_TEXT);
            }
            else {
                connection.notifyObservers("Incorrect passcode. Try again!");
            }

            connection.setAccumulatedKeys("");
        }
        else
            connection.setAccumulatedKeys(connection.getAccumulatedKeys() + key);
    }

    @Override
    public void changeState(Connection connection, ConnectionState connectionState) {
        connection.changeState(connectionState);
    }

    @Override
    public void record() {
        connection.setCurrentRecording(connection.getCurrentRecording());
    }

    @Override
    public void hangUp() {

    }
}
