package network.core;

import java.util.logging.Logger;

import network.exceptions.MessageCreationFailureException;
import network.messages.MessageType;

/**
 * A timer thread that periodically check and renew the session
 * lease to keep the connection alive unless failures or network
 * partition.
 * 
 * @author CharlesXu
 */
public class LeaseTimer extends Thread {

	public static final long DEAFUALT_TTL_MILLIS = 8000;
	
	private static final Logger LOGGER =
			Logger.getLogger( LeaseTimer.class.getName() );

	private Connection connection;
	private boolean isLeaseHolder;
	private final long TTL_MILLIS;
	private final long HALF_TTL_MILLIS;
	private final long THIRD_TTL_MILLIS;

	/**
	 * Uses default timeout value of 8 seconds
	 * @param conn the connection that is leased under
	 * @param isLeaseHolder true if responsible for lease renewal
	 */
	public LeaseTimer(Connection conn, boolean isLeaseHolder) {
		this(conn, isLeaseHolder, DEAFUALT_TTL_MILLIS);
	}
	
	/**
	 * Allows user to specify the timeout in milliseconds
	 * @param conn the connection that is leased under
	 * @param isLeaseHolder true if responsible for lease renewal
	 * @param TTL lease duration/freshness in milliseconds
	 */
	public LeaseTimer(Connection conn, boolean isLeaseHolder, long TTL) {
		this.connection = conn;
		this.isLeaseHolder = isLeaseHolder;
		this.TTL_MILLIS = TTL;
		this.HALF_TTL_MILLIS = TTL_MILLIS / 2;
		this.THIRD_TTL_MILLIS = TTL_MILLIS / 3;
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
					connection.send(MessageType.DISCONNECT
										.build(connection.getUserName()));
				} catch (MessageCreationFailureException e) {
					// trusted code but log this just in case
					LOGGER.info("Lease Timer failed to create DISCONNECT message");
				}
				connection.close();
			} else if (isLeaseHolder && timeElapsed > HALF_TTL_MILLIS) {
				try {
					connection.send(MessageType.SESSION_LEASE
										.build(connection.getUserName()));
					LOGGER.info("KeepAlive request sent");
					Thread.sleep(HALF_TTL_MILLIS);
				} catch (MessageCreationFailureException e) {
					// trusted code but log this just in case
					LOGGER.info("Lease Timer failed to create SESSION_LEASE message");
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			} else {
				try {
					Thread.sleep(THIRD_TTL_MILLIS);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}
	}

}
