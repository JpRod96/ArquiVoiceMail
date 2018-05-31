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
public class MessagePersistenceService implements MessageRepository{

    private Connection connectionObj;
    private Statement statementObj;
    private ResultSet resultSet;

    public MessagePersistenceService(String connectionString){
        load(connectionString);
    }
    public MessagePersistenceService(String connectionString, String user, String password, String driver){
        loadLocalHost(connectionString, user, password, driver);
    }
    public void loadLocalHost(String connectionString, String user, String password, String driver){
        try
        {
            Class.forName(driver);
            connectionObj = DriverManager.getConnection(connectionString, user, password);
            statementObj = connectionObj.createStatement();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void load(String connectionString){
        try
        {
            connectionObj = DriverManager.getConnection(connectionString);
            statementObj = connectionObj.createStatement();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void saveMessage(Message message, int mailBoxId){
        String text=message.getText();
        int id=message.getId();
        if(id==0){
            try {
                statementObj.executeUpdate("INSERT INTO Message (text,MailBoxId) VALUES" + "('" + text + "', '" + mailBoxId + "')");
                message.setId(Integer.MAX_VALUE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Message> getAllMessagesByMailBoxId(int mailBoxId){
        ArrayList<Message> messages=new ArrayList<>();
        int minId= getLimitMessagesIdByStrategy(mailBoxId,"MIN");
        int maxId= getLimitMessagesIdByStrategy(mailBoxId,"MAX");
        for(int index=minId; index<=maxId; index++){
            messages.add(getMessageById(index));
        }
        return messages;
    }

    private int getLimitMessagesIdByStrategy(int mailBoxId, String strategy){
        int id=0;
        String query="SELECT "+strategy+"(id) AS id FROM Message WHERE MailboxId="+mailBoxId;
        try {
            resultSet = statementObj.executeQuery(query);
            while(resultSet.next()) {
                id=Integer.parseInt(resultSet.getString("id"));
            }
        }
        catch(Exception ex) {

        }
        return id;
    }


    public Message getMessageById(int messageId){
        Message message=null;

        String query="SELECT * FROM Message WHERE Id ="+ messageId;

        try {
            resultSet = statementObj.executeQuery(query);
            String text="";
            int id=0;
            while(resultSet.next()) {
                id=Integer.parseInt(resultSet.getString("Id"));
                text=resultSet.getString("text");
            }
            message=new Message(text,id);
        }
        catch(Exception ex) {

        }
        return message;
    }

    public void saveAllMessages(MessageQueue queue, int mailBoxId){
        ArrayList<Message> messages=queue.getQueue();
        for (Message message: messages){
            saveMessage(message, mailBoxId);
        }
    }

    public void deleteMessage(Message message){
        int id=message.getId();
        String query="DELETE FROM Message WHERE Id="+id;
        if(id>0){
            try {
                statementObj.executeUpdate(query);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
