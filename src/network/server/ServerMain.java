package network.server;

import java.io.IOException;

import network.INetworkConfig;

public class ServerMain {
	
	public static void main(String[] args) {
		try {
			Coordinator c = new Coordinator(INetworkConfig.SERVER_PORT);
		} catch (IOException e) {
			// ran out of file descriptor, maybe run the server later
			e.printStackTrace();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}
