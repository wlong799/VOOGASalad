package network.client;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import network.Connection;
import network.exceptions.JeopardyException;
import network.exceptions.MessageCreationFailureException;
import network.exceptions.ServerDownException;
import network.messages.Message;
import network.messages.MessageType;

/** 
 * @author CharlesXu
 */
public class NetworkClient implements INetworkClient{
	
	private Socket socket;
	private Connection connectionToServer;
	private BlockingQueue<Message> inComingBuffer;
	private Queue<Message> nonBlockingIncomingBuffer;
	private Multiplexer mux;
	
	private String userName;
	
	public NetworkClient(String userName) throws ServerDownException {
		this.userName = userName;
		try {
			socket = new Socket(DEV_SERVER_NAME, SERVER_PORT);
			inComingBuffer = new LinkedBlockingQueue<>();
			connectionToServer = new ConnectionToServer(inComingBuffer, socket, true, userName);
			nonBlockingIncomingBuffer = new LinkedList<>();
			mux = new Multiplexer();
			startReaderThread();
		} catch (IOException e) {
			throw new ServerDownException();
		}
	}
	
	/**
	 * Refers to INetworkClient for more information. 
	 * 
	 * <p>This method is non-blocking. 
	 * 
	 * <p>Our developers are not familiar with the parallel computing paradigm
	 * in which a read from an empty synchronized buffer shall block the accessing
	 * thread. We have seen attempts to call this method using UI thread/main thread
	 * and the whole UI freezes when it blocks.
	 * 
	 * <p>To mitigate this challenge, the client starts a reader thread in its 
	 * constructor to implement this interface in a non-blocking fashion. 
	 * 
	 * @return a queue of messages read ordered in time
	 */
	@Override
	public Queue<Message> read(MessageType type) throws JeopardyException {
		if (connectionToServer.isClosed()) {
			throw new JeopardyException();
		}
		Queue<Message> msgsReceived;
		synchronized(nonBlockingIncomingBuffer) {
			msgsReceived = nonBlockingIncomingBuffer;
			nonBlockingIncomingBuffer = new LinkedList<>();
		}
		for(Message msg : msgsReceived) {
			msg.multiplex(mux);
		}
		Queue<Message> ret = mux.getMessageQueue(type);
		mux.flush(type);
		return ret;
	}
	
	@Override
	public void broadcast(Object payload, MessageType type)
			throws MessageCreationFailureException, JeopardyException {
		if (connectionToServer.isClosed()) {
			throw new JeopardyException();
		}
		signAndsend(type, payload);
	}
	
	private void signAndsend(MessageType type, Object... payload)
			throws MessageCreationFailureException {
		Message msg = type.build(payload);
		msg.setSender(userName);
		connectionToServer.send(msg);
	}
	
	@Override
	public void disconnect() {
		if (!connectionToServer.isClosed()) {
			try {
				signAndsend(MessageType.DISCONNECT);
				connectionToServer.close();
			} catch (MessageCreationFailureException e) {
				// trusted code with well defined message, no way for exception
			}
		}
	}
	
	/**
	 * This can NOT be extracted to another class. Passing the reference
	 * <code>nonBlockingIncomingBuffer</code> to another class (like in constructor)
	 * creates extra copy to the same message queue. In that case, since NetworkClient.read()
	 * empties this queue by have the reference point to a new Queue, the extra copy in another
	 * class still points to old Queue. 
	 * 
	 * <p>One might propose a setter on that class to update its copy of the reference, but
	 * operations to change the two references are NOT atomic, which is vulnerable especially 
	 * when one copy is updated and the other is not. 
	 */
	private void startReaderThread() {
		Thread reader = new Thread() {
			@Override
			public void run() {
				while (!connectionToServer.isClosed()) {
					try {
						Message msg = inComingBuffer.take();
						synchronized(nonBlockingIncomingBuffer) {
							nonBlockingIncomingBuffer.add(msg);
						}
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}
				}
			}
		};
		reader.start();
	}
}
