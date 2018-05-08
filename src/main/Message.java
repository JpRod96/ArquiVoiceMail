package main;

/**
   A message left by the caller.
*/
public class Message
{
   private String text;
   private int id;
   /**
      Construct a Message object.
      @param messageText the message text
   */
   public Message(String messageText)
   {
      text = messageText;
   }
   public Message(String messageText, int id)
   {
      text = messageText;
      this.id=id;
   }

   /**
      Get the message text.
      @return message text
   */
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
}
