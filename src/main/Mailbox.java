package main;

public class Mailbox
{
   private MessageQueue newMessages;
   private MessageQueue keptMessages;
   private String greeting;
   private String passcode;
   private int id;
   private MessageRepository messageRepository;
   private MailBoxRepository mailBoxRepository;

   public Mailbox(String aPasscode, String aGreeting)
   {
      passcode = aPasscode;
      greeting = aGreeting;
      newMessages = new MessageQueue();
      keptMessages = new MessageQueue();
   }

   public Mailbox(String passcode, String greeting, int id, MessageRepository messageRepository, MailBoxRepository mailBoxRepository)
   {
      this.passcode = passcode;
      this.greeting = greeting;
      newMessages = new MessageQueue();
      keptMessages = new MessageQueue();
      this.messageRepository=messageRepository;
      this.id=id;
      this.mailBoxRepository=mailBoxRepository;
   }

   /**
      Check if the passcode is correct.
      @param aPasscode a passcode to check
      @return true if the supplied passcode matches the mailbox passcode
   */
   public boolean checkPasscode(String aPasscode)
   {
      return aPasscode.equals(passcode);
   }

   /**
      Add a message to the mailbox.
      @param aMessage the message to be added
   */
   public void addMessage(Message aMessage)
   {
      newMessages.add(aMessage);
   }

   /**
      Get the current message.
      @return the current message
   */
   public Message getCurrentMessage()
   {
      if (newMessages.size() > 0)
         return newMessages.peek();
      else if (keptMessages.size() > 0)
         return keptMessages.peek();
      else
         return null;
   }

   /**
      Remove the current message from the mailbox.
      @return the message that has just been removed
   */
   public Message removeCurrentMessage()
   {
      Message message;
      if (newMessages.size() > 0){
         message=newMessages.remove();
         if(messageRepository!=null)
            messageRepository.deleteMessage(message);
         return message;
      }
      else if (keptMessages.size() > 0){
         message=keptMessages.remove();
         if(messageRepository!=null)
            messageRepository.deleteMessage(message);
         return message;
      }
      else
         return null;
   }

   /**
      Save the current message
   */
   public void saveCurrentMessage()
   {
      Message message = removeCurrentMessage();
      if (message != null){
         keptMessages.add(message);
         if(messageRepository!=null)
            messageRepository.saveMessage(message, id);
      }
   }

   /**
      Change mailbox's greeting.
      @param newGreeting the new greeting string
   */
   public void setGreeting(String newGreeting)
   {
      greeting = newGreeting;
      UpdateMailxboxRepository();
   }

   private void UpdateMailxboxRepository() {
      if(mailBoxRepository!=null)
      mailBoxRepository.updateMailbox(this);
   }

   /**
      Change mailbox's passcode.
      @param newPasscode the new passcode
   */
   public void setPasscode(String newPasscode)
   {
      passcode = newPasscode;
      UpdateMailxboxRepository();
   }

   /**
      Get the mailbox's greeting.
      @return the greeting
   */
   public String getGreeting()
   {
      return greeting;
   }

   public String getPasscode() {
      return passcode;
   }

   public MessageQueue getKeptMessages() {
      return keptMessages;
   }

   public void setKeptMessages(MessageQueue keptMessages) {
      this.keptMessages = keptMessages;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }
}
