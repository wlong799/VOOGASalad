package network.client;

import java.net.Socket;
import java.util.concurrent.BlockingQueue;

import network.Connection;
import network.messages.Message;

public class ConnectionToServer extends Connection {

	public ConnectionToServer(
			BlockingQueue<Message> incomingBuffer,
			Socket socket) {
		super(incomingBuffer, socket);
	}
	
	// TODO cx15 ConnectionToServer

}
