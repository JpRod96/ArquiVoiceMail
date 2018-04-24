package main;
import java.util.ArrayList;

import observers.*;

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
	public String currentRecording;
	public String accumulatedKeys;
	public int state;
	private ArrayList<StateWatcher> stateWatchers;

	/*private static final int DISCONNECTED = 0;
	private static final int CONNECTED = 1;
	public static final int RECORDING = 2;
	public static final int MAILBOX_MENU = 3;
	public static final int MESSAGE_MENU = 4;
	public static final int CHANGE_PASSCODE = 5;
	private static final int CHANGE_GREETING = 6;*/

	private static final String INITIAL_PROMPT = 
	      "Enter mailbox number followed by #";      
	public static final String MAILBOX_MENU_TEXT =
	      "Enter 1 to listen to your messages\n"
	      + "Enter 2 to change your passcode\n"
	      + "Enter 3 to change your greeting";
	public static final String MESSAGE_MENU_TEXT =
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
   public void changeState(ConnectionState c){
       _state=c;
   }

   /**
      Respond to the user's pressing a key on the phone touchpad
      @param key the phone key pressed by the user
   */
   public void dial(String key)
   {
      if (isConnected())
         _state.dial(key,this);
      else if (isRecording())
         _state.dial(key, this);
      else if (isChangePassCode())
         _state.dial(key,this);
      else if (isChangeGreeting())
         _state.dial(key,this);
      else if (isMailBoxMenu())
          _state.dial(key,this);
      else if (isMessageMenu())
         _state.dial(key,this);

   }
   
   public boolean isConnected() {
	   //return state==CONNECTED;
       return _state instanceof Connected;
   }
   
   public boolean isRecording() {
	   //return state==RECORDING;
	    return _state instanceof  Recording;
   }

   public boolean isChangePassCode() {
	   //return state == CHANGE_PASSCODE;
      return _state instanceof  ChangePassCode;
   }
   
   public boolean isChangeGreeting() {
	   //return state == CHANGE_GREETING;
      return _state instanceof  ChangeGreeting;
   }
   
   public boolean isMailBoxMenu() {
	   return _state instanceof MailBoxMenuState;
   }
   
   public boolean isMessageMenu() {
      return _state instanceof MessageMenuState;
   }
   
   /**
      Record voice.
      @param voice voice spoken by the user
   */
   public void record(String voice)
   {
      if (_state instanceof Recording || _state instanceof ChangeGreeting)
         currentRecording += voice;
   }

   /**
      The user hangs up the phone.
   */
   public void hangup()
   {
      if (_state instanceof Recording)
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
      //state = CONNECTED;
       changeState(new Connected());
      notifyObservers(INITIAL_PROMPT);
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











