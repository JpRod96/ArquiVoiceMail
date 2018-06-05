package MailVoice;

public class Mailbox
{
   private MessageQueue newMessages;
   private MessageQueue keptMessages;
   private String greeting;
   private String passcode;
   private int id;

   public Mailbox(String aPasscode, String aGreeting)
   {
      passcode = aPasscode;
      greeting = aGreeting;
      newMessages = new MessageQueue();
      keptMessages = new MessageQueue();
   }

   public Mailbox(String aPasscode, String aGreeting, int id)
   {
      passcode = aPasscode;
      greeting = aGreeting;
      this.id=id;
      newMessages = new MessageQueue();
      keptMessages = new MessageQueue();
   }

   public boolean checkPasscode(String aPasscode)
   {
      return aPasscode.equals(passcode);
   }

   public void addMessage(Message aMessage)
   {
      newMessages.add(aMessage);
   }

   public Message getCurrentMessage()
   {
      if (newMessages.size() > 0)
         return newMessages.peek();
      else if (keptMessages.size() > 0)
         return keptMessages.peek();
      else
         return null;
   }

   public Message removeCurrentMessage()
   {
      Message message;
      if (newMessages.size() > 0){
         message=newMessages.remove();
         return message;
      }
      else if (keptMessages.size() > 0){
         message=keptMessages.remove();
         return message;
      }
      else
         return null;
   }

   public Message saveCurrentMessage()
   {
      Message message = removeCurrentMessage();
      if (message != null){
         keptMessages.add(message);
      }
      return message;
   }

    public void setGreeting(String newGreeting)
   {
      greeting = newGreeting;
   }

   public void setPasscode(String newPasscode)
   {
      passcode = newPasscode;
   }

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
