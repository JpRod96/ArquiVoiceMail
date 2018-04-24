package main;

/**
 * Created by CORE i7 on 24/04/2018.
 */
public class MailBoxMenuState implements ConnectionState {
    @Override
    public void dial (String key, Connection connection){

            if (key.equals("1"))
            {
                connection.state = connection.MESSAGE_MENU;
                connection.notifyObservers(connection.MESSAGE_MENU_TEXT);
            }
            else if (key.equals("2"))
            {
                connection.state=connection.CHANGE_PASSCODE;
                connection.notifyObservers("Enter new passcode followed by the # key");
            }
            else if (key.equals("3"))
            {
                connection.state=connection.CHANGE_GREETING;
                connection.notifyObservers("Record your greeting, then press the # key");
            }
    }
    @Override
    public void changeState(Connection connection, ConnectionState connectionState) {
        connection.changeState(connectionState);
    }
}