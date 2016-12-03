package network.server;

import java.io.IOException;
import java.util.logging.Logger;

import network.INetworkConfig;

/**
 * The main entry to create and run a server instance. 
 * @author CharlesXu
 */
public class ServerMain {
	
	private static final Logger LOGGER =
			Logger.getLogger( ServerMain.class.getName() );
	
	public static void main(String[] args) {
		try {
			new Coordinator(INetworkConfig.DEV_SERVER_PORT);
		} catch (IOException e) {
			LOGGER.info("Ran out of file descriptor, maybe run the server later");
			e.printStackTrace();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}
