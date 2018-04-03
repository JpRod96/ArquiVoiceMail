package main;

/**
   Connects a phone to the mail system. The purpose of this
   class is to keep track of the state of a connection, since
   the phone itself is just a source of individual key presses.
*/
public class Connection
{
	private MailSystem system;
	private Mailbox currentMailbox;
	private String currentRecording;
	private String accumulatedKeys;
	private Telephone phone;
	private int state;

	private static final int CONNECTED = 1;
	private static final int RECORDING = 2;
	private static final int MAILBOX_MENU = 3;
	private static final int MESSAGE_MENU = 4;
	private static final int CHANGE_PASSCODE = 5;
	private static final int CHANGE_GREETING = 6;

	private static final String INITIAL_PROMPT = 
	      "Enter mailbox number followed by #";      
	private static final String MAILBOX_MENU_TEXT = 
	      "Enter 1 to listen to your messages\n"
	      + "Enter 2 to change your passcode\n"
	      + "Enter 3 to change your greeting";
	private static final String MESSAGE_MENU_TEXT = 
	      "Enter 1 to listen to the current message\n"
	      + "Enter 2 to save the current message\n"
	      + "Enter 3 to delete the current message\n"
	      + "Enter 4 to return to the main menu";
   /**
      Construct a Connection object.
      @param s a MailSystem object
      @param p a Telephone object
   */
   public Connection(MailSystem s, Telephone p)
   {
      system = s;
      phone = p;
      resetConnection();
   }

   /**
      Respond to the user's pressing a key on the phone touchpad
      @param key the phone key pressed by the user
   */
   public void dial(String key)
   {
      if (isConnected())
         connect(key);
      else if (isRecording())
         login(key);
      else if (isChangePassCode())
         changePasscode(key);
      else if (isChangeGreeting())
         changeGreeting(key);
      else if (isMailBoxMenu())
         mailboxMenu(key);
      else if (isMessageMenu())
         getMessageMenuStrings(key);
   }
   
   public boolean isConnected() {
	   return state==CONNECTED;
   }
   
   public boolean isRecording() {
	   return state==RECORDING;
   }

   public boolean isChangePassCode() {
	   return state == CHANGE_PASSCODE;
   }
   
   public boolean isChangeGreeting() {
	   return state == CHANGE_GREETING;
   }
   
   public boolean isMailBoxMenu() {
	   return state == MAILBOX_MENU;
   }
   
   public boolean isMessageMenu() {
	   return state == MESSAGE_MENU;
   }
   
   /**
      Record voice.
      @param voice voice spoken by the user
   */
   public void record(String voice)
   {
      if (state == RECORDING || state == CHANGE_GREETING)
         currentRecording += voice;
   }

   /**
      The user hangs up the phone.
   */
   public void hangup()
   {
      if (state == RECORDING)
         currentMailbox.addMessage(new Message(currentRecording));
      resetConnection();
   }

   /**
      Reset the connection to the initial state and prompt
      for mailbox number
   */
   private void resetConnection()
   {
      currentRecording = "";
      accumulatedKeys = "";
      state = CONNECTED;
      phone.speak(INITIAL_PROMPT);
   }

   /**
      Try to connect the user with the specified mailbox.
      @param key the phone key pressed by the user
   */
   private void connect(String key)
   {
      if (key.equals("#"))
      {
         currentMailbox = system.findMailbox(accumulatedKeys);
         if (currentMailbox != null)
         {
            state = RECORDING;
            phone.speak(currentMailbox.getGreeting());
         }
         else
            phone.speak("Incorrect mailbox number. Try again!");
         accumulatedKeys = "";
      }
      else
         accumulatedKeys += key;
   }

   /**
      Try to log in the user.
      @param key the phone key pressed by the user
   */
   private void login(String key)
   {
      if (key.equals("#"))
      {
         if (currentMailbox.checkPasscode(accumulatedKeys))
         {
            state = MAILBOX_MENU;
            phone.speak(MAILBOX_MENU_TEXT);
         }
         else
            phone.speak("Incorrect passcode. Try again!");
         accumulatedKeys = "";
      }
      else
         accumulatedKeys += key;
   }

   /**
      Change passcode.
      @param key the phone key pressed by the user
   */
   private void changePasscode(String key)
   {
      if (key.equals("#"))
      {
         currentMailbox.setPasscode(accumulatedKeys);
         state = MAILBOX_MENU;
         phone.speak(MAILBOX_MENU_TEXT);
         accumulatedKeys = "";
      }
      else
         accumulatedKeys += key;
   }

   /**
      Change greeting.
      @param key the phone key pressed by the user
   */
   private void changeGreeting(String key)
   {
      if (key.equals("#"))
      {
         currentMailbox.setGreeting(currentRecording);
         currentRecording = "";
         state = MAILBOX_MENU;
         phone.speak(MAILBOX_MENU_TEXT);
      }
   }

   /**
      Respond to the user's selection from mailbox menu.
      @param key the phone key pressed by the user
   */
   private void mailboxMenu(String key)
   {
      if (key.equals("1"))
      {
         state = MESSAGE_MENU;
         phone.speak(MESSAGE_MENU_TEXT);
      }
      else if (key.equals("2"))
      {
         state = CHANGE_PASSCODE;
         phone.speak("Enter new passcode followed by the # key");
      }
      else if (key.equals("3"))
      {
         state = CHANGE_GREETING;
         phone.speak("Record your greeting, then press the # key");
      }
   }

   /**
      Respond to the user's selection from message menu.
      @param keyPressed the phone key pressed by the user
   */
   private void getMessageMenuStrings(String keyPressed)
   {
	   final String LISTEN_MESSAGE_OPTION="1",
			     SAVE_MESSAGE_OPTION="2",
			     DELETE_MESSAGE_OPTION="3",
			     RETURN_TO_MAIN_MENU_OPTION="4";
      if (keyPressed.equals(LISTEN_MESSAGE_OPTION))
      {
         String output = "";
         Message message = currentMailbox.getCurrentMessage();
         if (message == null) 
        	 output += "No messages." + "\n";
         else 
        	 output += message.getText() + "\n";
         
         output += MESSAGE_MENU_TEXT;
         phone.speak(output);
      }
      else if (keyPressed.equals(SAVE_MESSAGE_OPTION))
      {
         currentMailbox.saveCurrentMessage();
         phone.speak(MESSAGE_MENU_TEXT);
      }
      else if (keyPressed.equals(DELETE_MESSAGE_OPTION))
      {
         currentMailbox.removeCurrentMessage();
         phone.speak(MESSAGE_MENU_TEXT);
      }
      else if (keyPressed.equals(RETURN_TO_MAIN_MENU_OPTION))
      {
         state = MAILBOX_MENU;
         phone.speak(MAILBOX_MENU_TEXT);
      }
   }
}











