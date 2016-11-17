package network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import network.Connection;
import network.Message;

/**
 * The daemon/dispatcher/master thread on server side, which listens
 * on the serverSocket to accept new connections from clients.
 * @author CharlesXu
 */
public class Daemon extends Thread {
	
	private Coordinator coordinator;
	
	public Daemon(Coordinator coordinator) {
		this.coordinator = coordinator;
	}
	
	@Override
	public void run() {
		System.out.println("Daemon starts");
		while (true) {
			try {
				Socket clientSock = coordinator.getServerSocket().accept();
				System.out.println("someone connects");
				Connection conn = new Connection(
						coordinator.getMessageQueue(), clientSock);
				coordinator.addConnection(conn);
			} catch (IOException e) {
				// TODO cx15
				e.printStackTrace();
			}
		}
    }
}
