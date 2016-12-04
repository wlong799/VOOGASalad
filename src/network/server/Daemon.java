package network.server;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * The daemon/dispatcher/master thread on server side, which listens
 * on the serverSocket to accept new connections from clients.
 * 
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
		while (!coordinator.hasStopped()) {
			try {
				Socket clientSock = coordinator.getServerSocket().accept();
				LOGGER.info("Daemon accepted new connection");
				ConnectionToClient conn = new ConnectionToClient(
						coordinator.getMessageQueue(), clientSock, false, coordinator);
				coordinator.addConnection(conn);
			} catch (IOException e) {
				// daemon keeps running in the hope that some connection will close soon
				LOGGER.info("Daemon encounters IOException due to shortage of file descriptor");
				e.printStackTrace();
			}
		}
    }
}
