package MailVoice;

/**
   A message left by the caller.
*/
public class Message
{
   private String text;
   private int id;
   private int MailboxId;

   public Message(String messageText)
   {
      text = messageText;
   }
   public Message(String messageText, int id)
   {
      text = messageText;
      this.id=id;
   }
   public Message(String messageText, int id, int MailboxId)
   {
      text = messageText;
      this.id=id;
      this.MailboxId=MailboxId;
   }

   public String getText()
   {
      return text;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }
   public int getMailboxId(){return MailboxId;}
   public void setMailboxId(int id){this.MailboxId=id;}
}
