package network.core;

import java.util.ResourceBundle;

/**
 * Defines the server mode in either production or development
 * and provide interfaces for getting configuration parameters based
 * on the server mode
 * 
 * @author CharlesXu
 */
public enum ServerMode {

	DEV ("network.devConfig"),
	PROD ("network.prodConfig");
	
	private ResourceBundle config;
	
	private ServerMode(String path) {
		config = ResourceBundle.getBundle(path);
	}
	
	/**
	 * Server name could either be an IP address or some DNS name
	 * @return the server name given the server mode
	 */
	public String getServerName() {
		return config.getString("ServerName");
	}
	
	/**
	 * The port number on which the server is listening.
	 * @return the server port number
	 */
	public int getServerPort() {
		return Integer.parseInt(config.getString("ServerPort"));
	}
	
}
