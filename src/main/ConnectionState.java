package main;

public interface ConnectionState {
    public void dial(String key, Connection connection);
    public void changeState (Connection connection, ConnectionState connectionState );
    public void record(Connection connection, String voice);
    public void hangUp(Connection connection);

    /**
       Try to log in the user.
     * @param key the phone key pressed by the user
     * @param connection
     */

}
