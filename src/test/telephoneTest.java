package test;
import main.Connection;
import main.MailSystem;
import observers.Telephone;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class telephoneTest {
	private static final Scanner Scanner = null;
	
	MailSystem mockedMailsystem;
    Telephone mockedTelephone;
    Connection connection;

    @Before
	public void init() {
	    mockedMailsystem = mock(MailSystem.class);
	    mockedTelephone = mock(Telephone.class);
	    connection = new Connection(mockedMailsystem);
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
	public void createPhoneJustWithScanner(){
		Telephone t = new Telephone(Scanner);
		assertNotNull(t);
	}

	@Test
	public void createPhoneWithStateWatcher(){
		Telephone t = new Telephone(connection, Scanner);
		assertNotNull(t);
	}
}


