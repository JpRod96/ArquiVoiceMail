package main;

/**
 * Created by Jp on 24/04/2018.
 */
public class ChangePassCode implements ConnectionState{

    public static final String MAILBOX_MENU_TEXT =
            "Enter 1 to listen to your messages\n"
                    + "Enter 2 to change your passcode\n"
                    + "Enter 3 to change your greeting";

    @Override
    public void dial(String key, Connection connection) {
        if (key.equals("#"))
        {
            connection.getCurrentMailbox().setPasscode(connection.getAccumulatedKeys());
            connection.getMailBoxRepository().updateMailbox(connection.getCurrentMailbox());
            changeState(connection,new MailBoxMenuState());
            connection.notifyObservers(MAILBOX_MENU_TEXT);
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
    public void record(Connection connection, String voice) {

    }

    @Override
    public void hangUp(Connection connection) {

    }

}
