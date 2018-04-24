package main;

/**
 * Created by CORE i7 on 24/04/2018.
 */
public class MessageMenuState implements ConnectionState{
    @Override
    public void dial (String key, Connection connection){

            if (key.equals("1")) {
                String output = "";
                Message m = connection.currentMailbox.getCurrentMessage();
                if (m == null) output += "No messages." + "\n";
                else output += m.getText() + "\n";
                output += connection.MESSAGE_MENU_TEXT;
                connection.notifyObservers(output);
            } else if (key.equals("2")) {
                connection.currentMailbox.saveCurrentMessage();
                connection.notifyObservers(connection.MESSAGE_MENU_TEXT);
            } else if (key.equals("3")) {
                connection.currentMailbox.removeCurrentMessage();
                connection.notifyObservers(connection.MESSAGE_MENU_TEXT);
            } else if (key.equals("4")) {
                changeState(connection,new MailBoxMenuState());
                connection.notifyObservers(connection.MAILBOX_MENU_TEXT);
            }

    }
    @Override
    public void changeState(Connection connection, ConnectionState connectionState) {
        connection.changeState(connectionState);
    }
}
