package test;

import main.MailSystem;
import main.Mailbox;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class MailSystemTest {
	MailSystem mailSystem=new MailSystem(10);
	@Test
	public void shouldFindMailbox() {
		Mailbox newMailbox=mailSystem.findMailbox("1");
		assertNotEquals(newMailbox.getGreeting(),null);
	}

}
