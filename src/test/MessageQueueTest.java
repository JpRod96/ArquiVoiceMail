package test;

import main.Message;
import main.MessageQueue;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MessageQueueTest {
	MessageQueue messageQueue=new MessageQueue();

	@Test
	public void shouldAddMessageToQueue() {
		Message newMessage=new Message("hola");
		messageQueue.add(newMessage);
		assertEquals(messageQueue.peek(),newMessage);
	}
	@Test 
	public void shouldRemoveMessageFromQueue() {
		Message newMessage=new Message("hola");
		messageQueue.add(newMessage);
		Message removedMessage=messageQueue.remove();
		assertEquals(newMessage,removedMessage);
	}
	@Test
	public void shouldReturnSizeOfQueue() {
		assertEquals(messageQueue.size(),0);
	}
	@Test
	public void shouldReturnPikeOfQueue() {
		assertEquals(messageQueue.peek(),null);
	}
}
