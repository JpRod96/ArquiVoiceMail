package Persistence;

import main.*;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Jp on 07/05/2018.
 */
public class MailboxPersistenceService implements MailBoxRepository{
    private Connection connectionObj;
    private Statement statementObj;
    private ResultSet resultSet;
    private MessagePersistenceService messagePersistenceService;

    public MailboxPersistenceService(String connectionString){
        load(connectionString);
        messagePersistenceService =new MessagePersistenceService(connectionString);
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

    public void saveMailbox(Mailbox mailbox){
        MessageQueue keptMessages=mailbox.getKeptMessages();
        String passcode=mailbox.getPasscode(),
                greeting=mailbox.getGreeting();
        int id=mailbox.getId();

        try {
            statementObj.executeUpdate("INSERT INTO `VoiceMailDB`.`Mailbox` (`id`,`passcode`, `greeting`) VALUES('" + id + "'," +
                    "'" + passcode + "', '" + greeting + "')");
            messagePersistenceService.saveAllMessages(keptMessages, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateMailbox(Mailbox mailbox){
        String passcode=mailbox.getPasscode(),
                greeting=mailbox.getGreeting();
        int id=mailbox.getId();

        String query="UPDATE `Mailbox` SET `passcode`='"+passcode+"',`greeting`='"+greeting+"' WHERE id = "+id;

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
                greeting=setGreetingRetrievedFromDB(greeting);
                mailbox=new Mailbox(passcode, greeting, mailboxId, messagePersistenceService, this);
                ArrayList<Message> messages= messagePersistenceService.getAllMessagesByMailBoxId(mailboxId);
                MessageQueue keptMessageQueue= new MessageQueue();
                keptMessageQueue.setQueue(messages);

                mailbox.setKeptMessages(keptMessageQueue);
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return mailbox;
    }

    private String setGreetingRetrievedFromDB(String greeting){
        String settedGreeting="";
        StringTokenizer tokenizer=new StringTokenizer(greeting,"\\");
        while(tokenizer.hasMoreTokens())
        {
            settedGreeting+=tokenizer.nextToken()+"\n";
        }
        return settedGreeting;
    }

    public ArrayList<Mailbox> getAllMailBoxes(){
        String query="SELECT * FROM Mailbox";
        ArrayList<Mailbox> mailboxes=new ArrayList<>();
        for (int mailboxId=1; mailboxId<=20;mailboxId++){
            mailboxes.add(getMailBoxById(mailboxId));
        }

     return mailboxes;
    }
    public static List<Mailbox> getAllMailboxes2(){
        String connectionString="jdbc:sqlite:db.db";
        MailBoxRepository mailBoxRepository=new MailboxPersistenceService(connectionString);
        List<Mailbox> mailboxes=mailBoxRepository.getAllMailBoxes();
        List<Mailbox> mailboxes1WithoutRepos=new ArrayList<>();
        for (Mailbox mailbox:
                mailboxes) {
            mailbox.setMessageRepository(null);
            mailbox.setMailBoxRepository(null);
            mailboxes1WithoutRepos.add(mailbox);
        }
        return mailboxes1WithoutRepos;
    }

    public boolean userExist(String params) {
        return true;
    }
}
