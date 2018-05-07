package Persistence;

import main.*;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 * Created by Jp on 07/05/2018.
 */
public class MessageQueuePersistenceService {
    private Connection connectionObj;
    private Statement statementObj;
    private ResultSet resultSet;
    private final String dbString = "jdbc:mysql://localhost:3306/VoiceMailDB";
    private final String userName = "root";
    private final String password = "mysql";
    private MessagePersistenceService messagePersistenceService;

    public MessageQueuePersistenceService(){
        load();
        messagePersistenceService=new MessagePersistenceService();
        messagePersistenceService.load();
    }

    public void load(){
        try
        {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connectionObj = DriverManager.getConnection(dbString,userName,password);
            statementObj = connectionObj.createStatement();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void saveMessageQueue(MessageQueue queue, int id){
        try {
            statementObj.executeUpdate("INSERT INTO `VoiceMailDB`.`MessageQueue` (`id`) VALUES" + "('" + id + "')");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<Message> messages=queue.getQueue();
        for (Message message: messages){
           messagePersistenceService.saveMessage(message, id);
        }
    }

    public MessageQueue getMessageQueueById(int queueId){
        MessageQueue queue=null;
        ArrayList<Message> messages = messagePersistenceService.getAllMessagesByQueueId(queueId);
        queue=new MessageQueue();
        queue.setQueue(messages);
        return queue;
    }

    public void updateMessageQueue(MessageQueue queue, int id){
        ArrayList<Message> messages=queue.getQueue();
        for (Message message: messages){
            messagePersistenceService.saveMessage(message, id);
        }
    }



}
