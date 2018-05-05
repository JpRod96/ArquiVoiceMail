package main;

public class Connected implements ConnectionState {


    @Override
    public void dial(String key, Connection connection) {
        if (key.equals("#"))
        {
            connection.setCurrentMailbox(connection.getSystem().findMailbox(connection.getAccumulatedKeys()));
            if (connection.getCurrentMailbox() != null)
            {
                //connection.state = Connection.RECORDING;
                changeState(connection,new Recording());
                connection.notifyObservers(connection.getCurrentMailbox().getGreeting());
            }
            else {
                connection.notifyObservers("Incorrect mailbox number. Try again!");
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

    }

    @Override
    public void hangUp(Connection connection) {

    }

}
