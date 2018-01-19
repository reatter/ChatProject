package de.krass.sind.wir.chat.container;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class Message implements Serializable {
	
	private static final long serialVersionUID = 101010101010L;
	
	private LocalDateTime dateTime = LocalDateTime.now();
	
	private String senderName = "";
	
	private String receiverName = "";
	
	private String messageText = "";
	
	public Message(LocalDateTime dateTime, String senderName, String receiverName, String messageTxt) {
		this.dateTime = dateTime;
		this.senderName = senderName;
		this.receiverName = receiverName;
		this.messageText = messageText;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}
	
	
	
	
	
	
}