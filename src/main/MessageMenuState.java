package main;

import MailVoice.Message;

/**
 * Created by CORE i7 on 24/04/2018.
 */
public class MessageMenuState implements ConnectionState{

    public static final String MAILBOX_MENU_TEXT =
            "Enter 1 to listen to your messages\n"
                    + "Enter 2 to change your passcode\n"
                    + "Enter 3 to change your greeting";
    public static final String MESSAGE_MENU_TEXT =
            "Enter 1 to listen to the current message\n"
                    + "Enter 2 to save the current message\n"
                    + "Enter 3 to delete the current message\n"
                    + "Enter 4 to return to the main menu";

    @Override
    public void dial (String key, Connection connection){

            if (key.equals("1")) {
                String output = "";
                Message m = connection.getCurrentMailbox().getCurrentMessage();
                if (m == null) output += "No messages." + "\n";
                else output += m.getText() + "\n";
                output += MESSAGE_MENU_TEXT;
                connection.notifyPresenters(output);
            } else if (key.equals("2")) {
                Message message= connection.getCurrentMailbox().saveCurrentMessage();
                int mailboxId=connection.getCurrentMailbox().getId();
                message.setMailboxId(mailboxId);
                if(message!=null && connection.getMessageRepository()!=null){
                    connection.getMessageRepository().saveMessage(message);
                }
                connection.notifyPresenters(MESSAGE_MENU_TEXT);
            } else if (key.equals("3")) {
                Message message=connection.getCurrentMailbox().removeCurrentMessage();
                if (message!=null && connection.getMessageRepository()!=null){
                    connection.getMessageRepository().deleteMessage(message);
                }
                connection.notifyPresenters(MESSAGE_MENU_TEXT);
            } else if (key.equals("4")) {
                changeState(connection,new MailBoxMenuState());
                connection.notifyPresenters(MAILBOX_MENU_TEXT);
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
