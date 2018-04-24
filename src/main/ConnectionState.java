package main;

public interface ConnectionState {
    public void dial();
    public void changeState (Connection c );
}
