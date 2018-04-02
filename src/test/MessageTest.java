package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Message;

class MessageTest {

	@Test
	void testGetMessage() {
		Message message=new Message("hola buen dia");
		assertEquals(message.getText(),"hola buen dia");
		
		
	}

}
