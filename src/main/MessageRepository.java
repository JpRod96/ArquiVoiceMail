package main;

import MailVoice.Message;
import MailVoice.MessageQueue;

import java.util.ArrayList;

/**
 * Created by Jp on 07/05/2018.
 */
public interface MessageRepository {
    void saveMessage(Message message);
    ArrayList<Message> getAllMessagesByMailBoxId(int mailBoxId);
    Message getMessageById(int messageId);
    void saveAllMessages(MessageQueue queue, int mailBoxId);
    void deleteMessage(Message message);
}
