package test;

import Persistence.MailboxPersistenceService;
import Persistence.MessagePersistenceService;
import main.*;

import org.junit.Test;

import static org.junit.Assert.*;
public class MailboxTest {
	
	Mailbox mailbox=new Mailbox("pass","saludos");

    @Test
    public void shouldReturnKeptMessages(){
        MessageQueue keptMessages = new MessageQueue();
        mailbox.setKeptMessages(keptMessages);
        assertEquals(keptMessages,mailbox.getKeptMessages());
    }
    @Test
    public void shouldSetKeptMessages(){
        MessageQueue setMessages=new MessageQueue();
        mailbox.setKeptMessages(setMessages);
        assertEquals(setMessages,mailbox.getKeptMessages());
    }
	@Test
	public void shouldChangePasscode() {
		mailbox.setPasscode("newpass");
		assertEquals(mailbox.checkPasscode("saludos"),false);
	}
	@Test
	public void shouldChangeGreeting() {
		mailbox.setGreeting("Hola");
		assertEquals(mailbox.getGreeting(),"Hola");
	}

	@Test
	public void shouldReturnCurretMessage() {
		assertEquals(mailbox.getCurrentMessage(),null);
	}
	
	@Test
	public void shouldAddMessage() {
		Message newMessage=new Message("nuevo mensaje");
		mailbox.addMessage(newMessage);
		assertEquals(mailbox.getCurrentMessage(),newMessage);
	}
	@Test 
	public void shouldRemoveCurretMessage() {
		assertEquals(mailbox.removeCurrentMessage(),null);
	}
	@Test
	public void  ShouldRemoveCurretMessageFromNewMessages() {
		Message newMessage=new Message("nuevo mensaje");
		mailbox.addMessage(newMessage);
		mailbox.removeCurrentMessage();
		assertEquals(mailbox.getCurrentMessage(),null);
	}
	@Test
	public void ShouldRemoveCurretMessageFromKeptMessages() {
		Message newMessage=new Message("nuevo mensaje");
		mailbox.addMessage(newMessage);
		mailbox.saveCurrentMessage();
		mailbox.removeCurrentMessage();
		assertEquals(mailbox.getCurrentMessage(),null);
	}
	@Test
	public void ShouldSaveCurretMessage() {
		Message newMessage=new Message("nuevo mensaje");
		mailbox.addMessage(newMessage);
		mailbox.saveCurrentMessage();
		assertEquals(mailbox.getCurrentMessage(),newMessage);
	}
	@Test
	public void shouldReturnPasscode(){
		assertEquals("pass",mailbox.getPasscode());
	}

	
	
}
