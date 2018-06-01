package test;
import Views.ConsoleViews.Console;
import main.Connection;
import main.MailSystem;
import MailVoice.Mailbox;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class ConnectionTest {

	MailSystem mockedMailsystem;
    Console mockedConsole;
    Connection connection;
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

    @Before
	public void init() {
	    mockedMailsystem = mock(MailSystem.class);
	    mockedConsole = mock(Console.class);
	    connection = new Connection(mockedMailsystem);
	    connection.addPresenter(mockedConsole);
	}

	@Test
	public void shouldBeInConnectedStatus() {
	    assertTrue(connection.isConnected());
	}

	@Test
	public void shouldShowInitialMessage() {
		verify(mockedConsole).parseModel(INITIAL_PROMPT);
	}
	
	@Test
	public void shouldChooseValidMailBox() {
		String idMailBox = "1";
		Mailbox chosenMailbox = new Mailbox(idMailBox, "Hola, como estas?");

		when(mockedMailsystem.findMailbox(idMailBox)).thenReturn(chosenMailbox);
		stepsForGettingIntoMailBox(idMailBox);

		assertTrue(connection.isRecording());
		verify(mockedConsole).parseModel(chosenMailbox.getGreeting());
	}
	@Test
	public void shouldChangeGretting(){

		String idMailBox = "1";
		String keyMailBox = "1";
		Mailbox chosenMailbox = new Mailbox(idMailBox, "Hola, como estas?");

		when(mockedMailsystem.findMailbox(idMailBox)).thenReturn(chosenMailbox);
		stepsForGettingIntoMailBoxMenu(idMailBox, keyMailBox);
		connection.dial("3");
		connection.dial("nuevo saludo");
		connection.dial("#");
		verify(mockedConsole,times(2)).parseModel(MAILBOX_MENU_TEXT);

	}
	@Test
	public void shouldHangUp(){

		String idMailBox = "1";
		String keyMailBox = "1";
		Mailbox chosenMailbox = new Mailbox(idMailBox, "Hola, como estas?");

		when(mockedMailsystem.findMailbox(idMailBox)).thenReturn(chosenMailbox);
		stepsForGettingIntoMailBox(idMailBox);
		connection.hangup();
		verify(mockedConsole,times(2)).parseModel("Enter mailbox number followed by #");

	}
	@Test
	public void shouldShowIncorrectPass(){
		String idMailBox = "1";
		Mailbox chosenMailbox = new Mailbox(idMailBox, "Hola, como estas?");

		when(mockedMailsystem.findMailbox(idMailBox)).thenReturn(chosenMailbox);
		stepsForGettingIntoMailBox(idMailBox);
		connection.dial("#");
		verify(mockedConsole).parseModel("Incorrect passcode. Try again!");
	}
	@Test
	public void shouldGetIntoMailBoxMenu() {
		String idMailBox = "1";
		String keyMailBox = "1";
		Mailbox chosenMailbox = new Mailbox(idMailBox, "Hola, como estas?");

		when(mockedMailsystem.findMailbox(idMailBox)).thenReturn(chosenMailbox);
		stepsForGettingIntoMailBoxMenu(idMailBox, keyMailBox);

		assertTrue(connection.isMailBoxMenu());
		verify(mockedConsole).parseModel(MAILBOX_MENU_TEXT);
	}

	@Test
	 public void shouldAccessUsingRecibeMethod(){
		String idMailBox = "1";
		Mailbox chosenMailbox = new Mailbox(idMailBox, "Hola, como estas?");

		when(mockedMailsystem.findMailbox(idMailBox)).thenReturn(chosenMailbox);
		connection.reciveData(idMailBox);
		verify(mockedConsole).parseModel("Enter mailbox number followed by #");
	}
	@Test
	public void shouldQuitFromMessageMenu() {
		String idMailBox = "1";
		String keyMailBox = "1";
		String mailBoxMenuOption = "1";
		Mailbox chosenMailbox = new Mailbox(idMailBox, "Hola, como estas?");

		when(mockedMailsystem.findMailbox(idMailBox)).thenReturn(chosenMailbox);
		stepsForGettingIntoMailboxMenuOption(idMailBox, keyMailBox, mailBoxMenuOption);
		connection.dial("4");
		assertTrue(connection.isMailBoxMenu());
		verify(mockedConsole,times(2)).parseModel(MAILBOX_MENU_TEXT);
	}

	@Test
	public void shouldRemoveActualMessage() {
		String idMailBox = "1";
		String keyMailBox = "1";
		String mailBoxMenuOption = "1";
		Mailbox chosenMailbox = new Mailbox(idMailBox, "Hola, como estas?");

		when(mockedMailsystem.findMailbox(idMailBox)).thenReturn(chosenMailbox);
		stepsForGettingIntoMailboxMenuOption(idMailBox, keyMailBox, mailBoxMenuOption);
		connection.dial("3");
		assertTrue(connection.isMessageMenu());
		verify(mockedConsole,times(2)).parseModel(MESSAGE_MENU_TEXT);
	}
	@Test
	public void shouldGetIntoMessageMenu() {
		String idMailBox = "1";
		String keyMailBox = "1";
		String mailBoxMenuOption = "1";
		Mailbox chosenMailbox = new Mailbox(idMailBox, "Hola, como estas?");

		when(mockedMailsystem.findMailbox(idMailBox)).thenReturn(chosenMailbox);
		stepsForGettingIntoMailboxMenuOption(idMailBox, keyMailBox, mailBoxMenuOption);

		assertTrue(connection.isMessageMenu());
		verify(mockedConsole).parseModel(MESSAGE_MENU_TEXT);
	}
	@Test
	public void shouldGetIntoChangePassCodeMenu() {
		String idMailBox = "1";
		String keyMailBox = "1";
		String mailBoxMenuOption = "2";
		Mailbox chosenMailbox = new Mailbox(idMailBox, "Hola, como estas?");

		when(mockedMailsystem.findMailbox(idMailBox)).thenReturn(chosenMailbox);
		stepsForGettingIntoMailboxMenuOption(idMailBox, keyMailBox, mailBoxMenuOption);

		assertTrue(connection.isChangePassCode());
		verify(mockedConsole).parseModel("Enter new passcode followed by the # key");
	}

	@Test
	public void shouldChangePassCodeFrom1To2() {
		String idMailBox = "1";
		String keyMailBox = "1";
		String newKeyMailBox = "2";
		String mailBoxMenuOption = "2";
		String hangDown = "h";
		Mailbox chosenMailbox = new Mailbox(idMailBox, "Hola, como estas?");

		when(mockedMailsystem.findMailbox(idMailBox)).thenReturn(chosenMailbox);
		stepsForGettingIntoMailboxMenuOption(idMailBox, keyMailBox, mailBoxMenuOption);

		connection.dial(newKeyMailBox);
		connection.dial("#");
		connection.dial(hangDown);

		stepsForGettingIntoMailBoxMenu(idMailBox, newKeyMailBox);

		assertTrue(connection.isMessageMenu());
		verify(mockedConsole, times(2)).parseModel(MAILBOX_MENU_TEXT);
	}

	@Test
	public void shouldGetIntoChangeGreetingMenu() {
		String idMailBox = "1";
		String keyMailBox = "1";
		String mailBoxMenuOption = "3";
		Mailbox chosenMailbox = new Mailbox(idMailBox, "Hola, como estas?");

		when(mockedMailsystem.findMailbox(idMailBox)).thenReturn(chosenMailbox);
		stepsForGettingIntoMailboxMenuOption(idMailBox, keyMailBox, mailBoxMenuOption);

		assertTrue(connection.isChangeGreeting());
		verify(mockedConsole).parseModel("Record your greeting, then press the # key");
	}

	private void stepsForGettingIntoMailBox(String mailBoxNumber){
		connection.dial(mailBoxNumber);
		connection.dial("#");
	}

	private void stepsForGettingIntoMailBoxMenu(String mailboxNumber, String keyMailBox){
		stepsForGettingIntoMailBox(mailboxNumber);
		connection.dial(keyMailBox);
		connection.dial("#");
	}

	private void stepsForGettingIntoMailboxMenuOption(String mailboxNumber, String keyMailBox, String mailBoxMenuOption){
		stepsForGettingIntoMailBoxMenu(mailboxNumber, keyMailBox);
		connection.dial(mailBoxMenuOption);
	}

}