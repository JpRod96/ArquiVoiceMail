package main;

public interface ConnectionState {
    public void dial(String key, Connection connection);
    public void changeState (Connection connection, ConnectionState connectionState );

    /**
       Try to log in the user.
     * @param key the phone key pressed by the user
     * @param connection
     */

}
