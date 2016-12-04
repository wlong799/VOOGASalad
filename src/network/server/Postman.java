package network.server;

import network.messages.Message;

/**
 * The worker thread that remove message from the main message queue
 * one at a time and forward the message to all subscribers in the
 * connectionPool
 * 
 * @author CharlesXu
 */
public class Postman extends Thread {
	
	private Coordinator coordinator;
	
	public Postman(Coordinator coordinator) {
		this.coordinator = coordinator;
	}
	
	@Override
	public void run() {
		while(!coordinator.hasStopped()) {
			try {
				Message msg = coordinator.getMessageQueue().take();
				coordinator.broadcast(msg);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
}
