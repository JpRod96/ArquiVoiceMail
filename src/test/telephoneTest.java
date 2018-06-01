package test;
import Views.ConsoleViews.Console;
import main.Connection;
import main.MailSystem;
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
    Console mockedConsole;
    Connection connection;

    @Before
	public void init() {
	    mockedMailsystem = mock(MailSystem.class);
	    mockedConsole = mock(Console.class);
	    connection = new Connection(mockedMailsystem);
	}

	@Test
	public void phoneCanSpeak() {
		OutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		System.setOut(ps);

		Console t = new Console(Scanner);
		t.speak("hello");
		assertEquals("hello" + System.getProperty("line.separator"), os.toString());
		
		PrintStream originalOut = System.out;
		System.setOut(originalOut);
	
	}
	@Test
	public void createPhoneJustWithScanner(){
		Console t = new Console(Scanner);
		assertNotNull(t);
	}

	@Test
	public void createPhoneWithStateWatcher(){
		Console t = new Console(connection, Scanner);
		assertNotNull(t);
	}
}


