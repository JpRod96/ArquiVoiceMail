package main;

public interface ConnectionState {
    public void dial(String key, Connection connection);
    public void changeState (Connection c );
}
