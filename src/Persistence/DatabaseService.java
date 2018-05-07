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

    public DatabaseService(){
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

    @Override
    public MailSystem getMailSystem(){
        MailSystem mailSystem=null;
        ArrayList<Mailbox> mailboxes=new ArrayList<Mailbox>();

        String query="SELECT * FROM Mailbox";

        try {
            resultSet = statementObj.executeQuery(query);
            while(resultSet.next()) {
                int mailboxId=Integer.parseInt(resultSet.getString("id"));

                Mailbox mailbox= getMailBoxById(mailboxId);
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
                MessageQueue keptMessageQueue=getMessageQueueById(keptMessagesId);
                MessageQueue newMessageQueue=getMessageQueueById(newMessageId);

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

    public MessageQueue getMessageQueueById(int queueId){
        MessageQueue queue=null;
        ArrayList<Message> messages = getAllMessagesByQueueId(queueId);
        queue=new MessageQueue();
        queue.setQueue(messages);
        return queue;
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

    @Override
    public void saveMailSystem(MailSystem mailsystem){
        ArrayList<Mailbox> mailboxes=mailsystem.getMailboxes();
        int initialQueueId=1;
        for (int index=0; index<mailboxes.size(); index++) {

            int mailboxId=index+1;
            Mailbox mailbox=mailboxes.get(index);

            saveMailbox(mailbox, mailboxId, initialQueueId);

            initialQueueId+=2;
        }
    }

    public void saveMailbox(Mailbox mailbox, int id, int initialQueueId){
        int keptMessagesId=initialQueueId,
                newMessagesId=initialQueueId+1;
        MessageQueue keptMessages=mailbox.getKeptMessages(),
                newMessages=mailbox.getNewMessages();
        String passcode=mailbox.getPasscode(),
                greeting=mailbox.getGreeting();
        saveMessageQueue(keptMessages, keptMessagesId);
        saveMessageQueue(newMessages, newMessagesId);

        try {
            statementObj.executeUpdate("INSERT INTO `VoiceMailDB`.`Mailbox` (`id`,`passcode`, `greeting`, `keptMessageQueueId`, `newMessageQueueId`) VALUES('" + id + "'," +
                    "'" + passcode + "', '" + greeting + "', '" + keptMessagesId + "', '" + newMessagesId + "')");
        } catch (Exception e) {
            e.printStackTrace();
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
            saveMessage(message, id);
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

    @Override
    public void updateMailSystem(MailSystem mailSystem){
        ArrayList<Mailbox> mailboxes=mailSystem.getMailboxes();
        int initialQueueId=1;
        for (int index=0; index<mailboxes.size(); index++) {

            int mailboxId=index+1;
            Mailbox mailbox=mailboxes.get(index);

            updateMailbox(mailbox, mailboxId, initialQueueId);

            initialQueueId+=2;
        }
    }

    public void updateMailbox(Mailbox mailbox, int mailboxId, int initialQueueId){
        int keptMessagesId=initialQueueId,
                newMessagesId=initialQueueId+1;
        MessageQueue keptMessages=mailbox.getKeptMessages(),
                newMessages=mailbox.getNewMessages();
        String passcode=mailbox.getPasscode(),
                greeting=mailbox.getGreeting();
        updateMessageQueue(keptMessages, keptMessagesId);
        updateMessageQueue(newMessages, newMessagesId);

        String query="UPDATE `Mailbox` SET `passcode`='"+passcode+"',`greeting`='"+greeting+"' WHERE id = "+mailboxId;

        try {
            statementObj.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateMessageQueue(MessageQueue queue, int id){
        ArrayList<Message> messages=queue.getQueue();
        for (Message message: messages){
            saveMessage(message, id);
        }
    }
}