package main;

/**
 * Created by Jp on 24/04/2018.
 */
public class ChangePassCode implements ConnectionState{

    @Override
    public void dial(String key, Connection connection) {
        if (key.equals("#"))
        {
            connection.getCurrentMailbox().setPasscode(connection.getAccumulatedKeys());
            if(connection.getMailBoxRepository()!=null)
                connection.getMailBoxRepository().updateMailbox(connection.getCurrentMailbox());
            changeState(connection,new MailBoxMenuState());
            connection.notifyPresenters();
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
