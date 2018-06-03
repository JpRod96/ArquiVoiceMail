package main;

/**
 * Created by Jp on 24/04/2018.
 */
public class ChangeGreeting implements ConnectionState{
    Connection connection;

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
            connection.notifyPresenters();
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

