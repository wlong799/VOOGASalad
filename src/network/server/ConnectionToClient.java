package network.server;

import java.net.Socket;
import java.util.concurrent.BlockingQueue;

import network.Connection;
import network.messages.Message;

public class ConnectionToClient extends Connection {

	public ConnectionToClient(
			BlockingQueue<Message> incomingBuffer,
			Socket socket) {
		super(incomingBuffer, socket);
	}

	// TODO cx15 ConnectionToClient
}
