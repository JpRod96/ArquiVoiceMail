package main;

import MailVoice.Message;

public class Recording implements ConnectionState {
    Connection connection;

    @Override
    public void dial(String key, Connection connection) {
        this.connection = connection;
        if (key.equals("#"))
        {
            if (connection.getCurrentMailbox().checkPasscode(connection.getAccumulatedKeys()))
            {
                changeState(connection,new MailBoxMenuState());
                connection.notifyPresenters();
            }
            else {
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
    public void record(Connection connection, String voice) {
        connection.setCurrentRecording(voice);
    }

    @Override
    public void hangUp(Connection connection) {
        this.connection = connection;
        connection.getCurrentMailbox().addMessage(new Message(connection.getCurrentRecording()));
    }
}
