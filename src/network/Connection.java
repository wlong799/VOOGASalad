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
	private Receiver receiver;
	private Sender sender;
	
	public Connection(BlockingQueue<Message> inComingBuffer,
					  Socket socket) throws IOException {
		this.socket = socket;
		this.outGoingBuffer = new LinkedBlockingQueue<>();
		this.isClosed = false;
		this.receiver = new Receiver(socket, this, inComingBuffer);
		this.sender = new Sender(socket, this, outGoingBuffer);
		this.receiver.start();
		this.sender.start();
	}
	
	public void send(Message msg) throws InterruptedException {
		outGoingBuffer.put(msg);
	}
	
	public boolean isClosed() {
		return isClosed;
	}

	public void close() {
		this.isClosed = true;
		// TODO cx15 notify coordinator
	}
}
