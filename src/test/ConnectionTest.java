package test;
import main.Connection;
import main.MailSystem;
import main.Mailbox;
import observers.Telephone;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class ConnectionTest {

	MailSystem mockedMailsystem;
    Telephone mockedTelephone;
    Connection connection;

    @Before
	public void init() {
	    mockedMailsystem = mock(MailSystem.class);
	    mockedTelephone = mock(Telephone.class);
	    connection = new Connection(mockedMailsystem);
	    connection.addObserver(mockedTelephone);
	}

	@Test
	public void shouldBeInConnectedStatus() {
	    assertTrue(connection.isConnected());
	}

	@Test
	public void shouldShowInitialMessage() {
		verify(mockedTelephone, times(1)).update();
	}
	
	@Test
	public void shouldChooseValidMailBox() {
		String idMailBox = "1";
		Mailbox chosenMailbox = new Mailbox(idMailBox, "Hola, como estas?");

		when(mockedMailsystem.findMailbox(idMailBox)).thenReturn(chosenMailbox);
		stepsForGettingIntoMailBox(idMailBox);

		assertTrue(connection.isRecording());
		verify(mockedTelephone, times(2)).update();
	}

	@Test
	public void shouldGetIntoMailBoxMenu() {
		String idMailBox = "1";
		String keyMailBox = "1";
		Mailbox chosenMailbox = new Mailbox(idMailBox, "Hola, como estas?");

		when(mockedMailsystem.findMailbox(idMailBox)).thenReturn(chosenMailbox);
		stepsForGettingIntoMailBoxMenu(idMailBox, keyMailBox);

		assertTrue(connection.isMailBoxMenu());
		verify(mockedTelephone, times(3)).update();
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
		verify(mockedTelephone, times(4)).update();
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
		verify(mockedTelephone, times(4)).update();
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
		verify(mockedTelephone, times(7)).update();
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
		verify(mockedTelephone, times(4)).update();
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