package network.client;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;

import network.Connection;
import network.Message;

public class Reader extends Thread {
	
	private BlockingQueue<Message> inComingBuffer;
	private Queue<Message> shared;
	private Connection connection;
	
	public Reader(BlockingQueue<Message> inComingBuffer,
				  Queue<Message> shared,
				  Connection connection) {
		this.inComingBuffer = inComingBuffer;
		this.shared = shared;
		this.connection = connection;
	}
	
	@Override
	public void run() {
		while (!connection.isClosed()) {
			try {
				Message msg = inComingBuffer.take();
				synchronized(shared) {
					shared.add(msg);
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
}
