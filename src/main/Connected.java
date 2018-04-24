package main;

public class Connected implements ConnectionState {
    @Override
    public void dial(String key, Connection connection) {
        if (key.equals("#"))
        {
            connection.currentMailbox = connection.system.findMailbox(connection.accumulatedKeys);
            if (connection.currentMailbox != null)
            {
                connection.state = Connection.RECORDING;
                connection.notifyObservers(connection.currentMailbox.getGreeting());
            }
            else {
                connection.notifyObservers("Incorrect mailbox number. Try again!");
            }
            connection.accumulatedKeys = "";
        }
        else
            connection.accumulatedKeys += key;
    }

    @Override
    public void changeState(Connection c) {

    }
}
