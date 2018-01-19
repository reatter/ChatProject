package de.krass.sind.wir.chat.server.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.krass.sind.wir.chat.server.MessageParser;

public class MessageParserTest {

	String input;
	String output;
	String expected;
	MessageParser messageParser = new MessageParser();

	@Before
	public void setUp(){

	}


	@Test
	public void parse_emptyString_returns_emptyString() {
		input = "";

		// act
		output = messageParser.parse(input);
		
		// assert
		assertEquals("empty String input should result in empty string output",input,output);
	}
	
	@Test
	public void parse_hello_returns_hello() {
		// arrange
		input = "hello";

		output = messageParser.parse(input);
		
		// assert
		assertEquals("input 'hello' should result in output 'hello'",input,output);
	}
	
	@Test
	public void parse_ADMINMAGIC_at_first_place_returns_emptyString() {
		// arrange
		input = MessageParser.ADMINMAGIC;
		expected = "Enter a CONSOLE_COMMAND";

		// act
		String output = messageParser.parse(input);
		
		// assert
		assertEquals("input ADMINMAGIC at first place should result in output empty string",expected,output);
	}
	
	@Test
	public void parse_ADMINMAGIC_hello_at_first_place_returns_CONSOLE_COMMAND_UNKNOWN() {
		// arrange
		input = MessageParser.ADMINMAGIC + "hello";
		expected = "ERROR: CONSOLE_COMMAND_UNKNOWN";

		// act
		output = messageParser.parse(input);
		
		// assert
		assertEquals("input ADMINMAGIC + hello at first place should result in output empty string",expected,output);
	}
	
	@Test
	public void parse_ADMINMAGIC_hello_at_second_place_echoes_input() {
		// arrange
		input = " " + MessageParser.ADMINMAGIC + "hello";
		expected = input;

		// act
		output = messageParser.parse(input);
		
		// assert
		assertEquals("input ADMINMAGIC + hello at seconds place should echo input",expected,output);
	}
	
	@Test
	public void parse_ADMINMAGIC_echo_SPACE_hello_echoes_hello() {
		// arrange
		input = MessageParser.ADMINMAGIC + "echo " + "hello";
		expected = "hello";

		// act
		output = messageParser.parse(input);
		
		// assert
		assertEquals("input 'ADMINMAGICecho hello' should echo 'hello'",expected,output);
	}
	
	@Test
	public void parse_ADMINMAGIC_port_should_return_port_number() {
		// arrange
		input = MessageParser.ADMINMAGIC + "port";
		expected = "";

		// act
		output = messageParser.parse(input);
		
		// assert
		assertEquals("input 'ADMINMAGICport' should echo Server.getPort()",expected,output);
	}
	
	
	

}
