package main;

/**
   A message left by the caller.
*/
public class Message
{
   private String text;
   private int id;

   public Message(String messageText)
   {
      text = messageText;
   }
   public Message(String messageText, int id)
   {
      text = messageText;
      this.id=id;
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
}
