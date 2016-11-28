package network;

import java.util.logging.Logger;

import network.exceptions.MessageCreationFailureException;
import network.messages.MessageType;

/**
 * A timer thread that periodically check and renew the session
 * lease to keep the connection alive unless failures or network
 * partition.
 * @author CharlesXu
 */
public class LeaseTimer extends Thread {

	public static final long TTL_MILLIS = 10000;
	
	private static final Logger LOGGER =
			Logger.getLogger( LeaseTimer.class.getName() );

	private Connection connection;
	private boolean isLeaseHolder;

	public LeaseTimer(Connection conn, boolean isLeaseHolder) {
		this.connection = conn;
		this.isLeaseHolder = isLeaseHolder;
	}

	@Override
	public void run() {
		LOGGER.info("Lease Timer starts");
		while (!connection.isClosed()) {
			
			long timeElapsed = System.currentTimeMillis() -
								connection.getLastActiveMillis();
			if (timeElapsed > TTL_MILLIS) {
				LOGGER.info("Session Timeout");
				try {
					connection.send(MessageType.DISCONNECT.build());
				} catch (MessageCreationFailureException e) {
					// trusted code
				}
				connection.close();
				// TODO cx15 tell every one else
			} else if (isLeaseHolder && timeElapsed > TTL_MILLIS / 2) {
				try {
					connection.send(MessageType.SESSION_LEASE.build());
					LOGGER.info("KeepAlive request sent");
					Thread.sleep(TTL_MILLIS / 2);
				} catch (MessageCreationFailureException e) {
					// trusted code
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			} else {
				try {
					Thread.sleep(TTL_MILLIS / 3);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}
	}

}
