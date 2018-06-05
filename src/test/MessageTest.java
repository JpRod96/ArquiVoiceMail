package test;

import MailVoice.Message;
import org.junit.Test;

import static org.junit.Assert.*;
public class MessageTest {

	@Test
	public void testGetMessage() {
		String text="hola buen dia";
		Message message=new Message(text);
		assertEquals(message.getText(),text);
		
		
	}

	@Test
	public void shouldGetMessageId() {
		int id=1;
		String text="hola buen dia";
		Message message=new Message(text,id);
		assertEquals(message.getId(), id);
	}

	@Test
	public void shouldChangeMessageId() {
		int id=1;
		int newId=2;
		String text="hola buen dia";
		Message message=new Message(text,id);
		message.setId(newId);
		assertEquals(message.getId(), newId);
	}

	@Test
	public void shouldGetMailboxId() {
		int id=1;
		int mailBoxId=2;
		String text="hola buen dia";
		Message message=new Message(text,id, mailBoxId);
		assertEquals(message.getMailboxId(), mailBoxId);
	}

	@Test
	public void shouldChangeMailboxId() {
		int id=1;
		int mailBoxId=2;
		int newmailBoxId=9;
		String text="hola buen dia";
		Message message=new Message(text,id, mailBoxId);
		message.setMailboxId(newmailBoxId);
		assertEquals(message.getMailboxId(), newmailBoxId);
	}

}
