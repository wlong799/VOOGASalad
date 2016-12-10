package network.client;

import java.net.Socket;
import java.util.concurrent.BlockingQueue;

import network.core.Connection;
import network.exceptions.MessageCreationFailureException;
import network.messages.Message;
import network.messages.MessageType;

/**
 * The connection to server does a handshake with server to provide
 * the userName of the client
 * 
 * @author CharlesXu
 */
public class ConnectionToServer extends Connection {
	
	private static final long DELAY = 100;
	
	private long startingSeqNo;
	
	public ConnectionToServer(BlockingQueue<Message> incomingBuffer,
							  Socket socket,
							  boolean isLeaseHolder,
							  String userName) {
		super(incomingBuffer, socket, isLeaseHolder);
		this.setUserName(userName);
		startingSeqNo = -1L;
		try {
			// TODO cx15 duplicated names
			send(MessageType.HANDSHAKE.build(userName));
		} catch (MessageCreationFailureException e) {
			this.close();
		}
	}

	/**
	 * This call is blocking until server has returned the assigned
	 * starting sequence number.
	 * 
	 * <p>See also {@link Connection#getStartingSequenceNumber()}
	 */
	@Override
	public long getStartingSequenceNumber() {
		while(startingSeqNo < 0) {
			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		return startingSeqNo;
	}
	
	/**
	 * Refers to {@link Connection#setStartingSequenceNumber(long)}
	 */
	@Override
	public void setStartingSequenceNumber(long seqno) {
		if (startingSeqNo > 0) return;
		startingSeqNo = seqno;
	}
	
}
