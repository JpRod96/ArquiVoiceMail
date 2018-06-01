package main;

/**
 * Created by Jp on 24/04/2018.
 */
public class ChangeGreeting implements ConnectionState{
    Connection connection;

    public static final String MAILBOX_MENU_TEXT =
            "Enter 1 to listen to your messages\n"
                    + "Enter 2 to change your passcode\n"
                    + "Enter 3 to change your greeting";

    @Override
    public void dial(String key, Connection connection) {
        this.connection=connection;
        if (key.equals("#"))
        {
            connection.getCurrentMailbox().setGreeting(connection.getCurrentRecording());
            if(connection.getMailBoxRepository()!=null)
                connection.getMailBoxRepository().updateMailbox(connection.getCurrentMailbox());
            connection.setCurrentRecording("");
            changeState(connection, new MailBoxMenuState());
            connection.notifyObservers(MAILBOX_MENU_TEXT);
        }
    }

    @Override
    public void changeState(Connection connection, ConnectionState connectionState) {
        connection.changeState(connectionState);
    }

    @Override
    public void record(Connection connection, String voice) {
        connection.setCurrentRecording(voice);
    }

    @Override
    public void hangUp(Connection connection) {

    }
}

