package test;
import main.*;

import static org.junit.Assert.*;
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
	    connection = new Connection(mockedMailsystem, mockedTelephone);
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
}