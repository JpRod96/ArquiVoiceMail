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
public class MessagePersistenceService {

    private Connection connectionObj;
    private Statement statementObj;
    private ResultSet resultSet;
    private final String dbString = "jdbc:mysql://localhost:3306/VoiceMailDB";
    private final String userName = "root";
    private final String password = "mysql";

    public MessagePersistenceService(){
        load();
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

    public void saveMessage(Message message, int queueId){
        String text=message.getText();

        try {
            statementObj.executeUpdate("INSERT INTO `VoiceMailDB`.`Message` (`text`,`MessageQueueId`) VALUES" + "('" + text + "', '" + queueId + "')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Message> getAllMessagesByQueueId(int queueId){
        ArrayList<Message> messages=new ArrayList<Message>();
        String query="SELECT id FROM Message WHERE MessageQueueId = "+queueId;

        try {
            resultSet = statementObj.executeQuery(query);
            while(resultSet.next()) {
                int id=Integer.parseInt(resultSet.getString("Id"));
                Message message=getMessageById(id);
                messages.add(message);
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        finally {
            try{

            }
            catch(Exception ex) {
                ex.printStackTrace();
            }
        }

        return messages;
    }

    public Message getMessageById(int messageId){
        Message message=null;

        String query="SELECT * FROM Message WHERE Id ="+ messageId;

        try {
            resultSet = statementObj.executeQuery(query);
            String text="";
            while(resultSet.next()) {
                text=resultSet.getString("text");
            }
            message=new Message(text);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        finally {
            try{

            }
            catch(Exception ex) {
                ex.printStackTrace();
            }
        }

        return message;
    }

}
