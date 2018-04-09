package test;
import main.*;
import observers.Telephone;

import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class ConnectionTest {

	MailSystem mockedMailsystem;
    Telephone mockedTelephone;
    Connection connection;

    @Before
	public void init() {
	    mockedMailsystem = mock(MailSystem.class);
	    mockedTelephone = mock(Telephone.class);
	    connection = new Connection(mockedMailsystem);
	}

	@Test
	public void shouldBeInConnectedStatus() {
	    assertTrue(connection.isConnected());
	}

	@Test
	public void shouldShowInitialMessage() {
		verify(mockedTelephone).speak("Enter mailbox number followed by #");
	}
	
	@Test
	public void shouldChooseValidMailBox() {
		String idMailBox = "1";
		Mailbox chosenMailbox = new Mailbox(idMailBox, "Hola, como estas?");

		when(mockedMailsystem.findMailbox(idMailBox)).thenReturn(chosenMailbox);
		connection.dial(idMailBox);
		connection.dial("#");

		assertTrue(connection.isRecording());
		verify(mockedTelephone).speak(chosenMailbox.getGreeting());
	}
	
	@Test
	public void shouldGetIntoMailBoxMenu() {
		String idMailBox = "1";
		String keyMailBox = "1";
		Mailbox chosenMailbox = new Mailbox(idMailBox, "Hola, como estas?");

		when(mockedMailsystem.findMailbox(idMailBox)).thenReturn(chosenMailbox);
		connection.dial(idMailBox);
		connection.dial("#");
		connection.dial(keyMailBox);
		connection.dial("#");

		assertTrue(connection.isMailBoxMenu());
	}
	
	@Test
	public void shouldGetIntoMessageMenu() {
		String idMailBox = "1";
		String keyMailBox = "1";
		String mailBoxMenuOption = "1";
		Mailbox chosenMailbox = new Mailbox(idMailBox, "Hola, como estas?");

		when(mockedMailsystem.findMailbox(idMailBox)).thenReturn(chosenMailbox);
		connection.dial(idMailBox);
		connection.dial("#");
		connection.dial(keyMailBox);
		connection.dial("#");
		connection.dial(mailBoxMenuOption);
		connection.dial("#");

		assertTrue(connection.isMessageMenu());
	}
	
	@Test
	public void shouldGetIntoChangePassCodeMenu() {
		String idMailBox = "1";
		String keyMailBox = "1";
		String mailBoxMenuOption = "2";
		Mailbox chosenMailbox = new Mailbox(idMailBox, "Hola, como estas?");

		when(mockedMailsystem.findMailbox(idMailBox)).thenReturn(chosenMailbox);
		connection.dial(idMailBox);
		connection.dial("#");
		connection.dial(keyMailBox);
		connection.dial("#");
		connection.dial(mailBoxMenuOption);

		assertTrue(connection.isChangePassCode());
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
		connection.dial(idMailBox);
		connection.dial("#");
		connection.dial(keyMailBox);
		connection.dial("#");
		connection.dial(mailBoxMenuOption);
		
		connection.dial(newKeyMailBox);
		connection.dial("#");
		connection.dial(hangDown);
		
		connection.dial(idMailBox);
		connection.dial("#");
		connection.dial(newKeyMailBox);
		connection.dial("#");
		
		assertTrue(connection.isMessageMenu());
	}
	
	@Test
	public void shouldGetIntoChangeGreetingMenu() {
		String idMailBox = "1";
		String keyMailBox = "1";
		String mailBoxMenuOption = "3";
		Mailbox chosenMailbox = new Mailbox(idMailBox, "Hola, como estas?");

		when(mockedMailsystem.findMailbox(idMailBox)).thenReturn(chosenMailbox);
		connection.dial(idMailBox);
		connection.dial("#");
		connection.dial(keyMailBox);
		connection.dial("#");
		connection.dial(mailBoxMenuOption);

		assertTrue(connection.isChangeGreeting());
	}
	
}