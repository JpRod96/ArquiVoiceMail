package main;

/**
 * Created by Jp on 03/06/2018.
 */
public class InitialState implements ConnectionState{
    private final String USE_IN_MEMORY_STORAGE="1";
    private final String USE_IN_PERSISTENCE="2";

    @Override
    public void dial(String key, Connection connection) {
        if (key.equals(USE_IN_MEMORY_STORAGE))
        {
            MailSystem mailSystem=connection.getSystem();
            mailSystem.setMailBoxRepository(null);
            connection.setSystem(mailSystem);
            connection.setMailBoxRepository(null);
            connection.setMessageRepository(null);

            changeState(connection,new Connected());
        }
        else if(key.equals(USE_IN_PERSISTENCE)){
            changeState(connection,new Connected());
        }
        connection.notifyPresenters();
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
