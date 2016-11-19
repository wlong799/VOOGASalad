package network;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import network.server.Coordinator;

public class Connection {
	
	private Socket socket;
	private BlockingQueue<Message> outGoingBuffer;
	private boolean isClosed;
//	private Receiver receiver;
//	private Sender sender;
	
	public Connection(BlockingQueue<Message> incomingBuffer, 
					  Socket socket) {
		this.socket = socket;
		this.outGoingBuffer = new LinkedBlockingQueue<>();
		this.isClosed = false;
		new Receiver(socket, this, incomingBuffer).start();
		new Sender(socket, this, outGoingBuffer).start();
//		this.receiver.start();
//		this.sender.start();
	}
	
	public void send(Message msg) {
		try {
			outGoingBuffer.put(msg);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
	public synchronized boolean isClosed() {
		return isClosed;
	}

	public synchronized void close() throws IOException {
		this.isClosed = true;
		socket.close();
	}
}
