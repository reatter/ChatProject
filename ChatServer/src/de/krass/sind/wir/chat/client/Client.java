package de.krass.sind.wir.chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	
	private String servername;
	
	public String getServername() {
		return servername;
	}

	private int port;
	private Socket socket;
	private PrintWriter out;
    private BufferedReader in;
	
	public Client(String servername, int port) {
		this.servername = servername;
		this.port = port;

	}
	
	public void go() {
		if (init()) {
			sagWas();
		}
	}
	
	private class MessageHandler extends Thread {
		
		private BufferedReader inputFromServer;
		
		public MessageHandler (BufferedReader inputFromServer){
			this.inputFromServer = inputFromServer;
		}
		
		@Override
		public void run() {
			String msg = "";
			try {
				while ((msg = inputFromServer.readLine()) != null) {
					System.out.println(msg);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void sagWas() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		try {
			String s = "";
			while ((s = br.readLine()) != null) {
				out.println(s);
				out.flush();
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private boolean init() {
		try {
			this.socket = new Socket(servername, port);
			out = new PrintWriter(socket.getOutputStream(), true);
		    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    MessageHandler msgHandler = new MessageHandler(in);
		    msgHandler.start();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
//		new Client("10.149.135.58", 5555).go();
		new Client("localhost", 5555).go();
	}

}