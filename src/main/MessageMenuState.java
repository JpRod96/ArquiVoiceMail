package main;

import MailVoice.Message;

/**
 * Created by CORE i7 on 24/04/2018.
 */
public class MessageMenuState implements ConnectionState{

    @Override
    public void dial (String key, Connection connection){

            if (key.equals("2")) {
                Message message = connection.getCurrentMailbox().saveCurrentMessage();
                int mailboxId=connection.getCurrentMailbox().getId();
                if(message!=null && connection.getMessageRepository()!=null){
                    message.setMailboxId(mailboxId);
                    connection.getMessageRepository().saveMessage(message);
                }
                connection.notifyPresenters();
            } else if (key.equals("3")) {
                Message message=connection.getCurrentMailbox().removeCurrentMessage();
                if (message!=null && connection.getMessageRepository()!=null){
                    connection.getMessageRepository().deleteMessage(message);
                }
                connection.notifyPresenters();
            } else if (key.equals("4")) {
                changeState(connection,new MailBoxMenuState());
                connection.notifyPresenters();
            }

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
