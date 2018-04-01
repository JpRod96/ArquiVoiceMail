package test;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;

import main.Connection;
import main.MailSystem;
import main.Telephone;

public class telephoneTest {
	private static final Scanner Scanner = null;
	
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
	public void phoneCanSpeak() {
		OutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		System.setOut(ps);

		Telephone t = new Telephone(Scanner);
		t.speak("hello");
		assertEquals("hello" + System.getProperty("line.separator"), os.toString());
		
		PrintStream originalOut = System.out;
		System.setOut(originalOut);
	
	}
   @Test
   public void shouldHangUp() {
	   
   }
}


