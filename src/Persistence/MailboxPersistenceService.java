package Persistence;

import MailVoice.Mailbox;
import MailVoice.Message;
import MailVoice.MessageQueue;
import main.*;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
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
        messagePersistenceService = new MessagePersistenceService(connectionString);
    }

    public MailboxPersistenceService(String connectionString, String user, String password, String driver){
        loadLocalHost(connectionString, user, password, driver);
        messagePersistenceService = new MessagePersistenceService(connectionString, user, password, driver);
    }

    public void loadLocalHost(String connectionString, String user, String password, String driver){
        try
        {
            Class.forName(driver);
            connectionObj = DriverManager.getConnection(connectionString, user, password);
            statementObj = connectionObj.createStatement();
            Statement creationStatement = connectionObj.createStatement();
            creationStatement.execute("CREATE TABLE IF NOT EXISTS Mailbox ( id SERIAL PRIMARY  KEY, passcode CHAR(20) NOT NULL, greeting CHAR(100) NOT NULL);");
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
    public void saveMailbox2(Mailbox mailbox){
        int id=mailbox.getId();
        String passcode=mailbox.getPasscode();
        String greeting=mailbox.getGreeting();


        try {
            statementObj.executeUpdate("INSERT INTO Mailbox (id, passcode, greeting) VALUES" + "('" + id + "' , '" + passcode + "', '" + greeting + "')");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void saveMailbox(Mailbox mailbox){
        MessageQueue keptMessages=mailbox.getKeptMessages();
        String passcode=mailbox.getPasscode(),
                greeting=mailbox.getGreeting();
        int id=mailbox.getId();

        try {
            statementObj.executeUpdate("INSERT INTO Mailbox (`id`,`passcode`, `greeting`) VALUES('" + id + "'," +
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

        String query="UPDATE Mailbox SET `passcode`='"+passcode+"',`greeting`='"+greeting+"' WHERE id = "+id;

        try {
            statementObj.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Mailbox getMailBoxById(int mailboxId){
        Mailbox mailbox1=null;

        String query="SELECT * FROM mailbox WHERE id = "+mailboxId;

        try {
            resultSet = statementObj.executeQuery(query);

            while(resultSet.next()) {
                String passcode=resultSet.getString("passcode"),
                        greeting=resultSet.getString("greeting");
                passcode=verifyPasscode(passcode);
                greeting=setGreetingRetrievedFromDB(greeting);
                mailbox1=new Mailbox(passcode, greeting, mailboxId);
                ArrayList<Message> messages= messagePersistenceService.getAllMessagesByMailBoxId(mailboxId);
                MessageQueue keptMessageQueue= new MessageQueue();
                keptMessageQueue.setQueue(messages);

                mailbox1.setKeptMessages(keptMessageQueue);
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return mailbox1;
    }

    public String verifyPasscode(String passcode){
        String finalPasscode="";
        for (int index=0; index<passcode.length(); index++){
            char charAt=passcode.charAt(index);
            if(charAt!=' ')
                finalPasscode+=charAt;
        }
        return finalPasscode;
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
        ArrayList<Mailbox> mailboxes=new ArrayList<>();
        for (int mailboxId=1; mailboxId<=20;mailboxId++){
            mailboxes.add(getMailBoxById(mailboxId));
        }
        return mailboxes;
    }
    public MessagePersistenceService getMessagePersistenceService(){return messagePersistenceService;}
}