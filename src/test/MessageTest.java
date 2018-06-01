package test;

import MailVoice.Message;
import org.junit.Test;

import static org.junit.Assert.*;
public class MessageTest {

	@Test
	public void testGetMessage() {
		Message message=new Message("hola buen dia");
		assertEquals(message.getText(),"hola buen dia");
		
		
	}

}
