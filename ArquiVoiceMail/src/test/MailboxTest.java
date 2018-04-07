package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Mailbox;
import main.Message;

class MailboxTest {
	
	Mailbox mailbox=new Mailbox("pass","saludos");
	@Test
	void shouldChangePasscode() {
		mailbox.setPasscode("newpass");
		assertEquals(mailbox.checkPasscode("saludos"),false);
	}
	@Test
	void shouldChangeGreeting() {
		mailbox.setGreeting("Hola");
		assertEquals(mailbox.getGreeting(),"Hola");
	}

	@Test
	void shouldReturnCurretMessage() {
		assertEquals(mailbox.getCurrentMessage(),null);
	}
	
	@Test
	void shouldAddMessage() {
		Message newMessage=new Message("nuevo mensaje");
		mailbox.addMessage(newMessage);
		assertEquals(mailbox.getCurrentMessage(),newMessage);
	}
	@Test 
	void shouldRemoveCurretMessage() {
		assertEquals(mailbox.removeCurrentMessage(),null);
	}
	@Test
	void ShouldRemoveCurretMessageFromNewMessages() {
		Message newMessage=new Message("nuevo mensaje");
		mailbox.addMessage(newMessage);
		mailbox.removeCurrentMessage();
		assertEquals(mailbox.getCurrentMessage(),null);
	}
	@Test
	void ShouldRemoveCurretMessageFromKeptMessages() {
		Message newMessage=new Message("nuevo mensaje");
		mailbox.addMessage(newMessage);
		mailbox.saveCurrentMessage();
		mailbox.removeCurrentMessage();
		assertEquals(mailbox.getCurrentMessage(),null);
	}
	@Test
	void ShouldSaveCurretMessage() {
		Message newMessage=new Message("nuevo mensaje");
		mailbox.addMessage(newMessage);
		mailbox.saveCurrentMessage();
		assertEquals(mailbox.getCurrentMessage(),newMessage);
	}
	
	
}
