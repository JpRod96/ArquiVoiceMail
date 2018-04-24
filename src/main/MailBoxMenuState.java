package main;

/**
 * Created by CORE i7 on 24/04/2018.
 */
public class MailBoxMenuState implements ConnectionState {
    @Override
    public void dial (String key, Connection connection){

            if (key.equals("1"))
            {
               // state = MESSAGE_MENU;
                connection.notifyObservers(connection.MESSAGE_MENU_TEXT);
            }
            else if (key.equals("2"))
            {
                connection.state=connection.CHANGE_PASSCODE;
                changeState(connection,null);
                connection.notifyObservers("Enter new passcode followed by the # key");
            }
            else if (key.equals("3"))
            {
                state = CHANGE_GREETING;
                notifyObservers("Record your greeting, then press the # key");
            }
    }
}
