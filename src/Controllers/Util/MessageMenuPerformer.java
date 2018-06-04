package Controllers.Util;

import MailVoice.Mailbox;
import MailVoice.Message;
import Presenters.ConsolePresenter;
import main.Connection;

/**
 * Created by Jp on 03/06/2018.
 */
public class MessageMenuPerformer implements ActionPerformer{
    private Connection connection;
    private ConsolePresenter consolePresenter;
    private final String TEXT_FOR_EMPTY_QUEUE="No messages.\n";

    public MessageMenuPerformer(Connection connection, ConsolePresenter consolePresenter){
        this.connection=connection;
        this.consolePresenter=consolePresenter;
    }

    public void performAction(ConnectionStateLog log, String key){
        if(key.equals("1")) {
            String output = "";
            Mailbox currentMailbox=connection.getCurrentMailbox();
            Message message = currentMailbox.getCurrentMessage();
            if (message == null || message.getText().equals(""))
                output += TEXT_FOR_EMPTY_QUEUE;
            else
                output += message.getText() + "\n";

            consolePresenter.parseModel(output);
            consolePresenter.parseModel();
        }
    }
}
