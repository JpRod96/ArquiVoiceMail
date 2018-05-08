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
public class MailboxPersistenceService implements MailBoxRepository{
    private Connection connectionObj;
    private Statement statementObj;
    private ResultSet resultSet;
    private final String dbString = "jdbc:mysql://localhost:3306/VoiceMailDB";
    private final String userName = "root";
    private final String password = "mysql";
    private MessagePersistenceService messagePersistenceService;

    public MailboxPersistenceService(){
        load();
        messagePersistenceService =new MessagePersistenceService();
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

    public void saveMailbox(Mailbox mailbox, int id){
        MessageQueue keptMessages=mailbox.getKeptMessages();
        String passcode=mailbox.getPasscode(),
                greeting=mailbox.getGreeting();

        try {
            statementObj.executeUpdate("INSERT INTO `VoiceMailDB`.`Mailbox` (`id`,`passcode`, `greeting`) VALUES('" + id + "'," +
                    "'" + passcode + "', '" + greeting + "')");
            messagePersistenceService.saveAllMessages(keptMessages, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateMailbox(Mailbox mailbox, int mailboxId){
        MessageQueue keptMessages=mailbox.getKeptMessages();
        String passcode=mailbox.getPasscode(),
                greeting=mailbox.getGreeting();

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
                String passcode=resultSet.getString("passcode"),
                        greeting=resultSet.getString("greeting");
                mailbox=new Mailbox(passcode,greeting);
                ArrayList<Message> messages= messagePersistenceService.getAllMessagesByMailBoxId(mailboxId);
                MessageQueue keptMessageQueue= new MessageQueue();
                keptMessageQueue.setQueue(messages);

                mailbox.setKeptMessages(keptMessageQueue);
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

    public ArrayList<Mailbox> getAllMailBoxes(){
        String query="SELECT id FROM Mailbox";
        ArrayList<Mailbox> mailboxes=new ArrayList<>();

        try {
            resultSet = statementObj.executeQuery(query);

            while(resultSet.next()) {
                int mailboxId=Integer.parseInt(resultSet.getString("id"));
                mailboxes.add(getMailBoxById(mailboxId));
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


        return mailboxes;
    }
}
