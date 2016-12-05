package network.server;

import java.io.IOException;
import java.util.logging.Logger;

import network.core.ServerMode;

/**
 * The main entry to create and run a server instance. 
 * @author CharlesXu
 */
public class ServerMain {
	
	private static final Logger LOGGER =
			Logger.getLogger( ServerMain.class.getName() );
	
	public static void main(String[] args) {
		try {
			new Coordinator(ServerMode.DEV.getServerPort());
		} catch (IOException e) {
			LOGGER.info("Ran out of file descriptor, maybe run the server later");
			e.printStackTrace();
		}
	}

}
