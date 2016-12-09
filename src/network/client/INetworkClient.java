package network.client;

import java.util.Queue;

import network.exceptions.SessionExpiredException;
import network.exceptions.MessageCreationFailureException;
import network.messages.Message;
import network.messages.MessageType;

/**
 * The Client API for access to the network to talk to other participants 
 * in the game.
 * 
 * @author CharlesXu
 */
public interface INetworkClient {
	
	/**
	 * Read all messages of the <code>messageType</code> that
	 * arrived after last invocation to read() and returns an
	 * empty queue if no new messages received.
	 * @param type specifies the type of message to be read
	 * @return a queue of messages in ordered by arrival time
	 * @throws SessionExpiredException if lost connection to server
	 */
	Queue<Message> read(MessageType type) throws SessionExpiredException;
	
	/**
	 * Send the payload wrapped in a message to all its peers
	 * through the central server, auto serialization
	 * @param payload the object to be sent
	 * @param type specifies the type of message to be sent
	 * @throws MessageCreationFailureException
	 * 				if MessageType unknown to the current version of application
	 * 				or if try to send more than one payload in a single message
	 * @throws SessionExpiredException if lost connection to server
	 */
	void broadcast(Object payload, MessageType type)
			throws MessageCreationFailureException, SessionExpiredException;
	
	/**
	 * Disconnect from server and gracefully clean up
	 */
	void disconnect();
	
	/**
	 * We uses partitioned name space for each client so that concurrent creation
	 * of new sprite by different clients is guaranteed to have different
	 * id/sequence number.
	 * @return the starting sequence number of new sprite
	 */
	long getStartingSequenceNumber() throws SessionExpiredException;
	
	/**
	 * A non-blocking request to acquire a lock on the sprite identified using
	 * <tt>id</tt>. Server returns immediately the userName of the holder of the
	 * lock in question. If such userName differs from that of the call issuer,
	 * then he knows someone else has it and must not proceed with modification.
	 * 
	 * <p> The lock acquire request is done in polling fashion. No lock queue is
	 * maintained. If a client's lock acquire request is rejected, he or she must
	 * explicitly reissue the request at a later time to acquire the lock, i.e.
	 * lock is not handed over to the next client in line. Server will not track
	 * who the next client is. 
	 * 
	 * @param id identifies the sprite to be locked
	 * @return the userName of the client that currently holding the lock
	 * @throws SessionExpiredException if lost connection to server
	 */
	String tryLock(long id) throws SessionExpiredException;
	
	/**
	 * Release the lock on sprite held by the client. 
	 * 
	 * <p>If the lock on sprite is held by other client or if the lock is free,
	 * this call is a no-op.
	 * 
	 * @param id identifies the sprite to be unlocked
	 * @throws SessionExpiredException if lost connection to server
	 */
	void unlock(long id) throws SessionExpiredException;
}
