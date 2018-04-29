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
	private MailSystem system;
	private Mailbox currentMailbox;
	private String currentRecording;
	private String accumulatedKeys;
	private ArrayList<StateWatcher> stateWatchers;

    private static final String INITIAL_PROMPT =
            "Enter mailbox number followed by #";

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
      _state.dial(key,this);
   }
   
   public boolean isConnected() {
       return _state instanceof Connected;
   }
   
   public boolean isRecording() {
	    return _state instanceof  Recording;
   }

   public boolean isChangePassCode() {
      return _state instanceof  ChangePassCode;
   }
   
   public boolean isChangeGreeting() {
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

    public ConnectionState get_state() {
        return _state;
    }

    public void set_state(ConnectionState _state) {
        this._state = _state;
    }

    public MailSystem getSystem() {
        return system;
    }

    public void setSystem(MailSystem system) {
        this.system = system;
    }

    public Mailbox getCurrentMailbox() {
        return currentMailbox;
    }

    public void setCurrentMailbox(Mailbox currentMailbox) {
        this.currentMailbox = currentMailbox;
    }

    public String getCurrentRecording() {
        return currentRecording;
    }

    public void setCurrentRecording(String currentRecording) {
        this.currentRecording = currentRecording;
    }

    public String getAccumulatedKeys() {
        return accumulatedKeys;
    }

    public void setAccumulatedKeys(String accumulatedKeys) {
        this.accumulatedKeys = accumulatedKeys;
    }

    public ArrayList<StateWatcher> getStateWatchers() {
        return stateWatchers;
    }

    public void setStateWatchers(ArrayList<StateWatcher> stateWatchers) {
        this.stateWatchers = stateWatchers;
    }
}











