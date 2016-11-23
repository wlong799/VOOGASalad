package network.server;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

import network.Connection;
import network.messages.Message;

/**
 * The worker thread that remove message from the main message queue
 * one at a time and forward the message to all subscribers in the
 * connectionPool
 * @author CharlesXu
 */
public class Postman extends Thread {
	
	private static final Logger LOGGER =
			Logger.getLogger( Postman.class.getName() );
	
	private List<Connection> connectionPool;
	private BlockingQueue<Message> messageQueue;
	private Coordinator coordinator;
	
	public Postman(List<Connection> connectionPool,
				   BlockingQueue<Message> messageQueue,
				   Coordinator coordinator) {
		this.connectionPool = connectionPool;
		this.messageQueue = messageQueue;
		this.coordinator = coordinator;
	}
	
	@Override
	public void run() {
		while(!coordinator.hasStopped()) {
			try {
				Message msg = messageQueue.take();
				synchronized (connectionPool) {
					for (Connection conn : connectionPool) {
						if (conn.isClosed()) {
							connectionPool.remove(conn);
						} else {
							conn.send(msg);
						}
					}
				}
				LOGGER.info("Broadcasted to all: " + msg);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
}
