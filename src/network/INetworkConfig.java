package network;

/** 
 * The server configuration that provides single point of update
 * for the rest of the project.
 * 
 * @author CharlesXu
 */
public interface INetworkConfig {
	
	// ******** DEVELOPMENT ******** 
	public static final String DEV_SERVER_NAME = "127.0.0.1";
	
	//  ******** PRODUCTION  ******** 
	public static final String PROD_SERVER_NAME = "192.168.0.11";
	public static final int SERVER_PORT = 9999;
	
}
