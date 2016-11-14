package network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import network.Message;

public class Coordinator {
	
	public static final int BUF_LEN = 4096;
	
	private ServerSocket serverSocket;
	private List<Connection> connectionPool;
	private BlockingQueue<Message> messageQueue;
	private Daemon daemon;
	
	public Coordinator(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		connectionPool = new ArrayList<>();
		// linkedlist works best since no dynamic resizing
		messageQueue = new LinkedBlockingQueue<>();
		daemon = new Daemon(serverSocket, connectionPool, messageQueue);
//		daemon = new Thread(){
//            public void run() {
//                while (true) {
//					try {
//						InputStream input = sd.getInputStream();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//    				byte[] buf = new byte[BUF_LEN];
//    				input.read(buf);
//    				System.out.println(new String(buf));
//                }
//            }
//        };
        daemon.start();
	}
	
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(9999);
			Socket sd = serverSocket.accept();
		} catch (IOException e) {
			System.out.println("Port Number");
			e.printStackTrace();
		}
		
	}
}
