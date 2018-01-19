package de.krass.sind.wir.chat.server;

import de.krass.sind.wir.chat.container.Message;

public class MessageParser {

	public static final String ADMINMAGIC = "%%";

	public String parse(String input) {

		if(input.length() > 1 && input.substring(0, 2).equals(ADMINMAGIC)) {
			if (input.length() == 2) {
				return "Enter a CONSOLE_COMMAND";
			}
			else if (input.length() > 7 && input.substring(2,7).equals("echo ")){
				return input.substring(7);
			} else {
				return "ERROR: CONSOLE_COMMAND_UNKNOWN";
			}	
		}
		return input;
	}
	
	public Message parse(Message message) {
		
		//TODO logic
		
		
		return message;
	}

}