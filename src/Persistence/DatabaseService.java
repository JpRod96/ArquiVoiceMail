package Persistence;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import main.*;

public class DatabaseService implements PersistenceRepository{
    private Connection connectionObj;
    private Statement statementObj;
    private ResultSet resultSet;
    private final String dbString = "jdbc:mysql://localhost:3306/VoiceMailDB";
    private final String userName = "root";
    private final String password = "mysql";
    private MailboxPersistenceService mailboxPersistenceService;

    public DatabaseService(){
        load();
        mailboxPersistenceService=new MailboxPersistenceService();
        mailboxPersistenceService.load();
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

    @Override
    public MailSystem getMailSystem(){
        MailSystem mailSystem=null;
        ArrayList<Mailbox> mailboxes=new ArrayList<Mailbox>();

        String query="SELECT * FROM Mailbox";

        try {
            resultSet = statementObj.executeQuery(query);
            while(resultSet.next()) {
                int mailboxId=Integer.parseInt(resultSet.getString("id"));

                Mailbox mailbox= mailboxPersistenceService.getMailBoxById(mailboxId);
                mailboxes.add(mailbox);
            }
            mailSystem=new MailSystem(mailboxes);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        finally {
            try{
                resultSet.close();
            }
            catch(Exception ex) {
                ex.printStackTrace();
            }
        }

        return mailSystem;
    }

    @Override
    public void saveMailSystem(MailSystem mailsystem){
        ArrayList<Mailbox> mailboxes=mailsystem.getMailboxes();
        int initialQueueId=1;
        for (int index=0; index<mailboxes.size(); index++) {

            int mailboxId=index+1;
            Mailbox mailbox=mailboxes.get(index);

            mailboxPersistenceService.saveMailbox(mailbox, mailboxId, initialQueueId);

            initialQueueId+=2;
        }
    }

    @Override
    public void updateMailSystem(MailSystem mailSystem){
        ArrayList<Mailbox> mailboxes=mailSystem.getMailboxes();
        int initialQueueId=1;
        for (int index=0; index<mailboxes.size(); index++) {

            int mailboxId=index+1;
            Mailbox mailbox=mailboxes.get(index);

            mailboxPersistenceService.updateMailbox(mailbox, mailboxId, initialQueueId);

            initialQueueId+=2;
        }
    }
}