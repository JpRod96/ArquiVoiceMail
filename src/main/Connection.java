package main;
import java.util.ArrayList;

import MailVoice.Mailbox;

public class Connection
{
    private ConnectionState _state;
	private MailSystem system;
	private Mailbox currentMailbox;
	private String currentRecording;
	private String accumulatedKeys="";
	private ArrayList<Presenter> presenters;
	private MailBoxRepository mailBoxRepository;
	private MessageRepository messageRepository;

    public Connection(MailSystem s)
   {
      system = s;
      presenters = new ArrayList<>();
      _state = new InitialState();
   }

    public Connection(MailSystem s, MailBoxRepository mailBoxRepository, MessageRepository messageRepository)
    {
        system = s;
        presenters = new ArrayList<>();
        _state = new InitialState();
        this.mailBoxRepository=mailBoxRepository;
        this.messageRepository=messageRepository;
    }

   public void changeState(ConnectionState c){
       _state=c;
   }

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

   public void record(String voice)
   {
      _state.record(this, voice);
   }

   public void hangup()
   {
      _state.hangUp(this);
      resetConnection();
   }

   private void resetConnection()
   {
      currentRecording = "";
      accumulatedKeys = "";
      changeState(new Connected());
      notifyPresenters();
   }
   
   public void addPresenter(Presenter presenter) {
	   presenters.add(presenter);
       notifyPresenters();
   }
   
   public void notifyPresenters() {
	   for(Presenter presenter : presenters) {
		   presenter.parseModel();
	   }
   }
   public void reciveData(String key){
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

    public ArrayList<Presenter> getPresenters() {
        return presenters;
    }

    public void setPresenters(ArrayList<Presenter> presenters) {
        this.presenters = presenters;
    }

    public MailBoxRepository getMailBoxRepository() {
        return mailBoxRepository;
    }

    public void setMailBoxRepository(MailBoxRepository mailBoxRepository) {
        this.mailBoxRepository = mailBoxRepository;
    }

    public MessageRepository getMessageRepository() {
        return messageRepository;
    }

    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }
}











