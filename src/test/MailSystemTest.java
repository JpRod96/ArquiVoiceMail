package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.MailSystem;
import main.Mailbox;

class MailSystemTest {
	MailSystem mailSystem=new MailSystem(10);
	@Test
	void shouldFindMailbox() {
		Mailbox newMailbox=mailSystem.findMailbox("1");
		assertNotEquals(newMailbox.getGreeting(),null);
	}

}
