package main;

import java.util.ArrayList;

/**
   A first-in, first-out collection of messages. This
   implementation is not very efficient. We will consider
   a more efficient implementation in chapter 3.
*/
public class MessageQueue
{
   private ArrayList<Message> queue;

   public MessageQueue()
   {
      queue = new ArrayList<>();
   }


   public Message remove()
   {
      return queue.remove(0);
   }

   public void add(Message newMessage)
   {
      queue.add(newMessage);
   }

   public int size()
   {
      return queue.size();
   }
   
   public Message peek()
   {
      if (queue.size() == 0) return null;
      else return queue.get(0);
   }

   public ArrayList<Message> getQueue() {
      return queue;
   }

   public void setQueue(ArrayList<Message> queue) {
      this.queue = queue;
   }
}
 