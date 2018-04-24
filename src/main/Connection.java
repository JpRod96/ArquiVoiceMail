package main;
import java.util.ArrayList;

import observers.*;

//organizar
/**
   Connects a phone to the mail system. The purpose of this
   class is to keep track of the state of a connection, sinc
 
   the phone itself is just a source of individual key presses.
*/
public class Connection
{
    private ConnectionState _state;
	public MailSystem system;
	public Mailbox currentMailbox;
	private String currentRecording;
	public String accumulatedKeys;
	public int state;
	private ArrayList<StateWatcher> stateWatchers;

	private static final int DISCONNECTED = 0;
	private static final int CONNECTED = 1;
	public static final int RECORDING = 2;
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

   public Connection(MailSystem s)
   {
      system = s;
      stateWatchers= new ArrayList<>();
      _state = new Connected();
   }
   private void changeState(ConnectionState c){
       _state=c;
   }

   /**
      Respond to the user's pressing a key on the phone touchpad
      @param key the phone key pressed by the user
   */
   public void dial(String key)
   {
      if (isConnected())
         //connect(key);
         _state.dial(key,this);
      else if (isRecording())
         login(key);
      else if (isChangePassCode())
         changePasscode(key);
      else if (isChangeGreeting())
         changeGreeting(key);
      else if (isMailBoxMenu())
         mailboxMenu(key);
      else if (isMessageMenu())
         messageMenu(key);

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
      notifyObservers(INITIAL_PROMPT);
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
            notifyObservers(currentMailbox.getGreeting());
         }
         else {
             notifyObservers("Incorrect mailbox number. Try again!");
         }
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
            notifyObservers(MAILBOX_MENU_TEXT);
         }
         else {
             notifyObservers("Incorrect passcode. Try again!");
         }
         	
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
         notifyObservers(MAILBOX_MENU_TEXT);
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
         notifyObservers(MAILBOX_MENU_TEXT);
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
         notifyObservers(MESSAGE_MENU_TEXT);
      }
      else if (key.equals("2"))
      {
         state = CHANGE_PASSCODE;
         notifyObservers("Enter new passcode followed by the # key");
      }
      else if (key.equals("3"))
      {
         state = CHANGE_GREETING;
         notifyObservers("Record your greeting, then press the # key");
      }
   }

   /**
      Respond to the user's selection from message menu.
      @param key the phone key pressed by the user
   */
   private void messageMenu(String key)
   {
      if (key.equals("1"))
      {
         String output = "";
         Message m = currentMailbox.getCurrentMessage();
         if (m == null) output += "No messages." + "\n";
         else output += m.getText() + "\n";
         output += MESSAGE_MENU_TEXT;
         notifyObservers(output);
      }
      else if (key.equals("2"))
      {
         currentMailbox.saveCurrentMessage();
         notifyObservers(MESSAGE_MENU_TEXT);
      }
      else if (key.equals("3"))
      {
         currentMailbox.removeCurrentMessage();
         notifyObservers(MESSAGE_MENU_TEXT);
      }
      else if (key.equals("4"))
      {
         state = MAILBOX_MENU;
         notifyObservers(MAILBOX_MENU_TEXT);
      }
   }
   
   public void addObserver(StateWatcher stateWatcher) {
	   stateWatchers.add(stateWatcher);
	   resetConnection();
   }
   
   public void notifyObservers(String updateString) {
	   for(StateWatcher stateWatcher : stateWatchers) {
		   stateWatcher.update(updateString);
	   }
   }
   public void recibeData(String key){
      this.dial(key);
   }
   
}











