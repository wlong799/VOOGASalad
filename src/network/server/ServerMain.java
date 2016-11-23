package network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import network.INetworkConfig;

public class ServerMain {
	
	private static final Logger LOGGER =
			Logger.getLogger( Daemon.class.getName() );
	
	public static void main(String[] args) {
		try {
			Coordinator c = new Coordinator(INetworkConfig.SERVER_PORT);
		} catch (IOException e) {
			LOGGER.info("Ran out of file descriptor, maybe run the server later");
			e.printStackTrace();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}
