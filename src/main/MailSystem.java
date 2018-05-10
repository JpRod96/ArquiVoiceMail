package main;

import java.util.ArrayList;

/**
   A system of voice mail boxes.
*/
public class MailSystem
{
   private ArrayList<Mailbox> mailboxes;
   private MailBoxRepository mailBoxRepository;
   public MailSystem(MailBoxRepository mailBoxRepository){
      this.mailBoxRepository=mailBoxRepository;
   }
   /**
      Constructs a mail system with a given number of mailboxes
      @param mailboxCount the number of mailboxes
   */
   public MailSystem(int mailboxCount)
   {
      mailboxes = new ArrayList<Mailbox>();

      // Initialize mail boxes.

      for (int i = 0; i < mailboxCount; i++)
      {
         String passcode = "" + (i + 1);
         String greeting = "You have reached mailbox " + (i + 1)
               + ". \nPlease leave a message now.";
         mailboxes.add(new Mailbox(passcode, greeting));
      }
   }

   public MailSystem(ArrayList<Mailbox> mailboxes){
      this.mailboxes=mailboxes;
   }

   /**
      Locate a mailbox.
      @param ext the extension number
      @return the mailbox or null if not found
   */
   public Mailbox findMailbox(String ext)
   {
      int i = Integer.parseInt(ext);
      if(mailBoxRepository!=null){
         return mailBoxRepository.getMailBoxById(i);
      }
      else
      {
         if (1 <= i && i <= mailboxes.size())
            return  mailboxes.get(i - 1);
         else return null;
      }
   }

   public ArrayList<Mailbox> getMailboxes() {
      return mailboxes;
   }

   public void setMailboxes(ArrayList<Mailbox> mailboxes) {
      this.mailboxes = mailboxes;
   }
}
