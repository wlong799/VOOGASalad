package network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import network.Connection;
import network.Message;

/**
 * The daemon/dispatcher/master thread on server side, which listens
 * on the serverSocket to accept new connections from clients.
 * @author CharlesXu
 */
public class Daemon extends Thread {
	
	private static final Logger LOGGER =
			Logger.getLogger( Daemon.class.getName() );
	
	private Coordinator coordinator;
	
	public Daemon(Coordinator coordinator) {
		this.coordinator = coordinator;
	}
	
	@Override
	public void run() {
		LOGGER.info("Daemon starts");
		while (true) {
			try {
				Socket clientSock = coordinator.getServerSocket().accept();
				LOGGER.info("Daemon accepted new connection");
				Connection conn = new Connection(
						coordinator.getMessageQueue(), clientSock);
				coordinator.addConnection(conn);
			} catch (IOException e) {
				Thread.currentThread().interrupt();
			}
		}
    }
}
