package de.krass.sind.wir.chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {
	
	private int port;
	
	private ServerSocket serverSocket;
	private LinkedList<ConnectedClient> connectedClients;
	
		
	public Server(int port) {
		this.port = port;
		this.connectedClients = new LinkedList<>();
	}
	
	public void go() {
		boolean running = init();
		if (!running) {
			return;
		}
		
		while(running) {
			try {
				Socket client = serverSocket.accept();
				handleConnection(client);
			} catch (IOException e) {
				running = false;
				e.printStackTrace();
			}
		}
	}
	
	private void handleConnection(Socket client) {
		ConnectedClient connectedClient = new ConnectedClient(client);
		connectedClients.add(connectedClient);
		Thread clientThread = new Thread(connectedClient);
		clientThread.start();
		connectedClient.getOut().println("Willkommen du bist!");
		connectedClient.getOut().flush();
		System.out.println("[Server] Client connected: " + connectedClient.toString());
	}
	
	public void sendeAnAlle(String msg) {
		for (ConnectedClient client : connectedClients) {
			client.getOut().println(">> " + msg);
			client.getOut().flush();
		}
	}
	
	private boolean init() {
		try {
			this.serverSocket = new ServerSocket(port);
			System.out.println("Server gestartet " + serverSocket.getLocalSocketAddress());
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public int getPort() {
		return port;
	}

	public static void main(String[] args) {
		new Server(5555).go();
	}

	public LinkedList<ConnectedClient> getConnectedClients() {
		return connectedClients;
	}

	public void receiveMessage(String msg) {
		//TODO ControLler-logic
		sendeAnAlle(msg);
	}

}