package main;

/**
 * Created by CORE i7 on 24/04/2018.
 */
public class MailBoxMenuState implements ConnectionState {

    @Override
    public void dial (String key, Connection connection){

            if (key.equals("1"))
            {
                changeState(connection,new MessageMenuState());
                connection.notifyPresenters();
            }
            else if (key.equals("2"))
            {
                changeState(connection,new ChangePassCode());
            }
            else if (key.equals("3"))
            {
                changeState(connection, new ChangeGreeting());
            }
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
