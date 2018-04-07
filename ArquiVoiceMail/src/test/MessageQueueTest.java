package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Message;
import main.MessageQueue;

class MessageQueueTest {
	MessageQueue messageQueue=new MessageQueue();

	@Test
	void shouldAddMessageToQueue() {
		Message newMessage=new Message("hola");
		messageQueue.add(newMessage);
		assertEquals(messageQueue.peek(),newMessage);
	}
	@Test 
	void shouldRemoveMessageFromQueue() {
		Message newMessage=new Message("hola");
		messageQueue.add(newMessage);
		Message removedMessage=messageQueue.remove();
		assertEquals(newMessage,removedMessage);
	}
	@Test
	void shouldReturnSizeOfQueue() {
		assertEquals(messageQueue.size(),0);
	}
	@Test
	void shouldReturnPikeOfQueue() {
		assertEquals(messageQueue.peek(),null);
	}
}
