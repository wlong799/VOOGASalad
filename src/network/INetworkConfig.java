package network;

import java.util.ResourceBundle;

/** 
 * The server configuration that provides single point of update
 * for the rest of the project.
 * 
 * @author CharlesXu
 */
public interface INetworkConfig {
	
	final static ResourceBundle DEV = ResourceBundle.getBundle("network.devConfig");
	final static ResourceBundle PROD = ResourceBundle.getBundle("network.devConfig");
	
	public static final String DEV_SERVER_NAME = DEV.getString("ServerName");
	public static final int DEV_SERVER_PORT = Integer.parseInt(DEV.getString("ServerPort"));
	
	public static final String PROD_SERVER_NAME = PROD.getString("ServerName");
	public static final int PROD_SERVER_PORT = Integer.parseInt(PROD.getString("ServerPort"));
}
