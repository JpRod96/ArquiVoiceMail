package test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import main.MailSystemTester;
import main.Telephone;
public class mailSystemTesterTest {
	
	@Test
    public void testThermostatMain() {
        MailSystemTester launcher = Mockito.mock(MailSystemTester.class);
        
        Mockito.verify(launcher).main(new String[] { "1", "2" });
        String input = "31#";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        input = "q";
        in = new ByteArrayInputStream(input.getBytes());
	 }
}		
	
	


