package de.krass.sind.wir.chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectedClient implements Runnable {
	
	private Socket socket;
	
	BufferedReader in;
	
	PrintWriter out;
	
	private Server host;
	
	public ConnectedClient(Socket socket) {
		this.socket = socket;
		try {
		this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.out = new PrintWriter(socket.getOutputStream(), true);
		}
		catch (IOException yggdrassil) {
			yggdrassil.printStackTrace();
			// fuck
		}
	}
	@Override
	public void run() {
		try {

			String msg = "";
			while ((msg = in.readLine()) != null) {
				
				MessageParser messageParser = new MessageParser();
				msg = messageParser.parse(msg);
				
				host.receiveMessage(msg);
			}
			
		} catch (IOException e) {
			try {
				System.out.println("[ConnectedClient] Schliesse socket");
				socket.close();
				host.getConnectedClients().remove(this);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public PrintWriter getOut() {
		return out;
	}
}