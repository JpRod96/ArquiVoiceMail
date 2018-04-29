package main;

/**
 * Created by CORE i7 on 24/04/2018.
 */
public class MailBoxMenuState implements ConnectionState {

    public static final String MESSAGE_MENU_TEXT =
            "Enter 1 to listen to the current message\n"
                    + "Enter 2 to save the current message\n"
                    + "Enter 3 to delete the current message\n"
                    + "Enter 4 to return to the main menu";
    @Override
    public void dial (String key, Connection connection){

            if (key.equals("1"))
            {
                changeState(connection,new MessageMenuState());
                connection.notifyObservers(MESSAGE_MENU_TEXT);
            }
            else if (key.equals("2"))
            {
                changeState(connection,new ChangePassCode());
                connection.notifyObservers("Enter new passcode followed by the # key");
            }
            else if (key.equals("3"))
            {
                changeState(connection, new ChangeGreeting());
                connection.notifyObservers("Record your greeting, then press the # key");
            }
    }
    @Override
    public void changeState(Connection connection, ConnectionState connectionState) {
        connection.changeState(connectionState);
    }

    @Override
    public void record() {

    }

    @Override
    public void hangUp() {

    }
}
