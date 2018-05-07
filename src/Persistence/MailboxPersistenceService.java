package Persistence;

import main.*;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 * Created by Jp on 07/05/2018.
 */
public class MailboxPersistenceService {
    private Connection connectionObj;
    private Statement statementObj;
    private ResultSet resultSet;
    private final String dbString = "jdbc:mysql://localhost:3306/VoiceMailDB";
    private final String userName = "root";
    private final String password = "mysql";
    private MessageQueuePersistenceService messageQueuePersistenceService;

    public MailboxPersistenceService(){
        load();
        messageQueuePersistenceService =new MessageQueuePersistenceService();
        messageQueuePersistenceService.load();
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

    public void saveMailbox(Mailbox mailbox, int id, int initialQueueId){
        int keptMessagesId=initialQueueId,
                newMessagesId=initialQueueId+1;
        MessageQueue keptMessages=mailbox.getKeptMessages(),
                newMessages=mailbox.getNewMessages();
        String passcode=mailbox.getPasscode(),
                greeting=mailbox.getGreeting();
        messageQueuePersistenceService.saveMessageQueue(keptMessages, keptMessagesId);
        messageQueuePersistenceService.saveMessageQueue(newMessages, newMessagesId);

        try {
            statementObj.executeUpdate("INSERT INTO `VoiceMailDB`.`Mailbox` (`id`,`passcode`, `greeting`, `keptMessageQueueId`, `newMessageQueueId`) VALUES('" + id + "'," +
                    "'" + passcode + "', '" + greeting + "', '" + keptMessagesId + "', '" + newMessagesId + "')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateMailbox(Mailbox mailbox, int mailboxId, int initialQueueId){
        int keptMessagesId=initialQueueId,
                newMessagesId=initialQueueId+1;
        MessageQueue keptMessages=mailbox.getKeptMessages(),
                newMessages=mailbox.getNewMessages();
        String passcode=mailbox.getPasscode(),
                greeting=mailbox.getGreeting();
        messageQueuePersistenceService.updateMessageQueue(keptMessages, keptMessagesId);
        messageQueuePersistenceService.updateMessageQueue(newMessages, newMessagesId);

        String query="UPDATE `Mailbox` SET `passcode`='"+passcode+"',`greeting`='"+greeting+"' WHERE id = "+mailboxId;

        try {
            statementObj.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Mailbox getMailBoxById(int mailboxId){
        Mailbox mailbox=null;

        String query="SELECT * FROM Mailbox WHERE id = "+mailboxId;

        try {
            resultSet = statementObj.executeQuery(query);

            while(resultSet.next()) {
                int keptMessagesId=Integer.parseInt(resultSet.getString("keptMessageQueueId"));
                int newMessageId=Integer.parseInt(resultSet.getString("newMessageQueueId"));
                String passcode=resultSet.getString("passcode"),
                        greeting=resultSet.getString("greeting");
                mailbox=new Mailbox(passcode,greeting);
                MessageQueue keptMessageQueue=messageQueuePersistenceService.getMessageQueueById(keptMessagesId);
                MessageQueue newMessageQueue=messageQueuePersistenceService.getMessageQueueById(newMessageId);

                mailbox.setKeptMessages(keptMessageQueue);
                mailbox.setNewMessages(newMessageQueue);
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

        return mailbox;
    }

}
